#Time complexity: O(n); n = no. of nested integers
#Space complexity: O(d); d = depth of largest nested integer
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
        self.st = []
        self.nextEle = None
        self.st.append(iter(nestedList))
        
    
    def next(self) -> int:
        return self.nextEle.getInteger()
        
    
    def hasNext(self) -> bool:
        while self.st:
            self.nextEle = next(self.st[-1], None)
            if not self.nextEle:
                self.st.pop()
            elif self.nextEle.isInteger():
                return True
            else:
                self.st.append(iter(self.nextEle.getList()))
        return False
        
         

# Your NestedIterator object will be instantiated and called as such:
# i, v = NestedIterator(nestedList), []
# while i.hasNext(): v.append(i.next())
