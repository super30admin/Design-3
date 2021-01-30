# No dummy nodes.
# Time, Space Complexity: O(1)
class LRUCache:
    
    class node():
        def __init__(self,key,val, next=None,prev=None):
            self.key = key
            self.val = val
            self.next = next
            self.prev = prev

    def __init__(self, capacity: int):
        self.capacity = capacity
        self.hashMap = {}
        self.size = 0
        self.head=None
        self.tail=None
    
    # delete a node in a doubly LL
    
    def deleteNode(self,node):
        # store node's prev
        # store node's next
        temp1 = node.prev
        temp2 = node.next
        if(temp2!=None):
            temp2.prev = temp1
        else:
            # last node deleted
        
            self.tail = temp1
        if(temp1!=None):
            temp1.next = temp2
        else:
            # First node deleted
            self.head = temp2
        self.size-=1
        return
    
    def addToFront(self,node):
        if(self.head==None):
            # No element, first element added
            self.head = node
            self.tail = node
            self.hashMap[node.key] = self.head
        else:
            # forgot to add node.prev = None
            node.prev = None
            node.next = self.head
            self.head.prev = node
            self.head = node
            self.hashMap[node.key] = self.head
        
        self.size+=1
        return
    
    def printQueue(self):
        temp = self.head
        while(temp!=None):
            print(temp.key,temp.val)
            temp = temp.next

    def get(self, key: int) -> int:
        if(key in self.hashMap.keys()):
            temp = self.hashMap[key]
            # delete node
            self.deleteNode(temp)
            # add to front
            self.addToFront(temp)
            return self.hashMap[key].val
        else:
            return -1
            

    def put(self, key: int, value: int) -> None:
        if(self.get(key)!=-1):
            # node will already be added to the front
            self.head.val = value
        else:
            if(self.head==None):
                self.addToFront(LRUCache.node(key,value))
            else:
                if(self.size==self.capacity):
                    # remove last element if ll reached the capacity
                    del self.hashMap[self.tail.key]
                    self.deleteNode(self.tail)
                
                # add node to the front
                h = LRUCache.node(key,value)
                self.addToFront(h)
                
                


# Your LRUCache object will be instantiated and called as such:
# obj = LRUCache(capacity)
# param_1 = obj.get(key)
# obj.put(key,value)
