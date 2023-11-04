# // Time Complexity :O(1)
# // Space Complexity :O(1)
# // Did this code successfully run on Leetcode :Yes
# // Any problem you faced while coding this :No

# we use a doubly linked list along with hashmap to keep tarck of the values in LRU. we keep updating the DLL according to the most used and least used items.

class Node:
    def __init__(self,key,val):
        self.key=key
        self.val=val
        self.next=None
        self.prev=None

class LRUCache(object):
   

    def __init__(self, capacity):
        """
        :type capacity: int
        """
        self.hmap={}
        self.capacity=capacity
        self.head=Node(None,None)
        
        self.tail=Node(None,None)
        self.head.next=self.tail
        self.tail.prev=self.head
    
    def removen(self,node):
        node.next.prev=node.prev
        node.prev.next=node.next

    def addtohead(self,node):
        node.next=self.head.next
        node.prev=self.head
        self.head.next=node
        node.next.prev=node

    def get(self, key):
        """
        :type key: int
        :rtype: int
        """
        if(key not in self.hmap):
            return -1
        node=self.hmap[key]
        self.removen(node)
        self.addtohead(node)
        return node.val

        

    def put(self, key, value):
        """
        :type key: int
        :type value: int
        :rtype: None
        """
        if(key in self.hmap):
            node=self.hmap[key]
            node.val=value
            self.removen(node)
            self.addtohead(node)
        else:
            if(self.capacity==len(self.hmap)):
                # remove tail
                tailprev=self.tail.prev
                self.removen(tailprev)
                del self.hmap[tailprev.key]
            node=Node(key,value)
            self.addtohead(node)
            self.hmap[key]=node


        


# Your LRUCache object will be instantiated and called as such:
# obj = LRUCache(capacity)
# param_1 = obj.get(key)
# obj.put(key,value)