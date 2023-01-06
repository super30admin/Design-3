#Time Complexity :  O(1) for get, put operations
#Space Complexity :  O(n)
#Did this code successfully run on Leetcode : Yes

#code along with comments 
class Node:                             #initializing the node class with all variables
    def __init__(self, key, val):
        self.key = key
        self.val = val
        self.prev = None
        self.next = None

class LRUCache:
    def __init__(self, capacity: int):
        self.capacity = capacity
        self.hashmap = {}                 #creating hashmap with key as integer: node as val       
        
        #left is LRU and right is Most recently used
        self.head = Node(0,0)
        self.tail = Node(0,0)
        self.head.next = self.tail
        self.tail.prev = self.head
    
    #inserting new node to the right so that it is the most recently used
    def insert(self, node):  
        prev, nxt = self.tail.prev, self.tail
        prev.next = nxt.prev = node
        node.prev, node.next = prev, nxt
        
    #removing the node from the list
    def remove(self, node):
        node.next.prev = node.prev
        node.prev.next = node.next
    
    def get(self, key: int) -> int:
        if key in self.hashmap:
            self.remove(self.hashmap[key])    #we remove it first
            self.insert(self.hashmap[key])    #then insert it back so that is is most recent
            return self.hashmap[key].val      #return the value of the key
        
        return -1                           #if key does not exist we return -1

    def put(self, key: int, value: int) -> None:
        if key in self.hashmap:               #if key already exists in hashmap
            self.remove(self.hashmap[key])    #we first remove it
        self.hashmap[key] = Node(key, value)  #create a new node and then insert it back to hashmap
        self.insert(self.hashmap[key])        #insert it back to DLL
        
        if len(self.hashmap) > self.capacity:
            lru = self.head.next
            self.remove(lru)                  #remove LRU node from the list
            del self.hashmap[lru.key]         #delete the key,value from hashmap