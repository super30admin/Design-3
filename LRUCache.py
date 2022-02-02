# Time Complexity : O(1)
# Space Complexity : O(1)
# Did this code successfully run on Leetcode : yes
# Any problem you faced while coding this : yes

class LRUCache:

    class Node:
        key = 0
        val = 0
        nextp = None
        prev = None

        def __init__(self, key, val):
            self.key = key
            self.val = val

    # hashmap to keep track of the keys and their values in douly linked list
    hmap = {}

    # doubly linked list
    head = None
    tail = None

    capacity = 0  # capacity of the list

    def removeNode(self, node):
        node.prev.nextp = node.nextp
        node.nextp.prev = node.prev

    def addhead(self, node):
        node.nextp = LRUCache.head.nextp
        node.prev = LRUCache.head
        LRUCache.head.nextp = node
        node.nextp.prev = node

    def __init__(self, capacity: int):

        LRUCache.hmap = {}

        LRUCache.head = self.Node(-1, -1)  # dummy node towards the head
        LRUCache.tail = self.Node(-1, -1)  # dummy node towards the tail

        LRUCache.head.nextp = LRUCache.tail
        LRUCache.tail.prev = LRUCache.head

        LRUCache.capacity = capacity

    def get(self, key: int) -> int:
        # return the key if its exists or else -1
        if not key in LRUCache.hmap.keys():
            return -1

        node = LRUCache.hmap[key]
        self.removeNode(node)
        self.addhead(node)
        return node.val

    def put(self, key: int, value: int) -> None:
        # if key exists, update the key value or else add the key value
        if key in LRUCache.hmap.keys():
            node = LRUCache.hmap[key]
            node.val = value
            self.removeNode(node)
            self.addhead(node)

        # if does not exists, remove LRU key
        else:
            if LRUCache.capacity == len(LRUCache.hmap):
                last_node = LRUCache.tail.prev
                self.removeNode(last_node)
                del LRUCache.hmap[last_node.key]
            node = self.Node(key, value)
            self.addhead(node)
            LRUCache.hmap[key] = node


# Your LRUCache object will be instantiated and called as such:
# obj = LRUCache(capacity)
# param_1 = obj.get(key)
# obj.put(key,value)
