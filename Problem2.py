# https://leetcode.com/problems/lru-cache/description/

# Time Complexity: O(1)
# Space Complexity: O(1)
# Did this code successfully run on Leetcode : Yes
# Any problem you faced while coding this : No

class LRUCache:

    class Node:
        def __init__(self, key, value):
            self.key = key
            self.value = value
            self.next = None  # Pointer to the next node
            self.prev = None  # Pointer to the previous node

    def __init__(self, capacity: int):
        self.capacity = capacity
        self.hm = {}

        self.head = self.Node(-1,-1)
        self.tail = self.Node(-1,-1)

        self.head.next = self.tail
        self.tail.prev = self.head

    def addToHead(self, node):
        node.next = self.head.next
        node.prev = self.head
        self.head.next = node
        node.next.prev = node

    def removeNode(self, node):
        node.prev.next = node.next
        node.next.prev = node.prev

    def get(self, key: int) -> int:
        if key not in self.hm:
            return -1
        node = self.hm[key]
        node = self.hm[key]
        self.removeNode(node)
        self.addToHead(node)
        return node.value
        
    def put(self, key: int, value: int) -> None:
        if key in self.hm:
            node = self.hm[key]
            node.value = value
            self.removeNode(node)
            self.addToHead(node)
        else:
            if len(self.hm) == self.capacity:
                lastNode = self.tail.prev
                self.removeNode(lastNode)
                self.hm.pop(lastNode.key)
            newNode = self.Node(key, value)
            self.addToHead(newNode)
            self.hm[key] = newNode

# Your LRUCache object will be instantiated and called as such:
# obj = LRUCache(capacity)
# param_1 = obj.get(key)
# obj.put(key,value)