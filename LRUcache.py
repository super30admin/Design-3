# Time Complexity : O(1)
# Space Complexity : O(n), where n is the capacity of the cache.
# Did this code successfully run on Leetcode : yes
# Any problem you faced while coding this : no

# Your code here along with comments explaining your approach
class Node(object):
    def __init__(self, key, value):
        self.next = None
        self.prev = None
        self.key = key
        self.val = value

# This approach utilises a hashmap and a Doubly Linked List to store the items.
# a hashmap aides in faster insertion and lookup, and the list helps preserve order.
# if an existing item is put or get, it is made the recent & value changed
# in case of put. otherwise the value is simply added and made the recent.
class LRUCache(object):
    def __init__(self, capacity):
        self.cap = capacity
        self.store = {}
        self.head, self.tail = Node(-1, -1), Node(-1, -1)
        self.head.next, self.tail.prev = self.tail, self.head

    def get(self, key):
        # if the key doesn't exist
        if key not in self.store:
            return -1
        # else make recent & return
        retVal = self.store[key]
        self.update(retVal)
        return retVal.val

    def put(self, key, value):
        # Case: key exists, change val & make recent
        if key in self.store:
            node = self.store[key]
            node.val = value
            self.update(node)
        # else make space if required & add to recent
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
