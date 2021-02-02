# Time Complexity : O(1)
# Space Complexity : O(1)
# Did this code successfully run on Leetcode : Yes
# Any problem you faced while coding this : No


class Node:
    def __init__(self, key, val):
        self.key = key
        self.val = val
        self.next = None
        self.prev = None
        
class LRUCache(object):
    
    #https://www.youtube.com/watch?v=akFRa58Svug&ab_channel=TECHDOSE 

    def __init__(self, capacity):
        """
        :type capacity: int
        """
        self.capacity = capacity
        self.cache = {} # as dic
        self.head = Node(-1, -1) #node is called it automatically call init(which has key, val, next prev) as in constructor will be called automatically when the object is created.
        self.tail = Node(-1, -1) # set tail as -1 -1
        self.head.next = self.tail
        self.tail.prev = self.head   # make a connection
        

    def get(self, key):
        """
        :type key: int
        :rtype: int
        """
        result = -1
        if key in self.cache: # check in dic
            node = self.cache[key] # take key in node go to the key - " get address in node that is O(1)memory allocation" add key memory allocation to node so that I will get instant access of the value
            result = node.val # add node value in res 
            self.remove(node) #it's used remove and add it to the front of the head that is MRU
            self.add(node)
            
        return result
        

    def put(self, key, value):
        """
        :type key: int
        :type value: int
        :rtype: None
        """
        if key in self.cache:  #if key in dic
            node = self.cache[key] # in cache
            node.val = value # just update the value in node 
            self.remove(node) # remove since it's used recently 
            self.add(node) # add at the front (most recently used)
            
        elif len(self.cache) == self.capacity: # fit to the size if it's full
            node = self.tail.prev # remove lRC element
            self.remove(node) # Remove that node
            del self.cache[node.key] # delete in cache
            new_node = Node(key, value)  # create a new 
            self.add(new_node) # add new node next to head
            self.cache[key] = new_node # add in cache as well
            
        else:
            new_node = Node(key, value) # nothing just add at the front (head)
            self.add(new_node) 
            self.cache[key] = new_node
        
    
    def remove(self, node): 
        node.prev.next = node.next
        node.next.prev = node.prev
        
    
    def add(self, node): #most recently used                                                                                                   
        node.next = self.head.next
        node.prev = self.head
        node.next.prev = node
        self.head.next = node
