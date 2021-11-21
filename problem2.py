#Time O(1), space O(n)

#Creating LinkedList
class ListNode:
    def __init__(self,key,value):
        self.key=key
        self.value=value
        self.prev=None
        self.next = None

class LRUCache:

#Initializing node
    def __init__(self, capacity: int):
        self.dict=dict()
        self.capacity = capacity
        self.head = ListNode(0,0)
        self.tail =ListNode(-1,-1)
        self.head.next = self.tail
        self.tail.previous=self.head
        

#Remove, insert and get the value if present
    def get(self, key: int) -> int:
        
        if key in self.dict:
            node = self.dict[key]
            self.remove(node)
            self.insert(node)
            return node.value
        
        return -1
        
##Remove, insert and update the value if present otherwise add next to head 
    def put(self, key: int, value: int) -> None:
        
        if key in self.dict:
            node = self.dict[key]
            self.remove(node)
            self.insert(node)
            node.value=value
        else:
            if len(self.dict)>=self.capacity:
                toremove =self.tail.prev
                self.remove(toremove)
                del self.dict[toremove.key]
            node = ListNode(key,value)
            self.dict[key]= node 
            self.insert(node)

    
    def remove(self,node):
        node.prev.next=node.next
        node.next.prev=node.prev
        
    def insert(self,node):
        node.next=self.head.next
        node.prev=self.head
        self.head.next=node
        node.next.prev=node
        

