class LRUCache:

    class Node:
        def __init__(self, key, value):
            self.key = key
            self.value = value
            self.prev = None
            self.next = None

    def __init__(self, capacity: int):
        self.map = {}
        self.capacity = capacity
        self.head = self.Node(-1, -1)
        self.tail = self.Node(-1, -1)
        self.head.next = self.tail
        self.tail.prev = self.head

    def removeNode(self, node):
        node.prev.next = node.next
        node.next.prev = node.prev
        node.prev = None
        node.next = None

    def addToHead(self, node):
        node.prev = self.head
        node.next = self.head.next
        node.next.prev = node
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
            node = self.map[key]
            node.value = value
            self.removeNode(node)
            self.addToHead(node)
        else:
            if len(self.map) == self.capacity:
                lastNode = self.tail.prev
                self.removeNode(lastNode)
                del self.map[lastNode.key]
            newNode = self.Node(key, value)
            self.addToHead(newNode)
            self.map[key] = newNode

# TC -  O(1) for both get and put
# SC - O(capacity) for storing up to capacity items in map and linked list