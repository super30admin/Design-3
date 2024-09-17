"""
Time complexity: O(1)
Space complexity: O(N)
Compiled on leetcode: Yes
"""
class Node:
    # Doubly Linked List Node structure
    def __init__(self, key, value):
        self.previous = None
        self.next = None
        self.key = key
        self.value = value
        
        
class LRUCache:

    def __init__(self, capacity: int):
        self.hashmap = dict()
        # Constructing a doubly Linked List
        self.head = Node(None, None)
        self.tail = Node(None, None)
        self.head.next = self.tail
        self.tail.previous = self.head
        self.capacity = capacity
        
        
    def addToHead(self, node):
        node.previous = self.head
        node.next = self.head.next
        self.head.next.previous = node
        self.head.next = node
        
    def removeFromList(self, node):
        node.previous.next = node.next
        node.next.previous = node.previous
        
        

    def get(self, key: int) -> int:
        if key not in self.hashmap:
            return -1
        else:
            node = self.hashmap[key]
            self.removeFromList(node)
            self.addToHead(node)
            return node.value
        

    def put(self, key: int, value: int) -> None:
        if key in self.hashmap:
            nodeToBeModified = self.hashmap[key]
            nodeToBeModified.value = value
            self.removeFromList(nodeToBeModified)
            self.addToHead(nodeToBeModified)
        else:  
            nodeToBeInserted = Node(key, value)
            self.hashmap[nodeToBeInserted.key] = nodeToBeInserted
            if len(self.hashmap) <= self.capacity: 
                self.addToHead(nodeToBeInserted)
            else:
                nodeToBeEvicted = self.tail.previous
                del self.hashmap[nodeToBeEvicted.key]
                self.removeFromList(nodeToBeEvicted)
                self.addToHead(nodeToBeInserted)



# Your LRUCache object will be instantiated and called as such:
# obj = LRUCache(capacity)
# param_1 = obj.get(key)
# obj.put(key,value)