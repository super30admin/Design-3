#Time complexity : O(1)
#Space complexity : O(C) #C is the capacity
class DoublyLLNode:
    def __init__(self,key=None,val=None):
        self.key = key
        self.val = val
        self.prev = None
        self.next = None
class LRUCache:

    def __init__(self, capacity: int):
        self.capacity =capacity
        self.map = {}
        self.head = DoublyLLNode(-1,-1)
        self.tail = DoublyLLNode(-1,-1)
        self.head.next = self.tail
        self.tail.prev = self.head
        
    def removenode(self,node):
        node.prev.next = node.next
        node.next.prev = node.prev
    
    def addnode(self,node):
        node.prev = self.head
        node.next = self.head.next
        node.next.prev = node
        self.head.next = node

    def get(self, key: int) -> int:
        if key in self.map:
            getnode = self.map[key]
            getnode_val = getnode.val
            self.removenode(getnode)
            self.addnode(getnode)
            return getnode_val
        else:
            return -1
        

    def put(self, key: int, value: int) -> None:
        
        if key in self.map:
            updatenode = self.map[key]
            updatenode.val = value
            self.removenode(updatenode)
            self.addnode(updatenode)
            
        else:
            if len(self.map)==self.capacity: #cache is full remove last node
                rmnode = self.tail.prev
                self.removenode(rmnode)
                self.map.pop(rmnode.key)

            newnode = DoublyLLNode(key,value)
            self.addnode(newnode)
            self.map[key] = newnode
        
    

# Your LRUCache object will be instantiated and called as such:
# obj = LRUCache(capacity)
# param_1 = obj.get(key)
# obj.put(key,value)