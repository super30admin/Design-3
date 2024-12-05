class Node:
    def __init__(self, key, value):
        self.key = key 
        self.value = value # Value stored in the node
        self.next = None 
        self.prev = None

class LRUCache:

    def __init__(self, capacity: int):
        self.head = Node(-1, -1)
        self.tail = Node(-1, -1)
        self.capacity = capacity
        self.cache = {}
        self.head.next = self.tail
        self.tail.prev = self.head
    
    def _add(self, new_node):
        prev_node = self.tail.prev
        prev_node.next = new_node
        new_node.prev = prev_node
        new_node.next = self.tail
        self.tail.prev = new_node
    
    def _remove(self, node):
        prev_node = node.prev
        prev_node.next = node.next
        node.next.prev = prev_node

        
    def get(self, key: int) -> int:
        if key in self.cache:
            node = self.cache[key]
            self._remove(node)
            self._add(node)
            return node.value
        return -1 

    def put(self, key: int, value: int) -> None:
        if key in self.cache:
            self._remove(self.cache[key])
        new_node = Node(key, value)
        self._add(new_node)
        self.cache[key] = new_node
        if len(self.cache) > self.capacity:
            lru_node = self.head.next
            self._remove(lru_node)
            del self.cache[lru_node.key]
        
# Your LRUCache object will be instantiated and called as such:
# obj = LRUCache(capacity)
# param_1 = obj.get(key)
# obj.put(key,value)