# // Time Complexity :O(1) for D-LL and Tries 
# // Space Complexity :O(L) len of smap
# // Did this code successfully run on Leetcode :Yes
# // Any problem you faced while coding this :No

# create a doubly LL use 2 dummy nodes. create an add and remove function.
# Last element used will always be at the tail of the LL. Store mem pointers of keys in smap for O(1) search.
class Node:

    def __init__(self, key, val):           # doubly LL
        self.key = key
        self.value = val
        self.prev = None
        self.next = None

class LRUCache:

    def __init__(self, capacity: int):
        self.smap = {}
        self.cap = capacity
        self.head = Node(-1,-1)
        self.tail = Node(-1,-1)
        self.head.next = self.tail
        self.tail.prev = self.head
    
    def removeNode(self,curr):
        curr.prev.next = curr.next
        curr.next.prev = curr.prev
        curr.next = None
        curr.prev = None
    
    def addToHead(self,curr):
        curr.next = self.head.next
        curr.prev = self.head
        self.head.next.prev = curr
        self.head.next = curr

    def get(self, key: int) -> int:
        if key not in self.smap:            
            return -1
        node = self.smap[key]
        self.removeNode(node)
        self.addToHead(node)
        return node.value

    def put(self, key: int, value: int) -> None:
        if key in self.smap:                # key in smap? just change value
            node = self.smap[key]
            node.value = value
            self.removeNode(node)
        else:                               # not in smap? capacity or not?
            if len(self.smap) == self.cap:  # capacity exceeded? remove lru, remove lru loc mapping
                lru = self.tail.prev
                self.removeNode(lru)
                self.smap.pop(lru.key)      # pop lru nodes key
            node = Node(key,value)
            self.smap[key] = node           # store node location in smap
        self.addToHead(node)       

# Your LRUCache object will be instantiated and called as such:
# obj = LRUCache(capacity)
# param_1 = obj.get(key)
# obj.put(key,value)