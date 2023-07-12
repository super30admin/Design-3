'''
Problem: LRU Cache
Time Complexity: O(1)
Space Complexity: O(1), auxillary space not used
Did this code successfully run on Leetcode: Yes
Any problem you faced while coding this: No
Your code here along with comments explaining your approach:
        maintain a hashmap for key and Node as value
        to maintain order we use doubly linked list
        if key is used, remove node and add to head to show most recently used
'''

class Node:
    def __init__(self, key, val):
        self.key = key
        self.val = val
        self.next = None
        self.prev = None

class LRUCache:

    def addtohead(self, curr):
        curr.prev = self.head
        curr.next = self.head.next
        self.head.next = curr
        curr.next.prev = curr
        
    def removenode(self,curr):
        curr.next.prev = curr.prev
        curr.prev.next = curr.next
        
    def __init__(self, capacity: int):
        self.head = Node(-1,-1)
        self.tail = Node(-1,-1)
        self.head.next = self.tail
        self.tail.prev = self.head
        self.cap = capacity
        self.cache = {}
        

    def get(self, key: int) -> int:
        if key in self.cache:
            node = self.cache[key]
            self.removenode(node)
            self.addtohead(node)
            return node.val
        else:
            return -1
        

    def put(self, key: int, value: int) -> None:
        if key in self.cache:
            node = self.cache.get(key)
            node.val = value
            self.removenode(node)
            self.addtohead(node)
        else:
            if self.cap == len(self.cache):
                node = self.tail.prev
                self.removenode(node)
                newnode = Node(key, value)
                self.addtohead(newnode)
                self.cache[key] = newnode
                del self.cache[node.key]
            else:
                newnode = Node(key, value)
                self.addtohead(newnode)
                self.cache[key] = newnode


# Your LRUCache object will be instantiated and called as such:
# obj = LRUCache(capacity)
# param_1 = obj.get(key)
# obj.put(key,value)