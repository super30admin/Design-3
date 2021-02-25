# // Time Complexity : O(2N)
# // Space Complexity : O(1) to get and to put
# // Did this code successfully run on Leetcode : Yes
# // Any problem you faced while coding this : No


# // Your code here along with comments explaining your approach
# We use doubly Linked List and Hashmap for LRU
# we store the Node as value and number as key in hashmap
# we create head and tail node at start
# most used element will be at head and least used element is at tail
# We will have 4 functions -
# 1. get
# 2. put
# 3. remove(node)
# 4. addToHead(node)
# remove will remove node from linkedList
# add to head will add node at head.
# we move element to head if we GET that element
# new element is added at the head
# hashmap maintains the object of the node hence we can directly access the node location inside the LinkedList

class LRUCache:

    def __init__(self, capacity: int):
        #create head and tail node
        self.head = Node(-1,-1)
        self.tail = Node(-1,-1)
        self.head.next = self.tail
        self.tail.prev = self.head
        self.capacity = capacity
        self.hashmap = {}
        
    def remove(self,node):
        #remove node from its current location
        node.prev.next = node.next
        node.next.prev = node.prev
    
    def addToHead(self,node):
        # add node to head
        node.next = self.head.next
        node.prev = self.head
        self.head.next = node
        node.next.prev = node
        
        
    def get(self, key: int) -> int:
        #find if key in map and return -1 if not
        # if its in. remove that element in LL and append to head
        if key not in self.hashmap: return -1
        node = self.hashmap[key]
        self.remove(node)
        self.addToHead(node)
        return node.val
            

    def put(self, key: int, value: int) -> None:
        # check if its already in 
        # update and bring to front
        if key in self.hashmap:
            node = self.hashmap[key]
            node.val = value
            self.remove(node)
            self.addToHead(node)
        # if key is new
        # cehck the size of thr Cache if its exceeding reduce by removing least used element i.e. tail.prev
        else:
            node = Node(key, value)
            # remove tail.prev
            if len(self.hashmap) == self.capacity:
                del self.hashmap[self.tail.prev.key]
                self.remove(self.tail.prev)
            # add to head
            self.addToHead(node)
            self.hashmap[key] = node

class Node:
    def __init__(self, key, val):
        self.key = key
        self.val = val


# Your LRUCache object will be instantiated and called as such:
# obj = LRUCache(capacity)
# param_1 = obj.get(key)
# obj.put(key,value)