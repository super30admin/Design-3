# Time Complexity - O(n), length all integers in the nexted list
# Space Complexity - O(n)


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
     #python don't have hasnext() method. so only using try, except
    def __init__(self, nestedList: [NestedInteger]):
        self.stack = []
        self.stack.append(iter(nestedList))
        self.nextEl = None
       
    
    def next(self) -> int:
        return self.nextEl.getInteger()
        
    
    def hasNext(self) -> bool:
        while self.stack:
            try:
                self.nextEl = next(self.stack[-1])
                if self.nextEl.isInteger():
                    return True
                else:
                    self.stack.append(iter(self.nextEl.getList()))
            except StopIteration:
                self.stack.pop()
        return False


         

# Your NestedIterator object will be instantiated and called as such:
# i, v = NestedIterator(nestedList), []
# while i.hasNext(): v.append(i.next())