# Time Complexity : O(1)
# Space Complexity : O(n)
# Did this code successfully run on Leetcode : Yes
# Any problem you faced while coding this : No

class Node:
        def __init__(self, key, value):
            self.key = key
            self.val = value
            self.prev = None
            self.next = None


class LRUCache:
    def add_to_head(self, node):  #adding the node to the doubly linkedlist
        node.prev = self.head
        node.next = self.head.next
        self.head.next = node
        node.next.prev = node
    
    def removeNode(self, node):  #removing the node from the LL
        node.next.prev = node.prev
        node.prev.next = node.next
        
    def __init__(self, capacity: int):
        self.hashmap = {}
        
        self.head = Node(-1, -1)
        self.tail = Node(-1, -1)
        self.head.next = self.tail
        self.tail.prev = self.head
        self.capacity = capacity
    
    def get(self, key: int) -> int:
        if key not in self.hashmap:
            return -1
        node = self.hashmap[key]
        self.removeNode(node)
        self.add_to_head(node)
        return node.val
            

    def put(self, key: int, value: int) -> None:
        if key in self.hashmap:
            node = self.hashmap[key]
            node.val = value
            self.removeNode(node)
            self.add_to_head(node)
        else:
            if len(self.hashmap) == self.capacity:
                #if capacity is full
                tailPrev = self.tail.prev
                self.removeNode(tailPrev)
                self.hashmap.pop(tailPrev.key)
            newnode = Node(key, value)
            self.hashmap[key] = newnode
            self.add_to_head(newnode)
        
        

