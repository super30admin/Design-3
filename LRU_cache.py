# Time complexity --> o(capacity)
# space complexity --> o(1)
// Did this code successfully run on Leetcode : Yes
// Any problem you faced while coding this : None


// Your code here along with comments explaining your approach:
Description for each functionality is given as comments as below.
# creating a node for a doubly linked list
class Nodelist:
    def __init__(self,key,val):
        self.key=key
        self.val=val
        self.prev=None
        self.next=None
class LRUCache(object):
# since each LRUcache has a fixed amount of capacity set we are creating a dictionary to maintain the capacity of the cache and used two dummy nodes head and tail to traverse in o(1)
    def __init__(self, capacity):
        """
        :type capacity: int
        """
        self.capacity=capacity
        self.ele=dict()
        self.head=Nodelist(-1,-1)
        self.tail=Nodelist(-1,-1)
        self.head.next=self.tail
        self.tail.prev=self.head
        # creating this method to add to the start of the linked list when any chnages are made in o(1)
    def addtostartlist(self,node):
        node.prev=self.head
        node.next=self.head.next
        self.head.next=node
        node.next.prev=node
        # removes the node at any position in o(1) when the node is given
    def removefromlinkedlist(self,node):
        node.next.prev=node.prev
        node.prev.next=node.next
        
# retrives the info if present in the dict else we return -1 and whenever this call is made we remove this node from the list and add it to the start of the linked list indicating it is recently added
    def get(self, key):
        """
        :type key: int
        :rtype: int
        """
        if key not in self.ele.keys():
            return -1
        node=self.ele[key]
        self.removefromlinkedlist(node)
        self.addtostartlist(node)
        return node.val
# inserts or updates the node value in the doubly linked list
    def put(self, key, value):
        """
        :type key: int
        :type value: int
        :rtype: None
        """
        # if node is in the doubly linked list we update the value and add it to the start of DLL indicating it is recently used.
        if key in self.ele.keys():
            node=self.ele[key]
            node.val=value
            self.removefromlinkedlist(node)
            self.addtostartlist(node)
        else:
            # when inserting the node we r checking the capacity of the dict if it is equal we delete the least recently used node both from the linked list and the dict.
            if len(self.ele)==self.capacity:
                tailprev=self.tail.prev
                del self.ele[tailprev.key]
                self.removefromlinkedlist(tailprev)
            node1=Nodelist(key,value)
            self.addtostartlist(node1)
            self.ele[key]=node1
        


# Your LRUCache object will be instantiated and called as such:
# obj = LRUCache(capacity)
# param_1 = obj.get(key)
# obj.put(key,value)