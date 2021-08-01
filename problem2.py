from collections import OrderedDict
class Node:
    def __init__(self,key,value,next=None,prev=None):
        self.key=key
        self.value=value
        self.next=next
        self.prev=prev
        
class LRUCache:
   #Done with doubly linkedist and dictionary
    def __init__(self, capacity: int):
        self.head=Node(0,0)
        self.tail=Node(0,0)
        self.head.next=self.tail
        self.tail.prev=self.head
        self.capacity=capacity
        self.store=dict()#Will store the nodes for easy access
   
    def get(self, key: int) -> int:
        
        if key in self.store:
            node=self.store[key]
            self.remove(node)
            self.add(node)
            return node.value
        return -1
    def put(self, key: int, value: int) -> None:
        if key in self.store:
            node=self.store[key]
            node.value=value
            self.remove(node)
        else:
            node=Node(key,value)
            
        self.add(node)
        self.store[key]=node
        
        if len(self.store)>self.capacity:
            v=self.tail.prev
            self.remove(v)
            del self.store[v.key]
 
    def remove(self,Node):
        pre=Node.prev
        pre.next=Node.next
        Node.next.prev=pre
    #We are adding into the Head, and while remove we will do from end
    def add(self,Node):
        cur=self.head.next
        self.head.next=Node
        Node.prev=self.head
        Node.next=cur
        cur.prev=Node
#Time all operation are O(1) 
#Space O(n)    
        
        
        


        
#     def __init__(self, capacity: int):
#         self.capacity=capacity
#         self.store=OrderedDict()
   
#     def get(self, key: int) -> int:
#         if key in self.store:
#             self.store.move_to_end(key,last=True)
#             return self.store[key]
#         return -1
#     def put(self, key: int, value: int) -> None:
#         if key not in self.store:
#             self.store[key]=value
#         else:
#             self.store.move_to_end(key,last=True)
#             self.store[key]=value
#         if len(self.store)>self.capacity:
#             self.store.popitem(last=False)
#     #Time all operation are O(1) but move_to_end will take 0(n)
#     #Space O(n)
        

# Your LRUCache object will be instantiated and called as such:
# obj = LRUCache(capacity)
# param_1 = obj.get(key)
# obj.put(key,value)
