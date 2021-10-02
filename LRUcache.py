#Time Complexity : O(1)
#Space Complexity : O(1) 
# Did this code successfully run on Leetcode : Yes
#Any problem you faced while coding this : No




class LRUCache:
    
    class Node:
        key = 0
        val = 0
        nextp = None
        prev = None
    
        def __init__(self, key, val):
            self.key = key
            self.val = val
    hmap = {}
    head = None
    tail = None
    capacity = 0
    
    def __removeNode__(self, node):
        node.prev.nextp = node.nextp
        node.nextp.prev = node.prev

    def __add_head__(self, node):
        node.nextp = LRUCache.head.nextp
        node.prev = LRUCache.head
        LRUCache.head.nextp = node
        node.nextp.prev = node

    def __init__(self, capacity: int):
        LRUCache.hmap = {}
        LRUCache.head = self.Node(-1, -1)
        LRUCache.tail = self.Node(-1, -1)
        LRUCache.head.nextp = LRUCache.tail
        LRUCache.tail.prev = LRUCache.head
        LRUCache.capacity = capacity

    def get(self, key: int) -> int:
        if not key in LRUCache.hmap.keys():
            return -1
        node = LRUCache.hmap[key]
        self.__removeNode__(node)
        self.__add_head__( node)
        return node.val

    def put(self, key: int, value: int) -> None:
        if key in LRUCache.hmap.keys():
            node = LRUCache.hmap[key]
            node.val = value
            self.__removeNode__(node)
            self.__add_head__(node)
        else:
            if LRUCache.capacity == len(LRUCache.hmap):
                last_node = LRUCache.tail.prev
                self.__removeNode__(last_node)
                del LRUCache.hmap[last_node.key]
            node = self.Node(key, value)
            self.__add_head__(node)
            LRUCache.hmap[key] = node


# Your LRUCache object will be instantiated and called as such:
# obj = LRUCache(capacity)
# param_1 = obj.get(key)
# obj.put(key,value)