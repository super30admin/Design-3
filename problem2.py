#Time complexity: O(1)
#Space complexity: O(n)
#Did this code successfully run on Leetcode : yes
class Node:
        def __init__(self, key, val):
            self.key = key
            self.val = val
            self.next = None
            self.prev = None
        
class LRUCache:           
    def __init__(self, capacity: int):
        self.hashmap = dict()
        self.cap = capacity
        self.head = Node(-1,-1)
        self.tail = Node (-1,-1)
        self.head.next = self.tail
        self.tail.prev = self.head

    # Add function
    def addToHead(self,node):
        node.next = self.head.next
        node.prev = self.head
        
        self.head.next = node
        node.next.prev = node

    # Delete function
    def removeNode(self,node):
        node.next.prev = node.prev
        node.prev.next = node.next
    
    def get(self, key: int) -> int:
        if key in self.hashmap:
            # make node as most recent
            node = self.hashmap[key]
            self.removeNode(node)
            self.addToHead(node)
            return node.val
        else:
            return -1

    def put(self, key: int, value: int) -> None:
        if key in self.hashmap:
            node = self.hashmap[key]
            node.val = value
            self.removeNode(node)
            self.addToHead(node)
        else:
            if len(self.hashmap) == self.cap:
                node = self.tail.prev
                self.removeNode(node)
                self.hashmap.pop(node.key)
            node = Node(key,value)
            self.addToHead(node)
            self.hashmap[key] = node