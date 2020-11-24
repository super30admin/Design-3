class DLL():
    def __init__(self):
        self.key = 0
        self.value = 0
        self.next = None
        self.prev = None
class LRUCache:
    def addNode(self, node):
        node.prev = self.head
        node.next = self.head.next
        self.head.next.prev = node
        self.head.next = node
        
    def __init__(self, capacity: int):
        self.map = {}
        self.capacity = capacity
        self.size = 0
        self.head = DLL()
        self.tail = DLL()
        self.head.next = self.tail
        self.tail.prev = self.head
        

    def get(self, key: int) -> int:
        node = self.map.get(key, None)
        
        if not node:
            #node is not there in the map
            return -1
     
        #update the position and then return 
        self.moveUp(node)
        return node.value

    def put(self, key: int, value: int) -> None:
        node = self.map.get(key)
        
        if not node:
            #you add the node here and increase the capacity
            #pop if needed
            
            #create DLL 
            newNode = DLL()
            newNode.value = value
            newNode.key = key
            
            #add node
            self.map[key] = newNode
            #pass newly created node to addNode helper function
            self.addNode(newNode)
            
            #capacity check, if yes pop tail
            self.size+=1
            if self.size>self.capacity:
                tailKey = self.popTail()
                del self.map[tailKey.key]
                self.size-=1
        else:
            #if node is present, we update it
            node.value = value
            self.moveUp(node)
    
    def popTail(self):
        lastNode = self.tail.prev
        self.removeNode(lastNode)
        return lastNode
    def removeNode(self, node):
        old = node.prev
        new = node.next
        old.next = new
        new.prev = old
        
    def moveUp(self, node):
        self.removeNode(node)
        self.addNode(node)
        
Time: O(1) 
Space : O(Capacity) 

# Your LRUCache object will be instantiated and called as such:
# obj = LRUCache(capacity)
# param_1 = obj.get(key)
# obj.put(key,value)