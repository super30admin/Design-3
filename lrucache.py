class LRUCache:
    
    class Node:
        
        def __init__(self,key,val):
            self.key = key
            self.val= val
            
        

    def __init__(self, capacity: int):
        self.capacity = capacity
        self.head = self.Node(-1,-1)
        self.tail = self.Node(-1,-1)
        self.head.next = self.tail
        self.tail.prev = self.head
        self.map = {}
        
    def removeNode(self,node):
        node.next.prev = node.prev
        node.prev.next = node.next
        
    def addNode(self,node):
        node.next = self.head.next
        node.prev = self.head
        self.head.next = node
        node.next.prev = node
        
    def get(self, key: int) -> int:
        if key not in self.map:
            return -1
        node = self.map[key]
        self.removeNode(node)
        self.addNode(node)
        return node.val
    
    def put(self, key: int, value: int) -> None:
        if key in self.map:
            node = self.map[key]
            node.val = value
            self.removeNode(node)
            self.addNode(node)
        else:  
            if len(self.map) == self.capacity:
                node = self.tail.prev
                self.removeNode(node)
                self.map.pop(node.key)
                
            newNode = self.Node(key,value)
            self.map[key] = newNode
            self.addNode(newNode)
                
            
       
       
        

        
        