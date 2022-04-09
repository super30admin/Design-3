    # Time Complexity : O(1)
    # Space Complexity : O(N)

class Node(object):

    #   Node which is part of a Doubly-Linked-List having a key, value, nextNode reference and previousNode reference

    def __init__(self, key, value):
        self.key = key
        self.value = value
        self.next = None
        self.prev = None

class LRUCache(object):

    def __init__(self, capacity):
        """
        :type capacity: int
        """
        self.capacity = capacity
        self.head = Node(-1, -1)        #   dummy head
        self.tail = Node(-1, -1)        #   dummy tail
        self.head.next = self.tail      #   head's next is initially pointing to tail
        self.tail.prev = self.head      #   tail's prev is initially pointing to head
        self.hash_set = {}              #   key:Node
        
    
    def remove(self, node):
        node.prev.next = node.next
        node.next.prev = node.prev
        return
        
    def addNodetoHead(self, node):
        node.prev = self.head
        node.next = self.head.next
        
        self.head.next = node
        node.next.prev = node
        return
        
        
    
    
    def get(self, key):
        """
        :type key: int
        :rtype: int
        """
        if self.hash_set.get(key):
            node = self.hash_set.get(key)
            
            self.remove(node)
            self.addNodetoHead(node)
            
            return node.value
        return -1
        
        

    def put(self, key, value):
        """
        :type key: int
        :type value: int
        :rtype: None
        """
        if self.hash_set.get(key):
            self.hash_set.get(key).value = value
            node = self.hash_set[key]
            
            self.remove(node)
            self.addNodetoHead(node) 
        else:
            if self.capacity==len(self.hash_set):
                node = self.tail.prev
                self.remove(node) 
                self.hash_set.pop(node.key)  
                
            self.hash_set[key] = Node(key, value)
            node = self.hash_set[key]  
            self.addNodetoHead(node)

        return 
                
    
            


# Your LRUCache object will be instantiated and called as such:
# obj = LRUCache(capacity)
# param_1 = obj.get(key)
# obj.put(key,value)