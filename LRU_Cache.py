# Time Complexity: O(1) for each operation
# Space Complexity: O(n) where n is the capacity. We use this space for hashmap and Doubly Linked List
class Node:
    def __init__(self, key, value):
        self.key = key
        self.value = value
        self.next = None
        self.prev = None
        
class LRUCache:

    def __init__(self, capacity: int):
        self.h = {}
        self.head = Node(-1,-1)
        self.tail = Node(-1,-1)
        self.head.next = self.tail
        self.tail.prev = self.head # Creating 2 dummy nodes in doubly linked list
        self.capacity = capacity
    
    def removeNode(self,node):
        node.next.prev = node.prev
        node.prev.next = node.next
    
    def addToHead(self,node):
        node.next = self.head.next
        node.prev = self.head
        self.head.next = node
        node.next.prev = node

    def get(self, key: int) -> int:
        if key not in self.h.keys():
            return -1
        node = self.h[key]
        self.removeNode(node)
        self.addToHead(node)
        return node.value
        

    def put(self, key: int, value: int) -> None:
        if key in self.h.keys():
            node = h[key]
            node.value = value
            self.removeNode(node)
            self.addToHead(node)
        else:
            if len(self.h) == self.capacity:
                node = self.tail.prev
                self.removeNode(node)
                self.h.pop(node.key)
            newNode = Node(key,value)
            self.addToHead(newNode)
            self.h[key] = newNode


# Your LRUCache object will be instantiated and called as such:
# obj = LRUCache(capacity)
# param_1 = obj.get(key)
# obj.put(key,value)