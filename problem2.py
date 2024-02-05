#Time Complexity :  O(N )
#Space Complexity :O(1)
#Did this code successfully run on Leetcode : yes

class Node:
    def __init__(self, key, value):
        self.key = key
        self.value = value
        self.prev = None
        self.next = None

class LRUCache:
    def __init__(self, capacity: int):
        self.capacity = capacity
        self.map = {}
        self.head = Node(0, 0)
        self.tail = Node(0, 0)
        self.head.next = self.tail
        self.tail.prev = self.head

    def get(self, key: int) -> int:
        if key in self.map:
            node = self.map[key]
            self.remove(node)
            self.insert(node)
            return node.value
        else:
            return -1
        
    def put(self, key: int, value: int) -> None:
        if key in self.map:
            self.remove(self.map[key])
        if len(self.map) == self.capacity:
            self.remove(self.tail.prev)
        self.insert(Node(key, value))

    def remove(self, node):
        del self.map[node.key]
        node.prev.next = node.next
        node.next.prev = node.prev

    def insert(self, node):
        self.map[node.key] = node
        node.next = self.head.next
        node.next.prev = node
        self.head.next = node
        node.prev = self.head

# Your LRUCache object will be instantiated and called as such:
# obj = LRUCache(capacity)
# param_1 = obj.get(key)
# obj.put(key,value)
