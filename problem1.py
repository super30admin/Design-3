#Time Complexity :  O(N )
#Space Complexity :O(N)
#Did this code successfully run on Leetcode : yes

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
    def __init__(self, nestedList: [NestedInteger]):
        self.flatten_list = self.helper(nestedList)
        self.index = 0
    
    def helper(self, nestedList):
        result = []
        for item in nestedList:
            if item.isInteger():
                result.append(item.getInteger())
            else:
                result.extend(self.helper(item.getList()))
        return result
        
    
    def next(self) -> int:
        if self.hasNext():
            value = self.flatten_list[self.index]
            self.index += 1
            return value
        
    
    def hasNext(self) -> bool:
        return self.index < len(self.flatten_list)

         

# Your NestedIterator object will be instantiated and called as such:
# i, v = NestedIterator(nestedList), []
# while i.hasNext(): v.append(i.next())