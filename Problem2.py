# Time Complexity : O(1)
# Space Complexity : O(n)
# Did this code successfully run on Leetcode : yes
# Any problem you faced while coding this : no


class Node(object):
    def __init__(self, key, value):
        self.next = None
        self.prev = None
        self.key = key
        self.val = value


class LRUCache(object):
    def __init__(self, capacity):
        self.cap = capacity
        self.store = {}
        self.head, self.tail = Node(-1, -1), Node(-1, -1)
        self.head.next, self.tail.prev = self.tail, self.head

    def get(self, key):

        if key not in self.store:
            return -1

        result = self.store[key]
        self.update(result)
        return result.val

    def put(self, key, value):

        if key in self.store:
            node = self.store[key]
            node.val = value
            self.update(node)

        else:
            if self.cap == len(self.store):
                toRemove = self.tail.prev
                self.removeNode(toRemove)
                del self.store[toRemove.key]
            toAdd = Node(key, value)
            self.store[key] = toAdd
            self.addToHead(toAdd)

    def update(self, node):
        self.removeNode(node)
        self.addToHead(node)

    def addToHead(self, node):
        node.next = self.head.next
        node.prev = self.head
        self.head.next = node
        node.next.prev = node

    def removeNode(self, node):
        node.prev.next = node.next
        node.next.prev = node.prev


# Your LRUCache object will be instantiated and called as such:
# obj = LRUCache(capacity)
# param_1 = obj.get(key)
# obj.put(key,value)
