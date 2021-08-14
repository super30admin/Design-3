class ListNode:

    def __init__(self, key, val):
        self.key = key
        self.val = val
        self.prev = None
        self.next = None


class LRUCache:

    def __init__(self, capacity):
        self.capacity = capacity
        self.hashmap = {}
        self.head = ListNode(-1, -1)
        self.tail = ListNode(-1, -1)
        self.head.next = self.tail
        self.tail.prev = self.head

    def removeNode(self, node):
        node.prev.next = node.next
        node.next.prev = node.prev

    def addTohead(self, node):
        node.prev = self.head
        node.next = self.head.next
        self.head.next = node
        node.next.prev = node

    def get(self, key) :
        # available
        if key not in self.hashmap:
            return -1

        node = self.hashmap[key]
        self.removeNode(node)
        self.addTohead(node)
        return node.val

    def put(self, key, value) :

        # present
        if key in self.hashmap:
            node = self.hashmap.get(key)
            node.val = value
            self.removeNode(node)
            self.addTohead(node)

        # not present
        else:
            if self.capacity == len(self.hashmap):
                tail_prev = self.tail.prev
                self.removeNode(tail_prev)
                del self.hashmap[tail_prev.key]
            new = ListNode(key, value)
            self.addTohead(new)
            self.hashmap[key] = new

# Your LRUCache object will be instantiated and called as such:
# obj = LRUCache(capacity)
# param_1 = obj.get(key)
# obj.put(key,value)