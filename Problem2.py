#Time Complexity - All the functions have O(1) time complexity
#Space Complexity - The cache overall has O(n) space based on the n as capacity, but all functions have O(1) space
#Works on leetcode - yes
#Approach - We use a combination of dictionary and doubly linkedlist(DLL) to construct LRU cache. Dictionary saves the key and node pair while
#the DLL keeps track of the order of data. The front of the DLL keeps track of the Most Recently used element while tail of DLL keeps track
#of the Least Recently used element
class Node:
    def __init__(self,k,v):
        self.key = k
        self.val = v
        self.prev = None
        self.next = None
        
class LRUCache:

    def __init__(self, capacity: int):
        self.capacity = capacity
        self.dic = dict()
        self.head = Node(-1, -1)
        self.tail = Node(-1, -1)
        self.head.next = self.tail
        self.tail.prev = self.head
    
    
    def remove(self, node):
        # node.prev.next = node.next
        # node.next.prev = node.prev
        p = node.prev
        n = node.next
        p.next = n
        n.prev = p

    def addtohead(self,node):
        n = self.head.next
        n.prev = node
        self.head.next = node
        node.next = n
        node.prev = self.head
        
        
    def get(self, key: int) -> int:
        
        if key in self.dic:
            n = self.dic[key]
            self.remove(n) 
            self.addtohead(n)
            print("Getting :",key)
            return n.val
        return -1
        

    def put(self, key: int, value: int) -> None:
        if key in self.dic:
            self.remove(self.dic[key])
            n = Node(key, value)
            self.addtohead(n)
            self.dic[key] = n
        else:
            if len(self.dic) == self.capacity :
                k = self.tail.prev
                self.remove(k)
                del self.dic[k.key]
            n = Node(key, value)
            self.addtohead(n)
            self.dic[key] = n
        