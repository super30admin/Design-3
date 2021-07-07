# Leet code - LRU cache - 146 - https://leetcode.com/problems/lru-cache/
# Time complexity - get - put -O(1)
# Space complexity -O(capacity)
# Approach - Double Linked list along with Hash Map - Here in each node  we store  key and Node data.
# When we put the key value if in case the capacity is reached then we remove the node that is least recently used (i.e right side) where prev to the tail. While inserting the node we insert at the left side where head is present. 



class Node:
    def __init__(self,key,val):
        self.key=key
        self.val=val
        self.prev=None
        self.next=None
    
class LRUCache(object):

    def __init__(self, capacity):
        """
        :type capacity: int
        """
        self.head= Node(None,None)
        self.tail=Node(None,None)
        self.head.next=self.tail
        self.tail.prev=self.head
        self.head.prev=None
        self.tail.next=None
        self.size=capacity
        self.map={}

    def get(self, key):
        """
        :type key: int
        :rtype: int
        """
        if key in self.map:
            node=self.map[key]
            self.deletenode(key)
            self.addnode_left(node)
            return node.val # here we can get key value if it is present
        return -1
    
        

    def put(self, key, value): # we are inserting at next to head
        """
        :type key: int
        :type value: int
        :rtype: None
        """
        if key in self.map:
            self.deletenode(key)
        elif len(self.map)>=self.size:
            node=self.tail.prev
            self.deletenode(node.key)
        node=Node(key,value)
        self.addnode_left(node)
            
    def addnode_left(self,node):
        self.map[node.key]=node
        nxt=self.head.next
        self.head.next=node
        node.prev=self.head
        node.next=nxt
        nxt.prev=node
        
        
        
    def deletenode(self,key): 
        node=self.map[key]
        #node.prev.next=node.next
        #node.next.prev=node.prev
        
        prev=node.prev
        next=node.next
        prev.next=next
        next.prev=prev
        
        del self.map[key]
        


# Your LRUCache object will be instantiated and called as such:
# obj = LRUCache(capacity)
# param_1 = obj.get(key)
# obj.put(key,value)


# when node is used instead of key in  deleting method
class Node:
    def __init__(self,key,val):
        self.key=key
        self.val=val
        self.prev=None
        self.next=None
    
class LRUCache(object):

    def __init__(self, capacity):
        """
        :type capacity: int
        """
        self.head= Node(None,None)
        self.tail=Node(None,None)
        self.head.next=self.tail
        self.tail.prev=self.head
        self.head.prev=None
        self.tail.next=None
        self.size=capacity
        self.map={}

    def get(self, key):
        """
        :type key: int
        :rtype: int
        """
        
        if key in self.map:
            node=self.map[key]
            self.deletenode(node)
            self.addnode_left(node)
            return node.val # here we can get key value if it is present or not present in the hash map
        return -1
    
        

    def put(self, key, value):
        """
        :type key: int
        :type value: int
        :rtype: None
        """
        
        if key in self.map:
            self.deletenode(self.map[key])
        elif len(self.map)>=self.size:
            node=self.tail.prev
            self.deletenode(node)
            del self.map[node.key]
        node=Node(key,value)
        self.addnode_left(node)
        self.map[key]=node
            
    def addnode_left(self,node):
        
        nxt=self.head.next
        self.head.next=node
        node.prev=self.head
        node.next=nxt
        nxt.prev=node
        
        
        
    def deletenode(self,node):
        node.prev.next=node.next
        node.next.prev=node.prev
        
        


# Your LRUCache object will be instantiated and called as such:
# obj = LRUCache(capacity)
# param_1 = obj.get(key)
# obj.put(key,value)