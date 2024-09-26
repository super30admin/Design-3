# if my cache reaches capacity, the least recently used element will be removed from the cache to add the new element(remove()).
#The new element is always added to the top of the cache and all the other subsequent elements are pushed below(add()). Thus the top element indicated the most recently used element.
#If any element present in the cache is accessed by either get or put method, then that element becomes the most recently used element and hence the cache needs to be updated in such a way that this element comes to the top of the cache(update()). 
#The functions mentioned above take care of these operations. 
#A doubly linked list was created where the next element to the head will be the most recently used element and the element just before the tail will be least recently used element. 
#This structure thus facilitated me to utilize the structure of doubly linked list and a hashmap to keep track of the elements added or removed from the cache to implement the LRU cache. 

#Time Complexity:O(n) because we have to traverse through the entire list to delete a node in between
#Space Complexity:O(1)
class Node:
    def __init__(self, k, v):
        self.key = k
        self.val = v
        self.prev = None
        self.next = None
class LRUCache:
    #Create a hashmap 
    def __init__(self, capacity: int):
        self.capacity=capacity
        self.hashMap=dict()
        self.head=Node(0,0)#creating dummy node
        self.tail=Node(0,0)#creating dummy tail node
        self.head.next=self.tail
        self.tail.prev=self.head
        #created [dummy head]<=>[dummy tail]
        
    def get(self, key: int) -> int:#O(1)
        if key in self.hashMap:
            n=self.hashMap[key]
            self.removeNode(n)
            self.addNode(n)
            return n.val
        return -1
            
        

    def put(self, key: int, value: int) -> None:#O(1)
        #If key is already existing in map
        if key in self.hashMap:
             self.removeNode(self.hashMap[key])
        n = Node(key, value)
        self.addNode(n)
        self.hashMap[key] = n
        if len(self.hashMap) > self.capacity:
            n = self.head.next
            self.removeNode(n)
            del self.hashMap[n.key]
    
    def removeNode(self, node):#O(1)
        node.next.prev = node.prev
        node.prev.next = node.next
        # p.next = n
        # n.prev = p

    def addNode(self, node):#O(1)
        #add a node to tail when we have reference to that node
   
        p = self.tail.prev
        p.next = node
        self.tail.prev = node
        node.prev = p
        node.next = self.tail


# Your LRUCache object will be instantiated and called as such:
# obj = LRUCache(capacity)
# param_1 = obj.get(key)
# obj.put(key,value)
