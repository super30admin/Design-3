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
    # Time and Space is O(N) for all the elements in the list, can have multiple lists inside the lists
    def __init__(self, nestedList: [NestedInteger]):
        def flatten(nested):
            temp = []
            for lis in nested:
                if lis.isInteger():
                    temp.append(lis.getInteger())
                else:
                    temp.extend(flatten(lis.getList()))
            return temp
        self.nest = deque(flatten(nestedList))

    # Time and Space is O(1)
    def next(self) -> int:
        return self.nest.popleft()

    # Time and Space is O(1)
    def hasNext(self) -> bool:
        return self.nest


# Your NestedIterator object will be instantiated and called as such:
# i, v = NestedIterator(nestedList), []
# while i.hasNext(): v.append(i.next())
