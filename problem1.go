// Time Complexity : O(1)
// Space Complexity : O(capacity)
// Did this code successfully run on Leetcode : Yes
// Any problem you faced while coding this : No
//TC - O(1)
//SC  - O(capacity)

type LRUCache struct {
    capacity    int
    head        *Node
    tail        *Node
    hashmap     map[int]*Node     
}

type Node struct{
    key     int
    value   int
    next    *Node
    prev    *Node
}

func Constructor(capacity int) LRUCache {
    cache := LRUCache{
        capacity: capacity,
        hashmap: map[int]*Node{},
    }
    
    cache.head = &Node{
        key:    -1,
        value:  -1,
    }
    
    cache.tail = &Node{
        key:    -1,
        value:  -1,
    }
    
    cache.head.next = cache.tail
    cache.tail.prev = cache.head
    
    return cache
}


func (this *LRUCache)addToHead(node *Node){
    node.next = this.head.next
    node.prev = this.head
    this.head.next = node
    node.next.prev = node
}

func (this *LRUCache)removeNode(node *Node){
    node.prev.next = node.next
    node.next.prev = node.prev
}

func (this *LRUCache) Get(key int) int {
    node,ok := this.hashmap[key]
    
    if !ok{
        return -1
    }
    
    this.removeNode(node)
    this.addToHead(node)
    return node.value
}


func (this *LRUCache) Put(key int, value int)  {
    if node,ok := this.hashmap[key];ok{
        node.value = value
        this.removeNode(node)
        this.addToHead(node)
        
        return
    }
    
    
    if len(this.hashmap) == this.capacity{
        prev := this.tail.prev
        this.removeNode(prev)
        delete(this.hashmap,prev.key)
    }
    
    node:= &Node{
        key: key,
        value: value,
    }
    
    this.addToHead(node)
    this.hashmap[key] = node
    
}


/**
 * Your LRUCache object will be instantiated and called as such:
 * obj := Constructor(capacity);
 * param_1 := obj.Get(key);
 * obj.Put(key,value);
 */
