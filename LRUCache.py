"""
HasMap is used to get the value in O(1). The key thing is to maintaining an object as a value in hashMap to get its reference in constant time and another important thing is to use the doubly linked list to access the previous and next node of the curr Node.

Time Complexity: O(1) for both get() and put() function
Space Complexity: O(1) for both get() and put() function. Though we have used hashMap, it will not be considered while calculating space complexity as it is declared in constructor and it is not affecting end user.

Accepted on LeetCode: Yes
"""
from collections import defaultdict


class Node:
    def __init__(self, key, val, prev=None, next=None):
        self.key = key
        self.val = val
        self.prev = prev
        self.next = next


class LRUCache:

    def __init__(self, capacity: int):
        self.hashMap = defaultdict(Node)
        self.head = Node(-1, -1)
        self.tail = Node(-1, -1)
        self.head.next = self.tail
        self.tail.prev = self.head
        self.capacity = capacity

    def addToHead(self, node):
        # Head -> Most Recently used
        node.next = self.head.next
        node.prev = self.head
        self.head.next = node
        node.next.prev = node

    def removeNode(self, node):
        # Tail->Least Recently used
        node.next.prev = node.prev
        node.prev.next = node.next
        # node.next = None
        # node.prev = None

    def get(self, key: int) -> int:
        if key not in self.hashMap:
            return -1
        node = self.hashMap[key]
        self.removeNode(node)
        self.addToHead(node)  # Most Recently used
        return node.val

    def put(self, key: int, value: int) -> None:
        if key in self.hashMap:
            node = self.hashMap[key]
            node.val = value  # Update the val
            self.removeNode(node)  # Remove to make it most recently used
            self.addToHead(node)
        else:
            # Check if capacity is full
            if self.capacity == len(self.hashMap):
                nodeToBeRemoved = self.tail.prev
                self.removeNode(nodeToBeRemoved)
                self.hashMap.pop(nodeToBeRemoved.key)
            newNode = Node(key, value)
            self.addToHead(newNode)
            self.hashMap[key] = newNode


# Your LRUCache object will be instantiated and called as such:
# obj = LRUCache(capacity)
# param_1 = obj.get(key)
# obj.put(key,value)
