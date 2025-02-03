# Time Complexity - O(1)
# Space Complexity - O(capacity)

"""
Approach:
Create a Node class with key, value, next and prev pointers
Create a doubly linked list with head and tail nodes
Create a map to store the key and node object
When get or put is called, add the node next to the head of the linked list
Remove a node from the linked list if the capacity is full and add the new node next to the head of the linkedlis
"""


class LRUCache:

    class Node:

        def __init__(self, key, value):
            self.key = key
            self.value = value
            self.next = None
            self.prev = None

    def __init__(self, capacity: int):

        self.head = self.Node(-1, -1)
        self.tail = self.Node(-1, -1)
        self.head.next = self.tail
        self.tail.prev = self.head
        self.map = {}
        self.capacity = capacity

    def addToHead(self, nodeObj):

        nodeObj.next = self.head.next
        self.head.next.prev = nodeObj
        nodeObj.prev = self.head
        self.head.next = nodeObj

    def removeNode(self, nodeObj):

        nodeObj.next.prev = nodeObj.prev
        nodeObj.prev.next = nodeObj.next

    def get(self, key: int) -> int:

        if not key in self.map:
            return -1

        self.removeNode(self.map[key])
        self.addToHead(self.map[key])

        return self.map[key].value

    def put(self, key: int, value: int) -> None:

        if key in self.map:
            self.removeNode(self.map[key])
            self.addToHead(self.map[key])
            self.map[key].value = value
            return None

        if len(self.map) == self.capacity:

            tailObj = self.tail.prev
            self.removeNode(tailObj)
            del self.map[tailObj.key]

        nodeObj = self.Node(key, value)
        self.addToHead(nodeObj)
        self.map[key] = nodeObj
