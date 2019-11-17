#leetcode: Accepted
#timecomplexity: 0(1)
#no doubts
#code explianation: we are implementing a doubly linkedlist along with the hashmap. We have disctinoary of keys and the values being nodes of key,value node of doubly linkedlist

#we have two dummy nodes of head and tail and will be placing the most recently used values in head and the last recently used are near the tail so you can remove the node from the tail when the size of the list increases the capacity.

#we will be using a DLL because,  it is easy to remove a node from the middle but if we are using a queue(a continous memorly allocation) it will be difficult to remove a node from the moddle,

class DLLNode:
    def __init__(self,key,value):
        self.key=key
        self.value=value
        self.next=None
        self.prev=None
        
class LRUCache(object):    
    def __init__(self,capacity):          
        self.head=DLLNode(0,0)
        self.tail=DLLNode(0,0)
        self.capacity=capacity
        self.dict={}
        self.head.next=self.tail
        self.tail.prev=self.head
    
    def addNode(self,node):
        node.prev=self.head
        node.next=self.head.next
        self.head.next.prev=node
        self.head.next=node
        
    def RemoveNode(self, node):
        node.prev.next=node.next 
        node.next.prev=node.prev
        
    def popTail(self):
        element=self.tail.prev
        del self.dict[element.key]
        self.RemoveNode(element)
     
        
    def movetohead(self, node):
        self.RemoveNode(node)
        self.addNode(node)

    def get(self, key):
        if key in self.dict:  
            newnode=self.dict[key]
            self.movetohead(newnode) 
            return newnode.value
        return -1
            

    def put(self, key, value):
        if key in self.dict: 
            newnode=self.dict[key]
            newnode.value=value 
            self.movetohead(newnode)  
        else:
            new=DLLNode(key,value)
            self.dict[key]=new
            self.addNode(new)     
            
        if len(self.dict)>self.capacity:
            return(self.popTail())
            
            
            


# Your LRUCache object will be instantiated and called as such:
# obj = LRUCache(capacity)
# param_1 = obj.get(key)
# obj.put(key,value)