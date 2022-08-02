
class Node:
    def __init__(self, key, val) -> None:
        self.val = val
        self.key = key
        self.prev = None
        self.next = None

class LRUCache:

    def __init__(self, capacity: int):
        self.cache = {}
        self.capacity = capacity
        self.head = Node(0,0)
        self.tail = Node(0,0)
        self.head.next = self.tail
        self.tail.prev = self.head    

    def remove(self, node):
        prev, next = node.prev, node.next
        prev.next , next.prev = next , prev

    def insert(self, node):
        exisitng = self.head.next
        self.head.next = node 
        node.prev = self.head
        node.next = exisitng
        exisitng.prev = node   

    def get(self, key: int) -> int:
        if key in self.cache:
            self.remove(self.cache[key])
            self.insert(self.cache[key])
            return self.cache[key].val
        return -1

    def put(self, key: int, value: int) -> None:
        if key in self.cache:
            self.remove(self.cache[key])
            del self.cache[key]
        self.cache[key] = Node(key,value)
        self.insert(self.cache[key])

        if len(self.cache) > self.capacity :
            curr_last = self.tail.prev
            self.remove(curr_last)
            del self.cache[curr_last.key]
