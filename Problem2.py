#Time Complexity :O(1)
#Space Complexity :O(n) n is the capacity 
#Did this code successfully run on Leetcode : Yes
#Any problem you faced while coding this : No
#Using double linked list and hash map
class Node:
    def __init__(self, key, val):
        self.key=key
        self.val=val
        self.next=None
        self.prev=None

class LRUCache:

    def __init__(self, capacity: int):
        self.map={}
        self.capacity=capacity
        self.head=Node(-1,-1)
        self.tail=Node(-1,-1)
        self.head.next=self.tail
        self.tail.prev=self.head

    def get(self, key: int) -> int:
        if key not in self.map.keys():
            return -1
        else:
            curr=self.map[key]
            self.removeNode(curr)
            self.addtoHead(curr)
            return self.map[key].val
        
    def put(self, key: int, value: int) -> None:
        if key in self.map.keys():
            curr=self.map[key]
            curr.val=value
            self.removeNode(curr)
            self.addtoHead(curr)
        else:
            if self.capacity==len(self.map):
                prevToTail=self.tail.prev
                self.removeNode(prevToTail)
                del(self.map[prevToTail.key])
            freshNode=Node(key,value)
            self.addtoHead(freshNode)
            self.map[key]=freshNode
    
    def addtoHead(self, curr):
        curr.next=self.head.next
        curr.prev=self.head
        self.head.next=curr
        curr.next.prev=curr

    def removeNode(self,curr):
        curr.prev.next=curr.next
        curr.next.prev=curr.prev


# Your LRUCache object will be instantiated and called as such:
# obj = LRUCache(capacity)
# param_1 = obj.get(key)
# obj.put(key,value)