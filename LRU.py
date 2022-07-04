"""Approach: Doubly LinkedList + HashMap
TC O(1) for get and put operations
SC O(Capacity)  at most 2xCapacity to store HashMap and DLL. But HashMap stores only keys, and values are just references.
"""
class DLinkNode:
    def __init__(self, key, value):
        self.key = key
        self.value = value
        self.next = self. prev = None
    
    def __str__(self):
        return str(self.key) + " " + str(self.value)
        
class LRUCache:

    def __init__(self, capacity: int):
        # set capacity and init empty map
        self.cap = capacity
        self.mp = {}
        # create dummy nodes
        self.head = DLinkNode(-1,-1)
        self.tail = DLinkNode(-1,-1)
        # make Dummy Nodes point to one another
        self.head.next = self.tail
        self.tail.prev = self.head
        
    
    def _remove_node(self,node):
        node.prev.next = node.next
        node.next.prev = node.prev
        node.next = node.prev = None
        
    def _insert_after_head(self,node):
        node.next = self.head.next
        node.prev = self.head
        node.next.prev = node
        self.head.next = node

    def _pop_tail(self):
        node = self.tail.prev
        node.prev.next = self.tail
        node.next.prev = node.prev
        node.next = node.prev = None
        return node
        
    def _move_to_head(self,node):
        self._remove_node(node)
        self._insert_after_head(node)
        
    def get(self, key: int) -> int:
        if key in self.mp:
            # retrieve node
            node = self.mp[key]
            # move this node to head as most recently used
            # this is 2 steps, remove it from wherever it is and move to head
            self._move_to_head(node)
            return node.value
        return -1
        

    def put(self, key: int, value: int) -> None:
        if key in self.mp:
            node = self.mp[key]
            node.value = value
            self._move_to_head(node)
        else:
            node = DLinkNode(key,value)
            if len(self.mp) == self.cap:
                del self.mp[self._pop_tail().key]
            self.mp[key] = node
            self._insert_after_head(node)
        


# Your LRUCache object will be instantiated and called as such:
# obj = LRUCache(capacity)
# param_1 = obj.get(key)
# obj.put(key,value)