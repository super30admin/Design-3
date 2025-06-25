"""
All operations are O(1)
s.c. = number of new elements
"""
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
from collections import deque
class NestedIterator:
    def __init__(self, nestedList: [NestedInteger]):
        self.stack = deque()
        self.stack.append(iter(nestedList))

        self.currElement = None
    
    def next(self) -> int:
        return self.currElement.getInteger()
    
    def hasNext(self) -> bool:
        if self.processList():
            return True
        return False
        
    def processList(self):

        while self.stack:
            try:
                self.currElement = next(self.stack[-1]) #gives a nestedList type
            except StopIteration:
                self.stack.pop()
                continue

            if self.currElement.isInteger():
                return True
            else:
                self.stack.append(iter(self.currElement.getList()))
        return False


# Your NestedIterator object will be instantiated and called as such:
# i, v = NestedIterator(nestedList), []
# while i.hasNext(): v.append(i.next())