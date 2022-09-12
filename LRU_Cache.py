'''
Time Complexity - O(1)
Space Complexity - O(n) where n is the capacity
'''


class Node:
    def __init__(self, key, value):
        self.key = key
        self.value = value
        self.prev = None
        self.next = None


class LRUCache:

    def __init__(self, capacity: int):
        self.hmap = {}
        self.head = Node(-1, -1)
        self.tail = Node(-1, -1)
        self.capacity = capacity
        self.head.next = self.tail
        self.tail.prev = self.head

    def remove(self, node):
        node.next.prev = node.prev
        node.prev.next = node.next

    def addHead(self, node):
        node.next = self.head.next
        node.prev = self.head
        self.head.next = node
        node.next.prev = node

    def get(self, key: int) -> int:
        if key not in self.hmap:
            return -1
        node = self.hmap[key]
        self.remove(node)
        self.addHead(node)
        return node.value

    def put(self, key: int, value: int) -> None:
        if key in self.hmap:
            node = self.hmap[key]
            node.value = value
            self.remove(node)
            self.addHead(node)
        else:
            if len(self.hmap) == self.capacity:
                node = self.tail.prev
                self.remove(node)
                self.hmap.pop(node.key)
            newNode = Node(key, value)
            self.hmap[key] = newNode
            self.addHead(newNode)


# Your LRUCache object will be instantiated and called as such:
# obj = LRUCache(capacity)
# param_1 = obj.get(key)
# obj.put(key,value)
