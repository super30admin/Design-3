"""
Time Complexity : O(1) 
Space Complexity : O(capacity) 
Did this code successfully run on Leetcode : Yes
Any problem you faced while coding this : No


Your code here along with comments explaining your approach
We will use hashmap to store keys of the nodes with values as the reference to the nodes. This will help
in checking existence of the keys in O(1). For putting, we are using a doubly linked list. This will help in 
removal and addition of nodes in O(1) time again.
"""


class DoublyListNode(object):
    def __init__(self, key, val):
        self.key = key
        self.val = val
        self.prev = None
        self.next = None


class LRUCache:

    def __init__(self, capacity: int):
        self.capacity = capacity
        self.dictt = {}
        self.head = DoublyListNode(-1, -1)
        self.tail = DoublyListNode(-1, -1)
        self.head.next = self.tail
        self.tail.prev = self.head

    def get(self, key: int) -> int:
        if key not in self.dictt:
            return -1
        node = self.dictt[key]
        self.remove(node)
        self.addToHead(node)
        return node.val

    def remove(self, node):
        node.prev.next = node.next
        node.next.prev = node.prev

    def addToHead(self, node):
        node.next = self.head.next
        node.prev = self.head
        self.head.next = node
        node.next.prev = node

    def put(self, key: int, value: int) -> None:
        if key in self.dictt:
            self.remove(self.dictt[key])

        tempnode = DoublyListNode(key, value)
        self.addToHead(tempnode)
        self.dictt[key] = tempnode

        if len(self.dictt) > self.capacity:
            node = self.tail.prev
            self.remove(node)
            del self.dictt[node.key]
