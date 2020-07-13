class Node:
    
    """
        Student :  Shahreen Shahjahan Psyche
        Time : deleteNode : O(1)
               addToHead : O(1)
               get : O(1)
               put : O(1)
        Space : O(N) , where N is the size of hashmap and the LinkedList which can bee higheest equivalent to the capacity
    
    """
    
    
    def __init__(self, key, value):
        self.key = key
        self.val = value
        self.next = None
        self.prev = None

class LRUCache:
    def __init__(self, capacity: int):
        
        self.capacity = capacity
        self.records = {}
        
        self.head = Node(-1,-1)
        self.tail = Node(-1,-1)
        self.head.next = self.tail
        self.tail.prev = self.head
    
    def deleteNode(self, node: Node) -> Node:
        
        # this method deletes a node
        node.prev.next = node.next 
        node.next.prev = node.prev
       
        node.next = None
        node.prev = None
        
        return node
            
    def addToHead(self, node: Node) -> None:
        # this method adds the node to the head
        node.next = self.head.next
        node.prev = self.head
        self.head.next = node
        node.next.prev = node
        
        
    def get(self, key: int) -> int:
        
        # this method checks whetheer the key is in hashmap. If yees, then it returns the value, otherwisee returns -1
        if key in self.records.keys():
            tempNode = self.records[key]
            tempNode = self.deleteNode(tempNode)
            self.addToHead(tempNode)
            return self.records[key].val
        else:
            return -1
        
    def put(self, key: int, value: int) -> None:
        
        # checking whether the key already eexists! If it exists theen it updates the value and puts the key, val in the head
        if key in self.records.keys():
            tempNode = self.records[key]
            tempNode.val = value
            tempNode = self.deleteNode(tempNode)
            self.addToHead(tempNode)
        # if thee keey is not in hashmap
        else:
            # so it is checking whether the capacity is full or not
            if len(self.records) == self.capacity:
                tempNode = self.tail.prev
                del self.records[tempNode.key]
                tempNode = self.deleteNode(tempNode)
            newNode = Node(key, value)
            self.addToHead(newNode)
            self.records[key] = newNode

                
                
            
            
        
        
        
        
# Your LRUCache object will be instantiated and called as such:
# obj = LRUCache(capacity)
# param_1 = obj.get(key)
# obj.put(key,value)
