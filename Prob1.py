class LRUCache(object):
    #Time: O(1)
    # Space: O(capacity)
    class Node(object):
        def __init__(self,key,val):
            self.prev = None
            self.nxt = None
            self.key = key
            self.val = val
    def __init__(self, capacity):
        """
        :type capacity: int
        """
        self.lruMap = {}
        self.capacity = capacity
        self.head = self.Node(-1,-1)
        self.tail = self.Node(-1,-1)
        self.head.nxt = self.tail
        self.tail.prev = self.head
    def remove(self,node):
        node.prev.nxt = node.nxt
        node.nxt.prev = node.prev
        # node.nxt = None
        # node.prev = None
        
    def addToHead(self,node):
        node.nxt = self.head.nxt
        node.prev = self.head
        self.head.nxt = node
        node.nxt.prev = node
        
    def get(self, key):
        """
        :type key: int
        :rtype: int
        """
        if key not in self.lruMap:
            return -1
        node = self.lruMap[key]
        self.remove(node)
        self.addToHead(node)
        return node.val

    def put(self, key, value):
        """
        :type key: int
        :type value: int
        :rtype: None
        """
        if key in self.lruMap:
            node = self.lruMap[key]
            node.val = value
            self.remove(node)
            self.addToHead(node)
        else:
            if len(self.lruMap) == self.capacity:
                del self.lruMap[self.tail.prev.key]
                self.remove(self.tail.prev)
            newNode = self.Node(key,value)
            self.addToHead(newNode)
            self.lruMap[key] = newNode
                
                
    
# Your LRUCache object will be instantiated and called as such:
# obj = LRUCache(capacity)
# param_1 = obj.get(key)
# obj.put(key,value)
