# Time: O(1)
# Space: O(1)


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
        self.st = list()
        self.st.append(iter(nestedList))
        self.next_ele = None
    def next(self) -> int:
        return self.next_ele.getInteger()
    
    def hasNext(self) -> bool:
         
        while len(self.st) != 0:
            
            self.next_ele = next(self.st[-1],None)
            if self.next_ele == None:
                self.st.pop()
            
            elif self.next_ele.isInteger():
                return True
            else:
                self.st.append(iter(self.next_ele.getList()))
        
        return False
# Your NestedIterator object will be instantiated and called as such:
# i, v = NestedIterator(nestedList), []
# while i.hasNext(): v.append(i.next())