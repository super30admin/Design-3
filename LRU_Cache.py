class Node:
    def __init__(self,key,value):
        self.key=key
        self.val=value
        self.next=None
        self.prev=None
class LRUCache:

    def __init__(self, capacity: int):
        self.capacity = capacity
        self.head=Node(-1,-1)
        self.tail=Node(-1,-1)
        self.head.next=self.tail
        self.tail.next = self.head
        self.curCapacity = 0
        self.mp = {}
        
    
    def addNode(self,node):
        # Add node next to head
        cur=self.head.next
        self.head.next=node
        cur.prev=node
        node.next = cur
        node.prev=self.head
        
    def removeNode(self,node):        
        node.prev.next=node.next
        node.next.prev=node.prev
        
    def moveToHead(self,node):
        self.removeNode(node)
        self.addNode(node)
        
    def removeTail(self):
        temp=self.tail.prev
        self.removeNode(temp)
        return temp
        

    def get(self, key: int) -> int:
        
        if key not in self.mp:
            return -1
        node=self.mp[key]
        self.moveToHead(node)
        return node.val   

    def put(self, key: int, value: int) -> None:
        
        if key in self.mp:
            node = self.mp[key]
            node.val = value
            self.moveToHead(node)
        else:
            if self.curCapacity == self.capacity:
                node = self.removeTail()
                del self.mp[node.key]
                self.curCapacity-=1
            
            cur=Node(key,value)
            self.addNode(cur)
            self.curCapacity+=1
            self.mp[key]=cur
                
        


# Your LRUCache object will be instantiated and called as such:
# obj = LRUCache(capacity)
# param_1 = obj.get(key)
# obj.put(key,value)