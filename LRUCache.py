#Time Complexity : O(1)
#Space Complexity : O(capacity)
class Newnode:
    
    def __init__(self, key, val):
        self.key = key
        self.val = val
        self.prev = None
        self.next = None


class LRUCache(object):


    def __init__(self, capacity):
        """
        :type capacity: int
        """
        self.head = Newnode(-1, -1)
        self.tail = Newnode(-1, -1)
        self.hash = {}
        self.capacity = capacity
        self.head.next=self.tail
        self.tail.prev=self.head
    
    def addTohead(self,node):
        node.next=self.head.next
        node.prev=self.head
        self.head.next=node
        node.next.prev=node
    
    def removeNode(self,node):
        node.prev.next=node.next
        node.next.prev=node.prev
        

    def get(self, key):#return val
        """
        :type key: int
        :rtype: int
        """
        
        
        if key not in self.hash:
            return -1
        node = self.hash[key]
        self.removeNode(node)
        self.addTohead(node)
        return node.val
        
        
        

    def put(self, key, value):
        """
        :type key: int
        :type value: int
        :rtype: void
        """
        if key in self.hash:
            node = self.hash[key]
            node.val = value
            self.removeNode(node)
            self.addTohead(node)
        else:
            newnode = Newnode(key, value)
            if(self.capacity==len(self.hash)):
                #remove the last ele from LRU cache least recently user near tail
                tailprev=self.tail.prev
                self.removeNode(tailprev)
                del self.hash[tailprev.key]
            self.addTohead(newnode)
            self.hash[newnode.key]=newnode
        
        