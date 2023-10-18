# Time Complexity: O(1)
#  Space Complexity:  O(1).
#  Did this code successfully run on Leetcode : Yes
#  Any problem you faced while coding this : No

class DLL:
    def __init__(self, key, val):
        self.key = key
        self.val = val
        self.next = None
        self.prev = None

class LRUCache:

    def __init__(self, capacity: int):
        self.head = DLL(-1,-1)
        self.tail = DLL(-1, -1)
        self.hashMap = dict()
        self.capacity = capacity
        self.head.next = self.tail
        self.tail.prev = self.head
    
    def insert_at_head(self, node):
        node.next = self.head.next
        self.head.next.prev = node
        node.prev = self.head
        self.head.next = node
    
    def delete_node(self, node):
        node.prev.next = node.next
        node.next.prev = node.prev
        

    def get(self, key: int) -> int:
        if key not in self.hashMap:
            return -1
        node = self.hashMap[key]
        self.delete_node(node)
        self.insert_at_head(node)
        return node.val
        

    def put(self, key: int, value: int) -> None:
        if key in self.hashMap:
            node = self.hashMap[key]
            node.val = value
            self.delete_node(node)
            self.insert_at_head(node)
            return node.val
        if self.capacity == len(self.hashMap):
            prev_tail = self.tail.prev
            self.delete_node(prev_tail)
            self.hashMap.pop(prev_tail.key)
        node = DLL(key, value)
        self.hashMap[key] = node
        self.insert_at_head(node)
        


# Your LRUCache object will be instantiated and called as such:
# obj = LRUCache(capacity)
# param_1 = obj.get(key)
# obj.put(key,value)
