# Time Complexity: O(1)
# Space Complexity: O(capacity)
class LRUCache:
    class ListNode:
        def __init__(self, key, val):
            self.key = key
            self.val = val
            self.next = None
            self.prev = None

    def __init__(self, capacity: int):
        self.capacity = capacity
        self.hashmap = {}
        self.head = self.ListNode(-1, -1)
        self.tail = self.ListNode(-1, -1)
        self.head.next = self.tail
        self.tail.prev = self.head

    def removeNode(self, node):
        node.prev.next = node.next
        node.next.prev = node.prev
        node.prev = None
        node.next = None

    def addNodetohead(self, node):
        node.prev = self.head
        node.next = self.head.next
        node.next.prev = node
        self.head.next = node

    def get(self, key: int) -> int:
        if key not in self.hashmap:
            return -1

        node = self.hashmap[key]
        self.removeNode(node)
        self.addNodetohead(node)
        return node.val

    def put(self, key: int, value: int) -> None:
        if key in self.hashmap:
            node = self.hashmap[key]
            node.val = value
            self.removeNode(node)
            self.addNodetohead(node)
        else:
            if len(self.hashmap) == self.capacity:
                lastNode = self.tail.prev
                self.removeNode(lastNode)
                del self.hashmap[lastNode.key]

            newNode = self.ListNode(key, value)
            self.addNodetohead(newNode)
            self.hashmap[key] = newNode



# Your LRUCache object will be instantiated and called as such:
# obj = LRUCache(capacity)
# param_1 = obj.get(key)
# obj.put(key,value)