#  Time Complexity :  O(1) for get and put
#  Space Complexity : O(capacity)
#  Did this code successfully run on Leetcode : Yes
#  Three line explanation of solution in plain english : This LRUCache uses a hash map + doubly linked list to achieve O(1) get and put. The hash map stores keys mapped to nodes, while the doubly linked list keeps track of usage order, with the most recently used items near the head and least recently used near the tail. On access or update, a node is moved to the head; if capacity is exceeded, the least recently used node (just before the tail) is removed.

class LRUCache:
    # TC: O(1) for get and put  SC: O(capacity)
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

    def remove(self, node):
        node.prev.next = node.next
        node.next.prev = node.prev
        node.prev = None
        node.next = None

    def addToHead(self, node):
        node.prev = self.head
        node.next = self.head.next
        self.head.next = node
        node.next.prev = node

    def get(self, key: int) -> int:
        if key not in self.map:
            return -1
        node = self.map[key]
        self.remove(node)
        self.addToHead(node)
        return node.value        

    def put(self, key: int, value: int) -> None:
        if key in self.map:
            node = self.map[key]
            node.value = value
            self.remove(node)
            self.addToHead(node)
        else:
            if len(self.map) == self.capacity:
                lastNode = self.tail.prev
                self.remove(lastNode)
                del self.map[lastNode.key]
            newNode = self.Node(key, value)
            self.map[key] = newNode
            self.addToHead(newNode)