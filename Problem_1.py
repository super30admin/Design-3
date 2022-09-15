"""
Problem 1: Flatten Nested List Iterator (https://leetcode.com/problems/flatten-nested-list-iterator/)

"""

# Approach - 1

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
        """
        Time Complexity : O(N +L)
        N be the total number of integers within the nested list,
        L be the total number of lists within the nested list
        Space Complexity : O(N + D) D be the maximum nesting depth
        """
        self.idx = -1
        self.res = []
        def dfs(nestedList):
            for elem in nestedList:
                if elem.isInteger():
                    self.res.append(elem.getInteger())
                else:
                    dfs(elem.getList())
        dfs(nestedList)
        
    
    def next(self) -> int:
        """
        TC : O(1)
        """
        self.idx += 1
        return self.res[self.idx]
        
    
    def hasNext(self) -> bool:
        """
        TC : O(1)
        """
        return self.idx + 1 < len(self.res)
         

# Your NestedIterator object will be instantiated and called as such:
# i, v = NestedIterator(nestedList), []
# while i.hasNext(): v.append(i.next())
