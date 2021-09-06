"""
Time Complexity : O(1)
Space Complexity : O(capacity)
Did this code successfully run on Leetcode : Yes
Any problem you faced while coding this : No
"""
class Node:
    def __init__(self):
        self.key = 0
        self.value = 0
        self.prev = None
        self.next = None
class LRUCache:
    def __init__(self, capacity: int):
        self.head = Node()
        self.tail = Node()
        self.capacity = capacity
        self.map = {}
        self.head.next = self.tail
        self.tail.prev = self.head
    # Fucntion to add the node to the head of doubly linked list
    def addToHead(self, node):
        node.next = self.head.next
        node.prev = self.head
        node.next.prev = node
        self.head.next = node
    # Function to remove the node from the tail of doubly linked list
    def removeNode(self, node):
        node.next.prev = node.prev
        node.prev.next = node.next
    # If the key is in the map we update the node containing the key
    # in a linked list to the head of the node to be able to keep its use updated
    # TC(get) : O(1)
    # TC(get) : O(capacity)
    def get(self, key: int) -> int:
        if key in self.map:
            node = self.map[key]
            self.removeNode(node)
            self.addToHead(node)
            return node.value
        return -1
    # If the key already exists then we updated the value in the hashmap and 
    # move the key node to the head of the linked list as it is recently used
    # else we add the node to the hashmap and key node to the doubly linked list
    # TC(put) : O(1)
    # SC(put) : O(capacity)
    def put(self, key: int, value: int) -> None:
        if key in self.map:
            node = self.map[key]
            node.value = value
            self.removeNode(node)
            self.addToHead(node)
        else:
            if self.capacity == len(self.map):
                tailPrev = self.tail.prev
                self.removeNode(tailPrev)
                del self.map[tailPrev.key]
            newNode = Node()
            newNode.key = key
            newNode.value = value
            self.map[key] = newNode
            self.addToHead(newNode)

# Your LRUCache object will be instantiated and called as such:
# obj = LRUCache(capacity)
# param_1 = obj.get(key)
# obj.put(key,value)