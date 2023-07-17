"""
Problem : 2

Time Complexity : O(1)
Space Complexity : O(n)

Did this code successfully run on Leetcode : Yes
Any problem you faced while coding this : No

Initializing a hashmao to store the key and pointer to the node of key to access in constant time
To get a value of a specific key, using hmap to access the node, if node present, returning the key, remivong the node from current position
and adding next to head, as it is recently used, else returning -1
to put the value for a key, checking if it already exists, if yes, changing the value, removing the node's current connection and adding next
to head, as it is currently used, if not present, and capacity is full, the node closest to tail is the LRU node, removing its connections, deleting its key from hash map,
and adding new node with key and value next to the head pointer.


"""
# LRU Cache

class Node(object):
    def __init__(self,key,val):
        self.prev=None
        self.next=None
        self.val=val
        self.key=key

class LRUCache(object):
    def addToHead(self,curr):
        curr.prev=self.head
        curr.next=self.head.next
        self.head.next=curr
        curr.next.prev=curr

    def removeNode(self,curr):
        curr.next.prev=curr.prev
        curr.prev.next=curr.next

    def __init__(self, capacity):
        """
        :type capacity: int
        """
        self.capacity=capacity
        self.head=Node(-1,-1)
        self.tail=Node(-1,-1)
        self.hmap={}
        self.head.next=self.tail
        self.tail.prev=self.head


    def get(self, key):
        """
        :type key: int
        :rtype: int
        """
        if key in self.hmap:
            node=self.hmap[key]
            self.removeNode(node)
            self.addToHead(node)
            return node.val
        else:
            return -1
        

    def put(self, key, value):
        """
        :type key: int
        :type value: int
        :rtype: None
        """
        if key in self.hmap:
            node=self.hmap[key]
            
            # self.hmap[key]=node
            self.removeNode(node)
            self.addToHead(node)
            node.val=value
        else:
            if len(self.hmap)==self.capacity:
                toRemove=self.tail.prev
                self.removeNode(toRemove)
                self.hmap.pop(toRemove.key)
                
        
            node=Node(key,value)
            self.addToHead(node)
            self.hmap[key]=node
                


# Your LRUCache object will be instantiated and called as such:
# obj = LRUCache(capacity)
# param_1 = obj.get(key)
# obj.put(key,value)