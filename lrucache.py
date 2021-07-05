# Optmized using doubly linked list and hashmap
# Runtime - put and get -O(1)
# Memory - O(n) where n is capacity of cache
class Node:
    def __init__(self, key, value):
        self.key = key
        self.val = value
        self.next = None
        self.prev = None
            
class LRUCache:
    def __init__(self, capacity: int):
        self.d = {}
        self.size = 0
        self.capacity = capacity
        self.head = Node(-1,-1)
        self.tail = Node(-1,-1)
        self.head.next = self.tail
        self.tail.prev = self.head
        
    def add_to_head(self, node):
        self.head.next.prev = node
        node.next = self.head.next
        self.head.next = node
        node.prev = self.head
   
    def remove_node(self, node):
        node.prev.next = node.next
        node.next.prev = node.prev
        node.prev = None
        node.next = None
        
    def get(self, key: int) -> int:
        if key not in self.d:
            return -1
        else:
            node = self.d[key]
            self.remove_node(node)
            self.add_to_head(node)
            return node.val
            
    def put(self, key: int, value: int) -> None:
        if key not in self.d:
            self.d[key] = Node(key, value)
            if self.size == self.capacity:
                del self.d[self.tail.prev.key]
                self.remove_node(self.tail.prev)
                self.add_to_head(self.d[key])
            else:
                self.add_to_head(self.d[key])
                self.size += 1
        else:
            node = self.d[key]
            node.val=value
            self.remove_node(node)
            self.add_to_head(node)
