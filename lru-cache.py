# Time Complexity: O(n)
# Space Complexity: O(n)
class LRUCache:

    class Node:

        def __init__(self, key, val, prev, next):
            self.key = key
            self.val = val
            self.prev = prev
            self.next = next
    
    def __init__(self, capacity: int):
        self.capacity = capacity
        self.root = self.Node(None, None, None, None)
        self.tail = self.Node(None, None, self.root, None)
        self.root.next = self.tail
        self.lookup = {}

    def get(self, key: int) -> int:
        if key not in self.lookup:
            return -1
        node = self.lookup[key]
        self._moveToFront(node)
        return node.val

    def put(self, key: int, value: int) -> None:
        if key not in self.lookup:
            node = self.Node(key, value, None, None)
            self.lookup[key] = self._pushInFront(node)
        else:
            node = self.lookup[key]
            node.val = value
            self._moveToFront(node)
        if len(self.lookup) > self.capacity:
            node = self._popFromEnd()
            self.lookup.pop(node.key)

    def _pop(self, node):
        node.prev.next = node.next
        node.next.prev = node.prev
        return node

    def _popFromEnd(self):
        node = self.tail.prev
        return self._pop(node)

    def _pushInFront(self, node):
        node.prev = self.root
        node.next = self.root.next
        self.root.next = node
        node.next.prev = node
        return node

    def _moveToFront(self, node):
        popped = self._pop(node)
        return self._pushInFront(popped)
    
# # Time Complexity:
# # Space Complexity:

# class LRUCache(object):
#     class Node:
#         def __init__(self, key, val, prev, next):
#             self.key = key
#             self.val = val
#             self.prev = prev
#             self.next = next

#     def __init__(self, capacity):
#         """
#         :type capacity: int
#         """
#         self.capacity = capacity
#         self.head = self.Node(None, None, None, None)
#         self.tail = self.Node(None, None, self.head, None)
#         self.head.next = self.tail
#         self.dict = {}

    
#     def popFromTail(self):
#         node = self.tail.prev
#         node.next.prev = node.prev
#         node.prev.next = node.next

#     def pop(self, node):
#         node.prev.next = node.next
#         node.next.prev = node.prev
#         return node
    
#     def addToHead(self, node):
#         node.prev = self.head
#         node.next = self.head.next
#         self.head.next = node
#         node.next.prev = node
        
#     def moveToFront(self, node):
#         popped = self.pop(node)
#         self.addToHead(popped)

#     def get(self, key):
#         """
#         :type key: int
#         :rtype: int
#         """
#         if key not in self.dict:
#             return -1
#         node = self.dict[key]
#         self.moveToFront(node)
#         return node.val
        
#     def put(self, key, value):
#         """
#         :type key: int
#         :type value: int
#         :rtype: None
#         """        
#         if key not in self.dict:
#             node = self.Node(key, value, None, None)
#             self.dict[key] = self.addToHead(node)
#         else:
#             node = self.dict[key]
#             node.val = value
#         if len(self.dict) > self.capacity:
#             popFromTail()
#             self.dict.pop(node.key)


# You shouldn't use ordered dict to create a ordered dict - My Bad

# from collections import OrderedDict
# class LRUCache(object):

#     def __init__(self, capacity):
#         """
#         :type capacity: int
#         """
#         self.capacity = capacity
#         self.dict = OrderedDict()

#     def get(self, key):
#         """
#         :type key: int
#         :rtype: int
#         """
#         if key in self.dict:
#             self.dict.move_to_end(key)
#             return self.dict[key]
#         else:
#             return -1
        

#     def put(self, key, value):
#         """
#         :type key: int
#         :type value: int
#         :rtype: None
#         """
#         if key in self.dict:
#             self.dict.move_to_end(key)
#         else:
#             if len(self.dict) >= self.capacity:
#                 self.dict.popitem(last=False)
#         self.dict[key] = value
        

# Your LRUCache object will be instantiated and called as such:
# obj = LRUCache(capacity)
# param_1 = obj.get(key)
# obj.put(key,value)