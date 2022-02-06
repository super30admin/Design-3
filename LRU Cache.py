# Time Complexity :
# TC: O(1)

# Space Complexity :
# SC: O(N)

# Did this code successfully run on Leetcode :
# Yes

# Any problem you faced while coding this :
# No

# Your code here along with comments explaining your approach

# I have used a hashmap of type key --> node pair to make searching of node in DLL O(1)
# I have used doubly linked list to make addition or deletion of a node O(1)
# The procedure is explained in the comments of code below

class LRUCache:
    
    # TC: O(1)
    # SC: O(N)
    
    # Creating doubly LL node class
    class Node:
        def __init__(self, key, val):
            self.key = key
            self.val = val
            self.prev = None
            self.next = None
    
    # Function to add node at the head of the doubly LL
    # We will keep the most recently used to head
    def addToHead(self, node):
        node.next =  self.head.next
        node.prev =  self.head
        self.head.next =  node
        node.next.prev = node
    
    # Function to remove a node from doubly LL
    def remove(self, node):
        node.prev.next = node.next
        node.next.prev = node.prev
    
    
    def __init__(self, capacity: int):
        self.capacity = capacity
        self.hashMap = dict()
        self.head = self.Node(-1, -1)
        self.tail = self.Node(-1, -1)
        self.head.next = self.tail
        self.tail.prev = self.head

    def get(self, key: int) -> int:
        if key not in self.hashMap: return -1
        # Fetch node from hashmap
        node = self.hashMap.get(key)
        # Remove node from its position
        self.remove(node)
        # Add node to head --> make it most recently used
        self.addToHead(node)
            
        return node.val


    def put(self, key: int, value: int) -> None:
        if key in self.hashMap:
            # Fetch node from hashmap
            node = self.hashMap.get(key)
            node.val = value
            # Remove node from its position
            self.remove(node)
            # Add node to head --> make it most recently used
            self.addToHead(node)
        else:
            if len(self.hashMap) == self.capacity:
                # Remove least recently used node that is at tail from D LL
                tailPrev = self.tail.prev
                self.remove(tailPrev)
                # Remove least recently used node that is at tail from hashmap
                self.hashMap.pop(tailPrev.key)
                
            newNode = self.Node(key, value)
            # Add node to head --> make it most recently used
            self.addToHead(newNode)
            # add to hashmap
            self.hashMap[key] = newNode
                

# Your LRUCache object will be instantiated and called as such:
# obj = LRUCache(capacity)
# param_1 = obj.get(key)
# obj.put(key,value)