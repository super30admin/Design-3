# O(1) TIME AND O(1) SPACE FOR BOTH THE METHODS

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
        self._nums = []
        self.getnums(nestedList)
        print(self._nums)
        self._p = 0
        
    def next(self) -> int:
        temp = self._nums[self._p]
        self._p += 1 
        return temp
    
    def hasNext(self) -> bool:
         return self._p < len(self._nums)
        
    def getnums(self,nest):
        for n in nest:
            if n.isInteger():
                self._nums.append(n.getInteger())
            else:
                self.getnums(n.getList())
        
         

# Your NestedIterator object will be instantiated and called as such:
# i, v = NestedIterator(nestedList), []
# while i.hasNext(): v.append(i.next())