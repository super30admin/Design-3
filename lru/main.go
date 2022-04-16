

type Node struct {
	Key int
	Val int
	Next *Node
	Prev *Node
}

type LRUCache struct {
	Head *Node
	Tail *Node
	Cap int
	Size int
	Lookup map[int]*Node
}

func Constructor(cap int) LRUCache {
	return LRUCache{
		Head: nil,
		Tail: nil,
		Cap:  cap,
		Size: 0,
		Lookup: map[int]*Node{},
	}
}

func (l *LRUCache) isEmpty() bool {
	if l.Size == 0 {
		return true
	}
	return false
}

func (l *LRUCache) maxedOut() bool {
	return l.Size == l.Cap
}

func (l *LRUCache) moveNodeToTail(n *Node) {
	if n == nil || l.Head == nil || l.Tail == n {
		return
	}
	// 1-2-3-4 ( move 2 ) 1-3-4-2
	// 1 2-3-4 ( move 1 ) 2-3-4-1
	prev := n.Prev
	next := n.Next
	if prev == nil {
		//  i.e the current n is head
		newHead := n.Next
		l.Head = newHead
		n.Next = nil
		next.Prev = nil
		l.Tail.Next = n
		n.Prev = l.Tail
		l.Tail = n
		return
	}
	prev.Next = next
	next.Prev = prev
	n.Next = nil
	n.Prev = l.Tail
	l.Tail.Next = n
	l.Tail = n
}

func (l *LRUCache) Get(key int) int {
	n, found := l.Lookup[key]
	if !found {
		return -1
	}
	l.moveNodeToTail(n)
	return n.Val
}

func (l *LRUCache) deleteHead() {
	if l.Head == nil {
		return
	}
	if l.Head.Next == nil {
		// i.e head is the only node
		l.Head = nil
		l.Tail = nil
		return
	}
	// 1-2-3 - 2-3
	// 1<->2 - 2
	newHead := l.Head.Next
	newHead.Prev = nil
	l.Head.Next = nil
	l.Head = newHead
}

func (l *LRUCache) Put(key, val int) {
	existingNode, ok := l.Lookup[key]
	if ok {
		existingNode.Val = val
		l.moveNodeToTail(existingNode)
		return
	}
	if l.maxedOut() {
		delete(l.Lookup, l.Head.Key)
		l.deleteHead()
		l.Size--
	}

	node := &Node{Val: val, Key: key}
	if l.Head == nil {
		l.Head = node
		l.Tail = node
	} else {
		node.Prev = l.Tail
		l.Tail.Next = node
		l.Tail = node
	}
	l.Size++
	l.Lookup[key] = node
}
/**
 * Your LRUCache object will be instantiated and called as such:
 * obj := Constructor(capacity);
 * param_1 := obj.Get(key);
 * obj.Put(key,value);
 */
