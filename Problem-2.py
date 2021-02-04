#Time Complexity :O(1)
#Space Complexity :O(1) if we avaoid hashmap as its the primary data structure 
#Did this code successfully run on Leetcode :yes
#Any problem you faced while coding this : No
class LRUCache(object):
    class Node():
        value=None
        key=None
        next=None
        prev=None
        def __init__(self,key,value):
            self.key=key
            self.value=value
    size=None
    hmap=None
    
    def removeNode(self,node):
        node.next.prev=node.prev
        node.prev.next=node.next
    
    def makeHead(self,node):
        node.next=self.head.next
        node.prev=self.head
        self.head.next=node
        node.next.prev=node
    
    def __init__(self, capacity):
        """
        :type capacity: int
        """
        self.size=capacity
        self.hmap={}
        self.head=self.Node(-1,-1)
        self.tail=self.Node(-1,-1)
        self.head.next=self.tail
        self.tail.prev=self.head

    def get(self, key):
        """
        :type key: int
        :rtype: int
        """
        if(key in self.hmap):
            self.removeNode(self.hmap[key])
            self.makeHead(self.hmap[key])
            return self.hmap[key].value
        return -1
        

    def put(self, key, value):
        """
        :type key: int
        :type value: int
        :rtype: None
        """
        node=self.Node(key,value)
    
        if(key in self.hmap):
            self.hmap[key].value=value
            self.removeNode(self.hmap[key])
            self.makeHead(self.hmap[key])
        else:
            if(len(self.hmap)==self.size):
                self.hmap.pop(self.tail.prev.key)
                self.removeNode(self.tail.prev)
                
            self.makeHead(node)
            self.hmap[key]=node
        
