"""
# Design-3

## Problem 1: Flatten Nested List Iterator (https://leetcode.com/problems/flatten-nested-list-iterator/)

Given a nested list of integers, implement an iterator to flatten it.

Each element is either an integer, or a list -- whose elements may also be integers or other lists.

Example 1:

Input: [[1,1],2,[1,1]]
Output: [1,1,2,1,1]
Explanation: By calling next repeatedly until hasNext returns false,
             the order of elements returned by next should be: [1,1,2,1,1].
Example 2:

Input: [1,[4,[6]]]
Output: [1,4,6]
Explanation: By calling next repeatedly until hasNext returns false,
             the order of elements returned by next should be: [1,4,6].

"""
#`class NestedInteger:
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
        self.flatten_helper(nestedList)

    def flatten_helper(self, nestedList):
        for element in nestedList:

            if element.isInteger():

                self.q.append(element.getInteger())

            else:
                self.flatten_helper(element.getList())

    def next(self) -> int:
        return self.q.popleft()

    def hasNext(self) -> bool:
        return len(self.q) != 0



class NestedIterator:
    def __init__(self, nestedList: [NestedInteger]):
        self.stack = []
        self.stack.append(nestedList)


    def next(self) -> int:
        self.hasNext()
        return self.stack[-1].next().getInteger()


    def hasNext(self) -> bool:
        while len(self.stack) != 0:
            if self.stack[-1].hasNext():
                self.stack.pop()
            else:
                x = self.stack[-1].next()
                if x.isInteger():
                    return self.stack[-1].previous()==x
                self.stack.append(x.getList())
        return False