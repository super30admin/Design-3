# -*- coding: utf-8 -*-
"""
TC : O(N) where N is the total no. of elemnts in the list given
SC : O(N) where N = size of stack
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

class NestedIterator:
    def __init__(self, nestedList: [NestedInteger]):
        self.stack = [iter(nestedList)]
        self.nextElement = None
    def next(self) -> int:
        return self.nextElement
    def hasNext(self) -> bool:
        while(len(self.stack)):
            try:
                stack_top = next(self.stack[-1])
                if stack_top.isInteger():
                    self.nextElement = stack_top.getInteger()
                    return True
                else:
                    self.stack.append(iter(stack_top.getList()))
            except StopIteration:
                self.stack.pop()
                continue
        return None
         

# Your NestedIterator object will be instantiated and called as such:
# i, v = NestedIterator(nestedList), []
# while i.hasNext(): v.append(i.next())