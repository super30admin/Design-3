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
    # Time Complexity: O(n)
    # Space Complexity: O(n)
    def __init__(self, nestedList: [NestedInteger]):

        self.st = []
        self.st.append(iter(nestedList))
        self.nextele = None

    def next(self) -> int:
        return self.nextele.getInteger()

    def hasNext(self) -> bool:
        while self.st:
            self.nextele = next(self.st[-1],
                                None)  # Since Python does not have hasNext, can use default value parameter to get None if there is no next
            if self.nextele == None:
                self.st.pop()

            elif self.nextele.isInteger():
                return True

            else:
                self.st.append(iter(self.nextele.getList()))
        return False

# Your NestedIterator object will be instantiated and called as such:
# i, v = NestedIterator(nestedList), []
# while i.hasNext(): v.append(i.next())