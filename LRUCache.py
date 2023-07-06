# Time Complexity : O(1) 
# Space Complexity : O(1) 
# Did this code successfully run on Leetcode : Yes
# Any problem you faced while coding this : NA
# Approach is to implement the function calls of get and put in LRU cache.


class Node:
    def __init__(self, key=-1, val=-1):
        self.key=key
        self.val=val
        self.next=None
        self.prev=None

class LRUCache:
    
    def removeNode(self, curr):
        curr.next.prev=curr.prev
        curr.prev.next=curr.next

    def addTohead(self, curr):
        curr.prev = self.head
        curr.next= self.head.next
        self.head.next = curr
        curr.next.prev = curr

    def __init__(self, capacity: int):
        self.head= Node()    
        self.tail = Node()
        self.head.next = self.tail
        self.tail.prev = self.head
        self.mp = {}
        self.capacity=capacity

    def get(self, key: int) -> int:
        if(key not in self.mp.keys()):
            return -1
        node=self.mp[key]
        self.removeNode(node)
        self.addTohead(node)
        return node.val

    def put(self, key: int, value: int) -> None:
        if(key in self.mp.keys()):
            node=self.mp[key]
            node.val=value
            self.removeNode(node)
            self.addTohead(node)
        else:
            if(len(self.mp) == self.capacity):
                toremove=self.tail.prev
                self.removeNode(toremove)
                del self.mp[toremove.key]
            newNode= Node(key, value)
            self.addTohead(newNode)
            self.mp[key]=newNode