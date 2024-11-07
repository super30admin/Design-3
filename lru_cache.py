class Node:
    def __init__(self, key, value):
        self.key = key
        self.value = value
        self.prev = None
        self.next = None


class LRUCache:

    def __init__(self, capacity: int):
        self.cache = {}
        self.capacity = capacity
        self.head = Node(0, 0)
        self.tail = Node(0, 0)
        self.head.next = self.tail
        self.tail.prev = self.head

    def remove(self, node):
        node.prev.next = node.next
        node.next.prev = node.prev

    def add_to_head(self, node):
        node.next = self.head.next
        node.prev = self.head
        self.head.next.prev = node
        self.head.next = node

    def get(self, key: int) -> int:
        node = self.cache.get(key)
        if not node:
            return -1
        self.remove(node)
        self.add_to_head(node)
        return node.value

    def put(self, key: int, value: int) -> None:
        node = self.cache.get(key)
        if node:
            node.value = value
            self.remove(node)
            self.add_to_head(node)
        else:
            new_node = Node(key, value)

            if len(self.cache) >= self.capacity:
                tail = self.tail.prev
                self.remove(tail)
                del self.cache[tail.key]
            self.add_to_head(new_node)
            self.cache[key] = new_node


# time complexity is O(1)
# space complexity is O(n)

# Your LRUCache object will be instantiated and called as such:
# obj = LRUCache(capacity)
# param_1 = obj.get(key)
# obj.put(key,value)
