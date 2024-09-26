class LRUCache(object):

    def __init__(self, capacity):
        """
        :type capacity: int
        """
        self.hashMap={}
        self.head=Node(-1,-1)
        self.tail=Node(-1,-1)
        self.head.next=self.tail
        self.tail.prev=self.head
        self.capacity=capacity
        
    def removeNode(self,node):
        node.next.prev=node.prev
        node.prev.next=node.next
    def addToHead(self,curr):
        curr.next=self.head.next
        curr.prev=self.head
        self.head.next=curr
        curr.next.prev=curr
    def get(self, key):
        """
        :type key: int
        :rtype: int
        """
        if key not in self.hashMap:
            return -1
        else:
            curr=self.hashMap[key]
            self.removeNode(curr)
            self.addToHead(curr)
            return curr.val
    def put(self, key, value):
        """
        :type key: int
        :type value: int
        :rtype: None
        """
        if key in self.hashMap:
            curr=self.hashMap[key]
            curr.val=value
            self.removeNode(curr)
            self.addToHead(curr)
        else:
            if self.capacity==len(self.hashMap):
                tailPrev=self.tail.prev
                self.removeNode(tailPrev)
                del self.hashMap[tailPrev.key]
            curr=Node(key,value)
            self.addToHead(curr)
            self.hashMap[key]=curr
class Node:
    def __init__(self,key,val):
        self.key=key
        self.val=val
        self.prev=None
        self.next=None
    
        

# Your LRUCache object will be instantiated and called as such:
# obj = LRUCache(capacity)
# param_1 = obj.get(key)
# obj.put(key,value)