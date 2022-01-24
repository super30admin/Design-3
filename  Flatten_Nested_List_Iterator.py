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
        self.nestedLists = nestedList
        self.nums = []
        self.i = 0
        self.flatten(self.nestedLists)
        
    def flatten(self,temp):
        for nestedlist in temp:
            if nestedlist.isInteger():
                # print(nestedlist.getInteger())
                self.nums.append(nestedlist.getInteger())
            else:
                self.flatten(nestedlist.getList())
        return
            
            
            
    
    def next(self) -> int:
        # while not self.hashNest:
        # ret = self.nums[i]
        self.i += 1
        return self.nums[self.i-1]
        
            
    
    def hasNext(self) -> bool:
        if self.i < len(self.nums):
            return True

# Your NestedIterator object will be instantiated and called as such:
# i, v = NestedIterator(nestedList), []
# while i.hasNext(): v.append(i.next())