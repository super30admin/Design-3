# time complexity is o(1)
# space complexity is o(n), where n is be the number of put opeartions in the input.
class LRUCache:
    
    class DoubleLinkedList:
        def __init__(self, key, value):
            self.key = key
            self.value = value
            self.prev = None
            self.next = None
            

    def __init__(self, capacity: int):
        self.capacity = capacity
        self.head = self.DoubleLinkedList(0, 0)
        self.tail = self.DoubleLinkedList(0, 0)
        
        self.head.next = self.tail
        self.tail.prev = self.head
        
        self.d = dict()
        
    def addtohead(self, node):
        node.prev = self.head
        node.next = self.head.next
        self.head.next.prev = node
        self.head.next = node
        
    
    def removenode(self, node):
        node.prev.next = node.next
        node.next.prev = node.prev
        
        
        

    def get(self, key: int) -> int:  #o(1)
        if(key not in self.d):
            return -1
        self.removenode(self.d[key])
        self.addtohead(self.d[key])
        return self.d[key].value
        
    def put(self, key: int, value: int) -> None: #o(1)
        if(key not in self.d):
            if(len(self.d) < self.capacity):
                node = self.DoubleLinkedList(key, value)
                self.addtohead(node)
                self.d[key] = node
            else:
                temp = self.tail.prev
                self.removenode(temp)
                del self.d[temp.key]
                node = self.DoubleLinkedList(key, value)
                self.addtohead(node)
                self.d[key] = node
        else:
            self.removenode(self.d[key])
            self.addtohead(self.d[key])
            self.d[key].value = value
                
                
                
        


# Your LRUCache object will be instantiated and called as such:
# obj = LRUCache(capacity)
# param_1 = obj.get(key)
# obj.put(key,value)