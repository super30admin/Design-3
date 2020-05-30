# Time complexity: O(1)
# Space complexity: O(capacity)
# Did this code successfully run on Leetcode : Yes
# Any problem you faced while coding this : No
class Node:

    def __init__(self, key, val):
        self.key = key
        self.val = val
        self.next = None
        self.prev = None


class LRUCache:

    def __init__(self, capacity: int):
        self.head = Node(-1, -1)
        self.tail = Node(-1, -1)
        self.size = capacity
        self.map = {}  # initialize hashmap
        self.head.next = self.tail
        self.tail.prev = self.head
        self.count = 0

    def get(self, key: int) -> int:
        if key in self.map:
            node = self.map[key]
            self.deleteNode(key)
            self.addNode(node)
            return node.val
        return -1

    def put(self, key: int, value: int) -> None:
        if key in self.map:
            self.deleteNode(key)
        elif len(self.map) >= self.size:
            node = self.tail.prev
            self.deleteNode(node.key)
        node = Node(key, value)
        self.addNode(node)

    def addNode(self, node):
        self.map[node.key] = node
        next = self.head.next
        self.head.next = node
        node.prev = self.head
        node.next = next
        next.prev = node

    def deleteNode(self, key):
        node = self.map[key]
        prev = node.prev
        next = node.next
        prev.next = next
        next.prev = prev

        del self.map[key]

# Your LRUCache object will be instantiated and called as such:
# obj = LRUCache(capacity)
# param_1 = obj.get(key)
# obj.put(key,value)
