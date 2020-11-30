class Node:
    def __init__(self, key, val):
        self.key = key
        self.val = val
        self.next = None
        self.prev = None


class LRUCache(object):

    def __init__(self, capacity):
        """
        :type capacity: int
        """
        self.capacity = capacity
        self.dic = {}
        self.head = Node(-1, -1)
        self.tail = Node(-1, -1)
        self.head.next = self.tail
        self.tail.prev = self.head

    def remove(self, node):
        n = node.next
        p = node.prev
        p.next = n
        n.prev = p

    def add(self, node):
        p = self.tail.prev
        p.next = node
        self.tail.prev = node
        node.next = self.tail
        node.prev = p

    def get(self, key):
        """
        :type key: int
        :rtype: int
        """
        if key not in self.dic:
            return -1
        node = self.dic[key]
        self.remove(node)
        self.add(node)
        return node.val

    def put(self, key, value):
        """
        :type key: int
        :type value: int
        :rtype: None
        """
        if key in self.dic:
            n = self.dic[key]
            self.dic.pop(key)
            self.remove(n)
        node = Node(key, value)
        self.add(node)
        self.dic[key] = node
        if len(self.dic) > self.capacity:
            old = self.head.next
            self.dic.pop(old.key)
            self.remove(old)

        # print(self.head.next.val)
# Your LRUCache object will be instantiated and called as such:
# obj = LRUCache(capacity)
# param_1 = obj.get(key)
# obj.put(key,value)
# time complexity-O(1) space-O(2n)