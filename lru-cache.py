''' Time Complexity : O(1)  for all operations
    Space Complexity : O(capacity) 
    Did this code successfully run on Leetcode : Yes
    Any problem you faced while coding this :  No
'''
class ListNode:
    def __init__(self, key=0,val=0, prev=None, next=None):
        self.key=key
        self.val=val
        self.prev=prev
        self.next=next

class LRUCache:
    def __init__(self, capacity: int):
        self.hashmap={}
        self.dummy = ListNode(-1,-1)
        self.tail = ListNode(-1,-1)
        self.dummy.next = self.tail
        self.tail.prev = self.dummy
        self.capacity = capacity

    def get(self, key: int) -> int:
        if key in self.hashmap:
            node = self.hashmap[key]
            self.removeNode(node)
            self.insertAtStart(node)
            return node.val
        else:
            return -1
        

    def put(self, key: int, value: int) -> None:
        if key in self.hashmap:
            node = self.hashmap[key]
            node.val = value
            self.removeNode(node)
            self.insertAtStart(node)
        else:
            node = ListNode(key,value)
            self.hashmap[key] = node
            self.insertAtStart(node)
            if len(self.hashmap) > self.capacity:
                node = self.tail.prev
                self.hashmap.pop(node.key)
                self.removeNode(node)


    def insertAtStart(self,node):
        temp = self.dummy.next
        self.dummy.next = node
        node.prev = self.dummy
        node.next = temp
        temp.prev = node
    
    def removeNode(self,node):
        node.prev.next = node.next
        node.next.prev = node.prev
        node.next = None
        node.prev = None

# Your LRUCache object will be instantiated and called as such:
# obj = LRUCache(capacity)
# param_1 = obj.get(key)
# obj.put(key,value)