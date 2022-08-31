class Node:
    def __init__(self, key, value):
        self.key = key
        self.val = value
        self.next = 0
        self.prev = 0


class LRUCache:

    def __init__(self, capacity: int):
        self.capacity = capacity
        self.d = dict()
        self.head = Node(0, 0)
        self.tail = Node(0, 0)
        self.head.next = self.tail
        self.tail.prev = self.head

    def removeNode(self, node):
        node.prev.next = node.next
        node.next.prev = node.prev

    def addNode(self, node):
        node.next = self.head.next
        node.prev = self.head
        self.head.next = node
        node.next.prev = node

    def get(self, key: int) -> int:
        if key not in self.d:
            return -1
        node = self.d[key]
        self.removeNode(node)
        self.addNode(node)
        return node.val

    def put(self, key: int, value: int) -> None:
        if key in self.d:
            node = self.d[key]
            node.val = value
            self.removeNode(node)
            self.addNode(node)

        else:
            if self.capacity == len(self.d):
                tailPrev = self.tail.prev
                self.removeNode(tailPrev)
                del self.d[tailPrev.key]
            newNode = Node(key, value)
            self.addNode(newNode)
            self.d[key] = newNode

# Your LRUCache object will be instantiated and called as such:
# obj = LRUCache(capacity)
# param_1 = obj.get(key)
# obj.put(key,value)

# Time Complexity = O(1)
# Space complexity : O(n)
# Did this code successfully run on Leetcode : yes
