# Time Complexity: O(N)
# Space Complexity: O(N)

# """
# This is the interface that allows for creating nested lists.
# You should not implement it, or speculate about its implementation
# """
# class NestedInteger:
#    def isInteger(self) -> bool:
#        """
#        @return True if this NestedInteger holds a single integer, rather than a nested list.
#        """
#    def getInteger(self) -> int:
#        """
#        @return the integer in the NestedInteger, if it holds a single integer
#        Return None if this NestedInteger holds a nested list
#        """
#    def getList(self) -> [NestedInteger]:
#        """
#        @return the nested list that this NestedInteger holds, if it holds a nested list
#        Return None if this NestedInteger holds a single integer
#        """

class NestedIterator:
    def __init__(self, nestedList: [NestedInteger]):
        self.stack = []
        self.flatten(nestedList)

    def flatten(self, nestedList):
        for nestedInteger in reversed(nestedList):
            if nestedInteger.isInteger():
                self.stack.append(nestedInteger.getInteger())
            else:
                self.flatten(nestedInteger.getList())

    def next(self) -> int:
        return self.stack.pop()

    def hasNext(self) -> bool:
        return len(self.stack) > 0

