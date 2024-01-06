"""
This is a brute force way and is not accepted as iterator should worry about the next element without iterating over all the elements in advance.
This technique iterates over all the elements in first place and stores the input elements in the queue. 

Time Complexity: O(1) for next() and on an average O(1) for hasnext() but in worst case it is a depth of the nested list
Space Complexity: o(1)

Accepted on LeetCode: Yes
"""

from collections import deque
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


class NestedIterator:
    def __init__(self, nestedList: [NestedInteger]):
        self.queue = deque()
        self.dfs(nestedList)

    def dfs(self, nestedList):
        for el in nestedList:
            if el.isInteger():
                self.queue.append(el.getInteger())
            else:
                self.dfs(el.getList())

    def next(self) -> int:
        return self.queue.popleft()

    def hasNext(self) -> bool:
        return len(self.queue) > 0


# Your NestedIterator object will be instantiated and called as such:
# i, v = NestedIterator(nestedList), []
# while i.hasNext(): v.append(i.next())
