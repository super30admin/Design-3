# """
# This is the interface that allows for creating nested lists.
# You should not implement it, or speculate about its implementation
# """
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
from collections import deque


class NestedIterator:
    """
        https://leetcode.com/problems/flatten-nested-list-iterator/
        // Time Complexity : O(n)
            'n' is the total number of elements in the nested list,
            including the nested elements
        // Space Complexity : O(n)
    """

    def __init__(self, nestedList: [NestedInteger]):
        self.queue = deque()
        self._dfs(nestedList)

    def _dfs(self, nestedList):
        for nested_integer in nestedList:
            if nested_integer.isInteger():
                self.queue.append(nested_integer.getInteger())
            else:
                self._dfs(nested_integer.getList())

    def next(self) -> int:
        return self.queue.popleft()

    def hasNext(self) -> bool:
        return len(self.queue) > 0

# Your NestedIterator object will be instantiated and called as such:
# i, v = NestedIterator(nestedList), []
# while i.hasNext(): v.append(i.next())
