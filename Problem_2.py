class LRUCache:
    """
    get - TC - O(1) SC - O(c)
    put - TC - O(1), SC - O(c)
    """

    class Node:
        def __init__(self, key, val):
            self.key = key
            self.val = val
            self.prev = None
            self.next = None

    def __init__(self, capacity: int):
        self.cap = capacity
        self.hp = dict()
        self.head = self.Node(-1, -1)
        self.tail = self.Node(-1, -1)
        self.head.next = self.tail
        self.tail.next = self.head

    def headAdd(self, node):
        node.next = self.head.next
        node.next.prev = node
        node.prev = self.head
        self.head.next = node

    def nodeRemove(self, node):
        node.next.prev = node.prev
        node.prev.next = node.next

    def get(self, key: int) -> int:
        if key in self.hp:
            node = self.hp[key]
            self.nodeRemove(node)
            self.headAdd(node)
            return node.val
        return -1

    def put(self, key: int, value: int) -> None:
        if key in self.hp:
            node = self.hp[key]
            node.val = value
            self.nodeRemove(node)
            self.headAdd(node)
        else:
            if self.cap == len(self.hp):
                tailprev = self.tail.prev
                self.nodeRemove(tailprev)
                del self.hp[tailprev.key]
            newNode = self.Node(key, value)
            self.headAdd(newNode)
            self.hp[key] = newNode

# Your LRUCache object will be instantiated and called as such:
# obj = LRUCache(capacity)
# param_1 = obj.get(key)
# obj.put(key,value)