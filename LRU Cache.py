class LRUCache:
    #Approach: Hashmap with doubly linked list
    #Time Complexity: O(1) for both, get and put
    #Space Complexity: O(capacity) // both data structrues are O(capacity)
    
    def __init__(self, capacity: int):
        self.head = Node(-1, -1)        #dummy
        self.tail = Node(-1, -1)        #dummy
        self.head.next = self.tail
        self.tail.prev = self.head
        
        self.capacity = capacity
        self.map = {}

    def get(self, key: int) -> int:
        if key not in self.map:
            return -1
        
        node = self.map[key]
        self.removeNode(node)
        self.addtoHead(node)
        
        return node.val

    def put(self, key: int, value: int) -> None:
        if key in self.map:
            node = self.map[key]
            node.val = value
            self.removeNode(node)
            self.addtoHead(node)
            
        else:
            if len(self.map) == self.capacity:
                tailPrev = self.tail.prev
                self.removeNode(tailPrev)
                del self.map[tailPrev.key]
                
            node = Node(key, value)
            self.addtoHead(node)
            self.map[key] = node
            
    def addtoHead(self, node):
        node.next = self.head.next
        node.prev = self.head
        self.head.next = node
        node.next.prev = node
        return
        
    def removeNode(self, node):
        node.prev.next = node.next
        node.next.prev = node.prev
        return

class Node:
    def __init__(self, key, val):
        self.key = key
        self.val = val
        self.prev = None
        self.next = None


# Your LRUCache object will be instantiated and called as such:
# obj = LRUCache(capacity)
# param_1 = obj.get(key)
# obj.put(key,value)