"""
146. LRU Cache
Time Complexity - O(1) for all operations"
Space Complexity - O(n) i.e capacity or length of hashmap
To attain O(1) time complexity we are using a hashmap that stores key and pairs by reference to node of doubly linked list
Then we are using DLL to traverse and perform add and remove functionalities based on operation performed and capacity of LRU
Have used add function that manipulates tail and head pointer to put a node in DLL
Have defined a remove function that deletes node from DLL
To perform get function:
remove key node and then add again node and return value for that key
To perform put operation:
if key present in hashmap delete node add new node
else
create node and add in DLL
also keep checking capacity if exceeds delete head.next node
"""
class node:
        def __init__(self,key,val):
            self.key = key
            self.val = val
            self.prev = None
            self.next = None
class LRUCache: 

    def __init__(self, capacity: int):
        self.hashmap = {}
        self.head = node(0,0)
        self.tail = node(0,0)
        self.size = 0
        self.capacity = capacity
        self.head.next = self.tail
        self.tail.prev = self.head
        
    def add(self,node):
        temp = self.tail.prev
        temp.next = node
        node.next = self.tail
        self.tail.prev = node
        node.prev = temp
    
    def remove(self,node):
        before = node.prev
        after = node.next
        before.next = after
        after.prev = before 
        
        
    def get(self, key: int) -> int:
        
        if key not in self.hashmap:
            return -1
        else:
            curr = self.hashmap[key]
            self.remove(curr)
            self.add(curr)
            return curr.val
        

    def put(self, key: int, value: int) -> None:
        if key in self.hashmap:
            curr = self.hashmap[key]
            self.remove(curr)
            self.hashmap.pop(key)            
        if len(self.hashmap) == self.capacity:
            self.hashmap.pop(self.head.next.key)
            self.remove(self.head.next)
            
            
        curr = node(key,value)
        self.hashmap[key] = curr
        self.add(curr)

# Your LRUCache object will be instantiated and called as such:
# obj = LRUCache(capacity)
# param_1 = obj.get(key)
# obj.put(key,value)