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

# Time Complexity: O(1)
# Space Complexity: O(l) #depth of nested list
class NestedIterator:
    def __init__(self, nestedList: [NestedInteger]):
        self.nested = [iter(nestedList)]
        self.nextelement = 0

    def next(self) -> int:
        return self.nextelement.getInteger()

    def hasNext(self) -> bool:
        while self.nested:
            self.nextelement = next(self.nested[-1], None)
            if self.nextelement is None:
                self.nested.pop()
            else:
                if self.nextelement.isInteger():
                    return True
                else:
                    self.nested.append(iter(self.nextelement.getList()))
        return False

# Your NestedIterator object will be instantiated and called as such:
# i, v = NestedIterator(nestedList), []
# while i.hasNext(): v.append(i.next())