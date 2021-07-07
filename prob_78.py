#LRU Cache
class Node:
    def __init__(self,key=None,val=None):
        self.key_val=(key,val)
        #self.val=val
        self.prev=None
        self.next=None

class LRUCache(object):

    def __init__(self, capacity):
        """
        :type capacity: int
        """
        self.head= Node()
        self.tail=Node()
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
            return node.key_val[1] # Getting key value if it is present
        return -1



    def put(self, key, value):
        """
        :type key: int
        :type value: int
        :rtype: None
        """
        if key in self.map: #if key already present, then we delete it in the duble linked list and create a new one
            self.deletenode(key)
        elif len(self.map)>=self.size: #if key not present in map but max size of cache reached
            node=self.tail.prev
            self.deletenode(node.key_val[0])
        node=Node(key,value)
        self.addnode_left(node)

    def addnode_left(self,node):
        self.map[node.key_val[0]]=node
        nxt=self.head.next
        self.head.next=node
        node.prev=self.head
        node.next=nxt
        nxt.prev=node



    def deletenode(self,key):
        node=self.map[key]
        prev=node.prev
        next=node.next
        prev.next=next
        next.prev=prev
        del self.map[key] #del in map as well
