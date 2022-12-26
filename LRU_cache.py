class Node:
    def __init__(self, key: int, value: int):
        self.key = key
        self.value = value
        self.next = Node
        self.prev = Node

class LRUCache:

    def __init__(self, capacity: int):
        self.capacity = capacity
        self.map = {}
        self.head = Node(-1, -1)
        self.tail = Node(-1, -1)
        self.head.next = self.tail
        self.tail.prev = self.head

    def removeNode(self, node: Node):
        node.next.prev = node.prev
        node.prev.next = node.next

    def addToHead(self, node: Node):
        node.next = self.head.next
        node.next.prev = node
        node.prev = self.head
        self.head.next = node

    def get(self, key: int) -> int:
        if key not in self.map:
            return -1
        node = self.map[key]
        self.removeNode(node)
        self.addToHead(node)
        return node.value

    def put(self, key: int, value: int) -> None:
        if key in self.map:
            existingKey = self.map[key]
            existingKey.value = value
            self.removeNode(existingKey)
            self.addToHead(existingKey)
            return
        if self.capacity == len(self.map):
            tailPrev = self.tail.prev
            self.removeNode(tailPrev)
            self.map.pop(tailPrev.key)
        newNode = Node(key, value)
        self.addToHead(newNode)
        self.map[key] = newNode 


# Your LRUCache object will be instantiated and called as such:
# obj = LRUCache(capacity)
# param_1 = obj.get(key)
# obj.put(key,value)
