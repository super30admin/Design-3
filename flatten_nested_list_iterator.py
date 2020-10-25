"""
Problem: 341. Flatten Nested List Iterator
Leetcode: https://leetcode.com/problems/flatten-nested-list-iterator/
Approach: Using Stack
Time Complexity:
N be the total number of integers within the nested list,
L be the total number of lists within the nested list, and
D be the maximum nesting depth (maximum number of lists inside each other)
    For constructor: O(N+L)
    For next(): O(1) if integer, else O(L/N)
    For hasNext(): O(1) if integer, else O(L/N)
Space Complexity: O(N+L)
Does this code run on Leetcode: Yes
"""

# """
# This is the interface that allows for creating nested lists.
# You should not implement it, or speculate about its implementation
#
# class NestedInteger:
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
        self.stack = []
        for i in reversed(range(len(nestedList))):
            self.stack.append(nestedList[i])

    def next(self) -> int:
        temp = None
        if self.hasNext:
            temp = self.stack.pop().getInteger()
        return temp

    def hasNext(self) -> bool:
        while len(self.stack) != 0 and not (self.stack[-1].isInteger()):
            temp = self.stack.pop().getList()
            for i in reversed(range(len(temp))):
                self.stack.append(temp[i])
        return (not (len(self.stack) == 0))

# Your NestedIterator object will be instantiated and called as such:
# i, v = NestedIterator(nestedList), []
# while i.hasNext(): v.append(i.next())