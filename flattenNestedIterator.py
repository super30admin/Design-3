'''
Time complexity --> O(n)

Space Cmplexity --> O(n) since using queue data struvture to store the element
'''
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
       self.stack = self.dfs(nestedList)
    
    def dfs(self, arr):
        res = []
        for i in arr:
            if i.isInteger():
                res.append(i.getInteger())
            else:
                res.extend(self.dfs(i.getList()))
        return res
    
    
    # def dfs(self, arr):
    #     res = []
    #     for i in arr:
    #         if i.isInteger():
    #             res.append(i.getInteger())
    #         else:
    #             res.extend(self.dfs(i.getList()))
            
    #     return res

    def next(self):
        return self.stack.pop(0)

    def hasNext(self) -> bool:
        return len(self.stack) > 0
         

# Your NestedIterator object will be instantiated and called as such:
# i, v = NestedIterator(nestedList), []
# while i.hasNext(): v.append(i.next())