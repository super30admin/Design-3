#146. LRU Cache
# Time Complexity : O(1)
# Space Complexity : O(1)
# Did this code successfully run on Leetcode : Yes
# Any problem you faced while coding this : No
class Node:
    def __init__(self,key,val):
        self.key = key
        self.val = val
        self.prev = None
        self.next = None
    
class LRUCache:
    head = None
    Tail = None

    def __init__(self, capacity: int):
        self.mapp = {}
        self.head = Node(-1,-1)
        self.tail = Node(-1,-1)
        self.head.next = self.tail
        self.tail.prev = self.head
        self.capacity = capacity
    def remove(self,node):
        node.prev.next = node.next
        node.next.prev = node.prev
    def insert(self,node):
        node.prev = self.head
        node.next = self.head.next
        self.head.next = node
        node.next.prev = node 

    def get(self, key: int) -> int:
        if key not in self.mapp:
            return -1
        node = self.mapp[key]
        self.remove(node)
        self.insert(node)
        return node.val

    def put(self, key: int, value: int) -> None:
        if key in self.mapp:
            node = self.mapp[key]
            node.val = value
            self.remove(node)
            self.insert(node)
        else:
            if len(self.mapp) == self.capacity:
                tailnode = self.tail.prev
                self.remove(tailnode)
                self.mapp.pop(tailnode.key)
            newnode = Node(key,value)
            self.mapp[key] = newnode
            self.insert(newnode)
