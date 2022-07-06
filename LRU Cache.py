""""// Time Complexity : O(1)
// Space Complexity : O(capacity)
// Did this code successfully run on Leetcode : Yes
// Any problem you faced while coding this : No
"""


class Node:
    def __init__(self, key, val):
        self.key = key
        self.val = val
        self.prev = None
        self.next = None


class LRUCache:
    def __init__(self, capacity: int):
        self.dummy = Node(-1, -1)
        self.tail = Node(-1, -1)
        self.dummy.next = self.tail
        self.tail.prev = self.dummy
        self.d = {}
        self.capacity = capacity

    def removeNode(self, node):
        node.prev.next = node.next
        node.next.prev = node.prev

    def addToHead(self, node):
        node.next = self.dummy.next
        node.prev = self.dummy
        self.dummy.next = node
        node.next.prev = node

    def get(self, key: int) -> int:
        if key not in self.d:
            return -1
        self.removeNode(self.d[key])
        self.addToHead(self.d[key])
        return self.d[key].val

    def put(self, key: int, value: int) -> None:

        if key not in self.d:
            if self.capacity > len(self.d):
                newNode = Node(key, value)
                self.d[key] = newNode
                self.addToHead(self.d[key])
            else:
                newNode = Node(key, value)
                del self.d[self.tail.prev.key]
                self.removeNode(self.tail.prev)
                self.d[key] = newNode
                self.addToHead(self.d[key])
        else:
            self.d[key].val = value
            self.removeNode(self.d[key])
            self.addToHead(self.d[key])

# Your LRUCache object will be instantiated and called as such:
# obj = LRUCache(capacity)
# param_1 = obj.get(key)
# obj.put(key,value)