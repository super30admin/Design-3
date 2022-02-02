# Time Complexity : O(depth)
# Space Complexity : O(depth)
# Did this code successfully run on Leetcode : yes
# Any problem you faced while coding this : yes

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

    st = deque()
    nextel = None

    def __init__(self, nestedList: [NestedInteger]):

        NestedIterator.st = deque()
        myi = iter(nestedList)
        NestedIterator.st.append(myi)

    def next(self) -> int:
        return NestedIterator.nextel.getInteger()

    def hasNext(self) -> bool:
        while NestedIterator.st:
            iterator = NestedIterator.st[-1]
            curr = next(iterator, None)
            if curr == None:
                NestedIterator.st.pop()
                continue

            NestedIterator.nextel = curr
            if NestedIterator.nextel.isInteger():
                return True
            else:
                NestedIterator.st.append(iter(NestedIterator.nextel.getList()))
        return False


# Your NestedIterator object will be instantiated and called as such:
# i, v = NestedIterator(nestedList), []
# while i.hasNext(): v.append(i.next())