class Node(object):
    def __init__(self, key=0, val=0, nex=None, prev=None):
        self.key = key
        self.val = val
        self.next = nex
        self.prev = prev
# TC - O(1)
# SC - O(c) => c is the capacity


class LRUCache(object):

    def __init__(self, capacity):
        """
        :type capacity: int
        """
        self.head = Node()
        self.tail = Node()
        self.capacity = capacity
        self.map = {}
        self.head.next = self.tail
        self.tail.prev = self.head

    def remove(self, node):
        node.prev.next = node.next
        node.next.prev = node.prev

    def addToHead(self, node):

        node.prev = self.head
        node.next = self.head.next
        self.head.next.prev = node
        self.head.next = node

    def get(self, key):
        """
        :type key: int
        :rtype: int
        """
        if key not in self.map:
            return -1
        node = self.map[key]
        self.remove(node)
        self.addToHead(node)
        return node.val

    def removeTail(self):
        self.tail = self.tail.prev
        self.tail.next = None

    def put(self, key, value):
        """
        :type key: int
        :type value: int
        :rtype: None
        """
        if key not in self.map:
            node = Node(key, value)
            if self.capacity == len(self.map):
                tailNode = self.tail.prev
                self.map.pop(tailNode.key)
                self.remove(tailNode)

            self.addToHead(node)
            self.map[key] = node
        else:
            node = self.map[key]
            node.val = value
            self.remove(node)
            self.addToHead(node)


# Your LRUCache object will be instantiated and called as such:
# obj = LRUCache(capacity)
# param_1 = obj.get(key)
# obj.put(key,value)
