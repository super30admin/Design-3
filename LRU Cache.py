'''
Solution - Doubly LinkedList 
- Maintain head and tail nodes. 
- when a node gets called, detach from the current position (if already there) and add 
  to the head. 
- if size of cache runs over the capacity, remove the node from the tail
Time Complexity: 
    - get: O(1)
    - put: O(1)
Space Complexity:
    - O(N), N = capacity  
'''

class Node:
    def __init__(self,key=None,value=None):
        self.key = key
        self.value = value
        self.next = None
        self.prev = None

class LRUCache:

    def __init__(self, capacity: int):
        self.capacity = capacity
        self.head = Node() # dummy head node
        self.tail = Node() # dummy tail node
        self.head.next = self.tail
        self.tail.prev = self.head

        self.cache = {} # {key - Node}

    def get(self, key: int) -> int:
        if key not in self.cache:
            return -1
        
        node = self.cache[key] # node that is requested.
        self.__detachNode(node) # detach from the current position in the list
        self.__update2head(node) # add to the head, as this becomes most recont one

        return node.value

    def put(self, key: int, value: int) -> None:
        if key not in self.cache:
            node = Node(key,value) # create the new node
            self.cache[key] = node # add the node to cache
        else:
            node = self.cache[key] # extract the node that is requested
            node.value = value # update the value of node in case it's changed
            self.__detachNode(node) # detach from the current position in the list
        
        self.__update2head(node) # add the node to front/head, as this becomes most recent one
        
        if len(self.cache)>self.capacity: # max capacity reached
            self.__removefromtail() # remove the least recently used node. 

    def __detachNode(self,node):
        node.prev.next = node.next
        node.next.prev = node.prev 
        node.next = None
        node.prev = None
    
    def __update2head(self,node):
        node.next = self.head.next
        self.head.next = node
        node.next.prev = node
        node.prev = self.head
    
    def __removefromtail(self):
        node2remove = self.tail.prev
        node2remove.prev.next = self.tail
        self.tail.prev = node2remove.prev
        node2remove.prev = None
        node2remove.next = None
        self.cache.pop(node2remove.key,"Key not found")
        

  

# Your LRUCache object will be instantiated and called as such:
# obj = LRUCache(capacity)
# param_1 = obj.get(key)
# obj.put(key,value)