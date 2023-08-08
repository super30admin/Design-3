# Space Complexity: O(N)
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

# Assuming you have defined the NestedInteger class with the required methods

# Assuming you have defined the NestedInteger class with the required methods

class NestedIterator:
    def __init__(self, nestedList):
        self.flatten_list = []
        self.flatten(nestedList)
        self.index = 0

    def flatten(self, nestedList): # O(N)
        for item in nestedList:
            if item.isInteger():
                self.flatten_list.append(item.getInteger())
            else:
                self.flatten(item.getList())

    def next(self): # O(1)
        val = self.flatten_list[self.index]
        self.index += 1
        return val

    def hasNext(self): # O(1)
        return self.index < len(self.flatten_list)