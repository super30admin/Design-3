class LL():
    def __init__(self, key, value):
        self.key=key
        self.value=value
        self.next=None
        self.prev=None

# class singleLL():
#     def __init__(self, key, value):
#         self.key=key
#         self.value=value
#         self.next=None
        
class LRUCache:
    """LRUCache using singly linked list and hashmap---Time Limit Exceeded
    Time complexity-O(n) as for deletion need to iterate till the previous of the deletion element
    Space complexity-O(n)"""
#     def __init__(self, capacity: int):
#         self.capacity=capacity
#         self.head=singleLL(-1,-1)
#         self.tail=singleLL(-1,-1)
#         self.head.next=self.tail
#         self.map={}
        
#     def get(self, key: int) -> int:
#         if key not in self.map:
#             return -1
#         val=self.map[key]
#         curr=self.head
#         while curr:
#             if curr.next==val:
#                 curr.next=curr.next.next
#                 break
#             curr=curr.next
#             # if curr.next.key==-1:
#             #     val.next=curr.next
#             #     curr.next=val
#         val.next=self.head.next
#         self.head.next=val
#         return val.value
        
        
        
#     def put(self, key: int, value: int) -> None:
#         if key in self.map:
#             curr=self.head
#             while curr.next:
#                 if curr.next.key==key:
#                     curr.next=curr.next.next
#                     break
#                 curr=curr.next
#             Node=singleLL(key, value)
#             Node.next=self.head.next
#             self.head.next=Node
#             self.map[key]=Node
#         else:
#             if self.capacity==len(self.map):
#                 curr=self.head
#                 while curr.next and curr.next.next:
#                     if curr.next.next.key==-1:
#                         self.map.pop(curr.next.key)
#                         curr.next=curr.next.next
#                         break
#                     curr=curr.next
#             Node=singleLL(key, value)
#             Node.next=self.head.next
#             self.head.next=Node
#             self.map[key]=Node
                
            
                
                
        
    
    """LRUCacheImplementation using array and hashmap
    Time complexity-O(n) as in worst case, deletion takes O(n)
    Space Complexity-O(n)"""
#     def __init__(self, capacity: int):
#         self.arr=[]
#         self.capacity=capacity
#         self.map={}
  
#     def get(self, key: int) -> int:
#         if key not in self.map:
#             return -1
#         val=self.map[key]
#         self.arr.remove((key, val))
#         self.arr.append((key, val))
#         return val
        
        
#     def put(self, key: int, value: int) -> None:
#         if key in self.map:
#             self.arr.remove((key,self.map[key]))
#             self.arr.append((key, value))
#             self.map[key]=value
#         else:
#             if self.capacity==len(self.map):
#                 (delkey, delval)=self.arr.pop(0)
#                 self.map.pop(delkey)
#             self.arr.append((key, value))
#             self.map[key]=value
            

    def __init__(self, capacity: int):
        self.capacity=capacity
        self.head=LL(-1,-1)
        self.tail=LL(-1, -1)
        self.head.next=self.tail
        self.tail.prev=self.head
        self.map={}
        
        
    def addtoHead(self, Node):
        Node.next=self.head.next
        Node.prev=self.head
        self.head.next=Node
        Node.next.prev=Node
        
        
    
    def DelNode(self, Node):
        Node.prev.next=Node.next
        Node.next.prev=Node.prev
        
        
    def get(self, key: int) -> int:
        """LRuCache Implementation using doubly LL and Hashmap
        O(1) time complexity
        O(n) space complexity"""
        if key not in self.map:
            return -1
        else:
            Node=self.map[key]
            self.DelNode(Node)
            self.addtoHead(Node)
        return Node.value
        
    
    def put(self, key: int, value: int) -> None:
        """LRuCache Implementation using doubly LL and Hashmap
        O(1) time complexity
        O(n) space complexity"""
        if key in self.map:
            Node=self.map[key]
            Node.value=value
            self.DelNode(Node)
            self.addtoHead(Node)
            self.map[key]=Node
        else:
            if self.capacity==len(self.map):
                print(self.tail.prev.key, key, self.head.next.key, self.head.next.next.key)
                self.map.pop(self.tail.prev.key)
                self.DelNode(self.tail.prev)
            Node=LL(key, value)
            self.addtoHead(Node)
            self.map[key]=Node
            
                
                
        
        


# Your LRUCache object will be instantiated and called as such:
# obj = LRUCache(capacity)
# param_1 = obj.get(key)
# obj.put(key,value)