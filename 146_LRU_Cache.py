# Leetcode problem link : https://leetcode.com/problems/lru-cache/
# Time Complexity:    O(capacity)
# Space Complexity:   O(capacity)
#  Did this code successfully run on Leetcode : Yes
# Any problem you faced while coding this : No

# Your code here along with comments explaining your approach
'''
    Basic intution: Keep a singly linked list or queue to traverse the elements but add/remove is costly in case of get
    
    Optimized approach: Keep a doubly linked list with nodes equal to the capacity.
    Update the list to remove the element and insert at end as soon as get is performed on the ndoe
    Hashmap can be used to do optimized get and keeping pointer to the node instead of key
    

'''

class LRUNode:
    def __init__(self,key,val):
        self.key = key
        self.val = val
        self.prev = None
        self.next = None

class CacheList:
    def __init__(self):
        self.head = LRUNode(-1,None)
        self.tail = LRUNode(-1,None)
        self.tail.prev = self.head
        self.head.next = self.tail
    
    def _insertAtEnd(self,node):
        self.tail.prev.next = node
        node.prev = self.tail.prev
        self.tail.prev = node
        node.next = self.tail
    
    def _removeFromBegn(self):
        key = self.head.next.key
        self.head.next.next.prev = self.head
        self.head.next = self.head.next.next
        return key
    
    def _updateCache(self,node):
        node.prev.next = node.next
        node.next.prev = node.prev
        self._insertAtEnd(node)
        
class LRUCache:

    def __init__(self, capacity: int):
        self.capacity = capacity
        self.cache_map = {}
        self.cache_list = CacheList()

    def get(self, key: int) -> int:
        if key not in self.cache_map:
            return -1
        node = self.cache_map[key]
        self.cache_list._updateCache(node)
        return node.val        

    def put(self, key: int, value: int) -> None:
        if key in self.cache_map:
            node = self.cache_map[key]
            node.val = value
            self.cache_list._updateCache(node)
            return
        node = LRUNode(key,value)
        self.cache_map[key] = node
        self.cache_list._insertAtEnd(node)
        if self.capacity == 0:
            remKey = self.cache_list._removeFromBegn()
            del self.cache_map[remKey]
        else:
            self.capacity -= 1

# Your LRUCache object will be instantiated and called as such:
# obj = LRUCache(capacity)
# param_1 = obj.get(key)
# obj.put(key,value)