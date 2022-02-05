# Design-3

## Problem 1: Flatten Nested List Iterator (https://leetcode.com/problems/flatten-nested-list-iterator/)


# """
# This is the interface that allows for creating nested lists.
# You should not implement it, or speculate about its implementation
# """
#class NestedInteger:
#    def isInteger(self) -> bool:
#        """
#        @return True if this NestedInteger holds a single integer, rather than a nested list.
#        """
#
#    def getInteger(self) -> int:
#        """
#        @return the single integer that this NestedInteger holds, if it holds a single integer
#        Return None if this NestedInteger holds a nested list
#        """
#
#    def getList(self) -> [NestedInteger]:
#        """
#        @return the nested list that this NestedInteger holds, if it holds a nested list
#        Return None if this NestedInteger holds a single integer
#        """
class NestedIterator:
    def __init__(self, nestedList: [NestedInteger]):
        def _generator(nestedList):
            for nl in nestedList:
                if nl.isInteger():
                    yield nl.getInteger()
                else:
                    yield from _generator(nl.getList())
        self.gen = _generator(nestedList)
        self.nextelem = next(self.gen, None)
    
    def next(self) -> int:
        ret = self.nextelem
        self.nextelem = next(self.gen, None)
        return ret
    
    def hasNext(self) -> bool:
        return self.nextelem != None

# Your NestedIterator object will be instantiated and called as such:
# i, v = NestedIterator(nestedList), []
# while i.hasNext(): v.append(i.next())

## Problem 2: LRU Cache(https://leetcode.com/problems/lru-cache/)



class Node:
    def __init__(self,key,value):
        self.value=value
        self.key=key
        self.next=None
        self.prev=None
class LRUCache:
    
    def __init__(self, capacity: int):
        self.head=Node(-1,-1)
        self.tail=Node(-1,-1)
        self.capacity=capacity
        self.maps={}
        self.head.next=self.tail
        self.tail.prev=self.head
    def removenode(self,node):
        node.next.prev=node.prev
        node.prev.nect=node.next
    def addnode(self,node):
        node.prev=self.head
        node.next=self.head.next
        self.head.next=node
        node.next.prev=node
        
    def get(self, key: int) -> int:
        node1=self.maps.get(key)
        if node1:
            self.removenode(node1)
            self.addnode(node1)
            return node1.value
        else:
            return -1
        
    def put(self, key: int, value: int) -> None:
        n=self.maps.get(key)
        if n:
            n.value=value
            self.removenode(n)
            self.addnode(n)
        else:
            if self.capacity==len(self.maps):
                node1=self.tail.prev
                self.removenode(node1)
                self.maps.pop(node1.key,None)
            newn=Node(key,value)
            self.maps[key]=newn
            self.addnode(newn)
            
                
        
        


# Your LRUCache object will be instantiated and called as such:
# obj = LRUCache(capacity)
# param_1 = obj.get(key)
# obj.put(key,value)