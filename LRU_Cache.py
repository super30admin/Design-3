class Node:
    def __init__(self,key,value):
        self.next = None
        self.prev = None
        self.key = key
        self.value = value

class LRUCache:

    def __init__(self, capacity: int):
        self.capacity = capacity
        self.hashmap = {}        
        self.head,self.tail = Node(-1,-1),Node(-1,-1)
        
        self.head.next=self.tail
        self.tail.prev=self.head
        
    
    def addTohead(self,node):
        node.next = self.head.next
        node.prev = self.head
        self.head.next.prev = node
        self.head.next = node
    
    
    def removeNode(self,node):      

        node.prev.next = node.next
        node.next.prev = node.prev
    
    def get(self, key: int) -> int:
        
        if key in self.hashmap:
            node = self.hashmap[key]
            self.removeNode(node)
            self.addTohead(node)
            return node.value
        else:
            return -1
        

    def put(self, key: int, value: int) -> None:
       
        #if key present
        if  key in self.hashmap:
            node = self.hashmap[key]
            self.removeNode(node)
            self.addTohead(node)
            node.value = value
        else:
            
            node = Node(key,value)
            
            #if capcity has not reached maximum
            if len(self.hashmap) == self.capacity:
                
                tailprev = self.tail.prev
                self.removeNode(tailprev)
                del self.hashmap[tailprev.key]
                self.hashmap[key] = node
                self.addTohead(node)
                
            else:                
                        
                self.hashmap[key] = node
                self.addTohead(node)    


# Your LRUCache object will be instantiated and called as such:
# obj = LRUCache(capacity)
# param_1 = obj.get(key)
# obj.put(key,value)
