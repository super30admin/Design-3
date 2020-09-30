# Time Complexity : O(1) valid combination
# Space Complexity : O(n) size of keys in map
# Did this code successfully run on Leetcode : Yes
# Any problem you faced while coding this : No

#create LL separately and store the reference to list in the map for easy access
# 1->2->3  {1: ref(1), 2: ref(2)}
class Node(object):
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
        self.res = {}
        self.head = Node(-1,-1)
        self.tail = Node(-1,-1)
        self.head.next = self.tail
        self.tail.prev = self.head
        self.capacity = capacity

        

    def get(self, key):
        """
        :type key: int
        :rtype: int
        """
        if key in self.res:
            node = self.res[key]
            self.remove(node)
            self.add(node)
            return node.value
        return -1
        
        

    def put(self, key, value):
        """
        :type key: int
        :type value: int
        :rtype: None
        """
        
        if key in self.res:
            self.remove(self.res[key])
        node = Node(key, value)
        self.add(node)
        self.res[key] = node
        if len(self.res) > self.capacity:
            n = self.head.next
            self.remove(n)
            del self.res[n.key]
            
#         if key not in self.res:
#             node = Node(key, value)
#             self.res[key] = node
#         else:
#             node = self.res[key]
#             node.value = value
#             print node
#             self.remove(node)
#             self.add(node)
        
#         if len(self.res) > self.capacity:
#             n = self.head.next
#             print n
#             self.remove(n)
#             del self.res[n.key]
        
    
    def add(self, node):
        node.next = self.tail
        node.prev = self.tail.prev
        node.prev.next = node
        self.tail.prev = node
    
    def remove(self, node):
        n = node.next
        p = node.prev
        n.prev = p
        p.next = n
            
        