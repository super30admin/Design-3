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
    def __init__(self, nestedList):
        self.result = []
        self.i = 0
        self.dfs(nestedList)

    def dfs(self, nestedList):
        # Helper function to perform depth-first search on the nested list
        for el in nestedList:
            if el.isInteger():
                self.result.append(el.getInteger())
            else:
                self.dfs(el.getList())

    def next(self):
        # Return the next integer from the result list
        re = self.result[self.i]
        self.i += 1
        return re

    def hasNext(self):
        # Check if there are more integers to be returned from the result list
        return self.i < len(self.result)   
         

# Your NestedIterator object will be instantiated and called as such:
# i, v = NestedIterator(nestedList), []
# while i.hasNext(): v.append(i.next())