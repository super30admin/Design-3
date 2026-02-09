/**
 * // This is the interface that allows for creating nested lists.
 * // You should not implement it, or speculate about its implementation
 * class NestedInteger {
 *     // Return true if this NestedInteger holds a single integer, rather than a nested list.
 *     public func isInteger() -> Bool
 *
 *     // Return the single integer that this NestedInteger holds, if it holds a single integer
 *     // The result is undefined if this NestedInteger holds a nested list
 *     public func getInteger() -> Int
 *
 *     // Set this NestedInteger to hold a single integer.
 *     public func setInteger(value: Int)
 *
 *     // Set this NestedInteger to hold a nested list and adds a nested integer to it.
 *     public func add(elem: NestedInteger)
 *
 *     // Return the nested list that this NestedInteger holds, if it holds a nested list
 *     // The result is undefined if this NestedInteger holds a single integer
 *     public func getList() -> [NestedInteger]
 * }
 */

class StackUsingLinkedList<T> {
    class LinkedListNode<T> {
        var value: T
        var next: LinkedListNode<T>?
        init(value: T) {
            self.value = value
        }
    }

    private var head: LinkedListNode<T>?
    private var count: Int = 0


    func push(_ value: T) {
        let newNode = LinkedListNode(value: value)
        if (head == nil) {
            head = newNode
            return
        }
        newNode.next = head
        head = newNode
        count += 1
    }

    func pop() -> T? {
        if (head == nil) {
            return nil
        }
        let node = head
        head = head?.next
        count -= 1

        return node?.value
    }

    var peek: T? {
        return head?.value
    }

    var isEmpty: Bool {
        return head == nil
    }

    var size: Int {
         return count
    }
}

class NestedIterator {

    var st = StackUsingLinkedList<IndexingIterator<[NestedInteger]>>()
    var nextEl: NestedInteger?
    init(_ nestedList: [NestedInteger]) {
        st.push(nestedList.makeIterator())
    }
    
    func next() -> Int {
        return nextEl!.getInteger()
    }
    
    func hasNext() -> Bool {
        while (!st.isEmpty) {
            guard var iterator = st.peek else {
                return false
            }

            if let ni = iterator.next() {
                //update iterator on stack
                st.pop()
                st.push(iterator)

                if (ni.isInteger()) {
                    nextEl = ni
                    return true
                } else {
                    st.push(ni.getList().makeIterator())
                }
            } else {
                st.pop()
            }
        }
        return false
    }
}

/**
 * Your NestedIterator object will be instantiated and called as such:
 * let obj = NestedIterator(nestedList)
 * let ret_1: Int = obj.next()
 * let ret_2: Bool = obj.hasNext()
 */
