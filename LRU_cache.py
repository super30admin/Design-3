# Bruteforce using array and hashmap

class LRUCache:
    def __init__(self, capacity: int):
        self.d = {}
        self.size = 0
        self.queue = []
        self.capacity = capacity
        
    def get(self, key: int) -> int:
        if key not in self.d:
            return -1
        else:
            self.queue.pop(0)
            self.queue.append(key)
            return self.d[key]

    def put(self, key: int, value: int) -> None:
        if key not in self.d:
            if self.size == self.capacity:
                popped = self.queue.pop(0)
                if popped:
                    del self.d[popped]
                self.d[key] = value
                self.queue.append(key)
            else:
                self.d[key] = value
                self.size += 1
                self.queue.append(key)
        else:
            self.d[key] = value
            self.queue.pop(0)
            self.queue.append(key)


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
        else:
            self.remove_node(self.d[key])
            self.size -= 1
            self.d[key] = Node(key, value)
        
        if self.size == self.capacity:
            del self.d[self.tail.prev.key]
            self.remove_node(self.tail.prev)
            self.add_to_head(self.d[key])
        else:
            self.add_to_head(self.d[key])
            self.size += 1
