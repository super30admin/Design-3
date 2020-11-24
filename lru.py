# Time Complexity: O(1) - get, put
# Space Complexity: O(n)
# Approach: Use a Doubly Linked List and Hashmap to keep track of elements - more comments in-line

class ListNode:
    def __init__(self, key, val):
        self.key = key
        self.val = val
        self.next = None
        self.prev = None
        
class LRUCache(object):

    def __init__(self, capacity):
        """
        :type capacity: int
        """
        # initialize a hashmap, sentinel head, tail nodes
        self.hashMap = {}
        self.head = ListNode(-1,-1)
        self.tail = ListNode(-1,-1)
        self.capacity = capacity
        self.head.next = self.tail
        self.tail.prev = self.head
        
    def deleteNode(self, node):
        # Function to delete a node from DLL, given the node reference
        node.prev.next = node.next
        node.next.prev = node.prev
        
    def addToHead(self, node):
        # Function to add a node to the head pointer
        node.next = self.head.next
        node.prev = self.head
        self.head.next = node
        node.next.prev = node
        
    def get(self, key):
        """
        :type key: int
        :rtype: int
        """
        # Key not in cache, return -1
        if key not in self.hashMap:
            return -1
        
        # Get the node
        node = self.hashMap[key]
        self.deleteNode(node)
        self.addToHead(node)
        return node.val

    def put(self, key, value):
            """
            :type key: int
            :type value: int
            """
            # If key exists, update it's value and add it to head
            if key in self.hashMap:
                node = self.hashMap[key]
                node.val = value
                self.deleteNode(node)
                self.addToHead(node)
            else:
                # Key doesn't exist, create a new node
                node = ListNode(key, value)
                # If capacity is reached, evict the tail node - least recently used
                if self.capacity == len(self.hashMap):
                    tailPrev = self.tail.prev
                    self.deleteNode(tailPrev)
                    del self.hashMap[tailPrev.key]
                # Add new node to head
                self.addToHead(node)
                self.hashMap[key] = node
                    
                
                    
                    
           


# Your LRUCache object will be instantiated and called as such:
# obj = LRUCache(capacity)
# param_1 = obj.get(key)
# obj.put(key,value)