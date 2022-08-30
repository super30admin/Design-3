# Space complexity : O(capacity)
# Leetcode : Solved and submitted

# declaring a class for doubly linked list, with key, value, prev and next
class Node:
    def __init__(self,key,value):
        self.key = key
        self.val = value
        self.prev = None
        self.next = None
        
class LRUCache:
     # function to add a node to the start of the linked list, here we are using head as the recently accessed nodes
    def add_to_head(self, node):
        node.next = self.head.next
        node.prev = self.head
        
        self.head.next = node
        node.next.prev = node
    
    # removed a node from either tail or any position
    # tail is used as the least recently used elements which we can pop in order to make space for new incoming key and value pairs
    def remove(self, node):
        node.next.prev = node.prev
        node.prev.next = node.next
     
   # initializing the default values of all variables and declaring a hashmap to store the values in cache, also start of head and tail of doubly
  # linked list
    def __init__(self, capacity: int):
        self.cache = {}
        self.size = 0
        self.capacity = capacity
        self.head = Node(-1,-1)
        self.tail = Node(-1,-1)
        
        self.head.next = self.tail
        self.tail.prev = self.head
    # In this function, we do not the key in the hashmap, then simply return -1, but if present, then move the element to the head which is the recently
    # accessed element and return val of that node for that key
    def get(self, key: int) -> int:
        if key not in self.cache:
            return -1
        node = self.cache[key]
        self.remove(node)
        self.add_to_head(node)
        return node.val
    
    # In order to place the element in the hashmap, we have to check if the key is not present in the hashmap, then we check for the capacity if does not
    # excced the limit, then we can simply place the element, but if not, then we'd have to remove an element from the tail and add our new key,
    # value to the head
    def put(self, key: int, value: int) -> None:
        if key not in self.cache:
            if len(self.cache) == self.capacity:
                node = self.tail.prev
                self.remove(node)
                self.cache.pop(node.key)
            newNode = Node(key,value)
            self.cache[key] = newNode
            self.add_to_head(newNode) 
        else:
           # if we do have that element in the hashmap, then we update it's value and also move it to the head
            self.cache[key].val = value
            node = self.cache[key]
            self.remove(node)
            self.add_to_head(node)
        
# Your LRUCache object will be instantiated and called as such:
# obj = LRUCache(capacity)
# param_1 = obj.get(key)
# obj.put(key,value)
