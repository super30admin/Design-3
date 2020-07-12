"""
// Time Complexity : o(n), to fill up the queue
// Space Complexity : o(n), queue size
// Did this code successfully run on Leetcode : yes
// Any problem you faced while coding this : no

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
        self.q = deque()
        self.fill(nestedList)
        
    def fill(self, nestedList): #store all the values in a queue
        for i in nestedList:
            if i.isInteger():
                self.q.append(i.getInteger())
            else:
                self.fill(i.getList())

    def next(self) -> int: # next element will be at the top of the queue
        return self.q.popleft()
        
    def hasNext(self) -> bool: #if queue is not empty, return true
        return len(self.q) > 0
         

# Your NestedIterator object will be instantiated and called as such:
# i, v = NestedIterator(nestedList), []
# while i.hasNext(): v.append(i.next())