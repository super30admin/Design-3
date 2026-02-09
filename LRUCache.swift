class DLLNode {
    let key: Int
    var value: Int
    var next: DLLNode?
    weak var prev: DLLNode?
    init(key: Int, value: Int) {
        self.key = key
        self.value = value
    }
}


class LRUCache {

    let capacity: Int
    var nodeMap = Dictionary<Int, DLLNode>()
    var head: DLLNode?
    var tail: DLLNode?

    init(_ capacity: Int) {
        self.capacity = capacity
    }
    
    func get(_ key: Int) -> Int {
        guard let node = nodeMap[key] else {
            return -1
        }
        moveToHead(node: node)
        return node.value
    }
    
    func put(_ key: Int, _ value: Int) {
        if var node = nodeMap[key] {
            node.value = value
            moveToHead(node: node)
            return
        }
        let newNode = DLLNode(key: key, value: value)
        nodeMap[key] = newNode
        addToHead(node: newNode)
        if (nodeMap.count > capacity), let tailKey = tail?.key {
            //remove the least recently used node i.e. tail and add this node
            removeTail()
            nodeMap.removeValue(forKey: tailKey)
        }
    }

    private func removeTail() {
        if let tail = self.tail {
            removeNode(node: tail)
        }
    }

    private func moveToHead(node: DLLNode) {
        //if the node is head then return
        if (node === head) {
            return
        }
        removeNode(node: node)
        addToHead(node: node)
    }

    private func removeNode(node: DLLNode) {
        node.prev?.next = node.next
        node.next?.prev = node.prev
        if (tail === node) {
            tail = tail?.prev
        }
        if (head === node) {
            head = head?.next
        }
        nodeMap[node.key] = nil
    }

    private func addToHead(node: DLLNode) {
        nodeMap[node.key] = node
        if (head == nil) {
            head = node
            tail = node
            return
        }
        node.next = head
        head?.prev = node
        head = node
    }
}

/**
 * Your LRUCache object will be instantiated and called as such:
 * let obj = LRUCache(capacity)
 * let ret_1: Int = obj.get(key)
 * obj.put(key, value)
 */
