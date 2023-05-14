# Time Complexity: O(1) for both get and put operations because we are using a hashmap and a doubly linked list.
# Space Complexity: O(capacity) because we are using a hashmap and a doubly linked list.
# Did this code successfully run on Leetcode : Yes
# Any problem you faced while coding this : No


# Your code here along with comments explaining your approach in three sentences only
"""
Here we use a hashmap and a doubly linked list. The hashmap stores the key and the node. The doubly linked list stores the key and value.
"""



class LRUCache:

    class Node:
        def __init__(self, key, value):
            self.key = key
            self.value = value
            self.next = None
            self.prev = None

    def removeNode(self, node):
        node.prev.next = node.next
        node.next.prev = node.prev

    def addToHead(self, node):
        node.next = self.head.next
        node.prev = self.head
        self.head.next.prev = node
        self.head.next = node

    def __init__(self, capacity: int):
        self.capacity = capacity
        self.head = self.Node(None, None)
        self.tail = self.Node(None, None)
        self.head.next = self.tail
        self.tail.prev = self.head
        self.map = {}

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
            self.removeNode(node)
            self.addToHead(node)
            node.value = value
        else:
            if self.capacity == len(self.map):
                tailprev = self.tail.prev
                self.removeNode(tailprev)
                del self.map[tailprev.key]
            node = self.Node(key, value)
            self.addToHead(node)
            self.map[key] = node


# Your LRUCache object will be instantiated and called as such:
# obj = LRUCache(capacity)
# param_1 = obj.get(key)
# obj.put(key,value)
