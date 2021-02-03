# Time Complexity : O(1) for get and put operations
# Space Complexity : O(c) where c is the capacity
# Did this code successfully run on Leetcode : Yes
# Three line explanation of solution in plain english
# I iterate through trie for each word and return the prefix where I find the first root

class ListNode:
    def __init__(self, key, val, prev=None, nextNode=None):
        self.key = key
        self.val = val
        self.next = nextNode
        self.prev = prev

class LRUCache:

    def __init__(self, capacity: int):
        self.capacity = capacity
        self.mapping = {}
        self.head = ListNode(-1, -1)
        self.tail = ListNode(-1, -1, prev=self.head, nextNode=None)
        self.head.next = self.tail
        

    def remove_after_(self, node):
        if node.next.val != -1:
            node.next = node.next.next
            node.next.prev = node
    
    def put_before_tail(self, node):
        self.tail.prev.next = node
        node.prev = self.tail.prev
        self.tail.prev = node
        node.next = self.tail
        
    
    def get(self, key: int) -> int:
        node = self.mapping[key] if key in self.mapping.keys() else None
        if node is not None:
            self.remove_after_(node.prev)
            self.put_before_tail(node)
            return node.val
        else:
            return -1
        

    def put(self, key: int, value: int) -> None:
        node = None
        if key in self.mapping.keys() and self.mapping[key]:
            node = self.mapping[key]
            self.remove_after_(node.prev)
            node.val = value
            
        else:    
            node = ListNode(key, value)
            self.mapping[key] = node
            
        self.put_before_tail(node)
        if len(self.mapping) > self.capacity:
            self.mapping.pop(self.head.next.key)
            self.remove_after_(self.head)


# Your LRUCache object will be instantiated and called as such:
# obj = LRUCache(capacity)
# param_1 = obj.get(key)
# obj.put(key,value)