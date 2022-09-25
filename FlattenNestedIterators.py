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
        if nestedList:
            self.currVal = None
            self.stack = [iter(nestedList)]
            
        
    
    def next(self) -> int:  # TC O(1) 
        v = self.currVal
        self.currVal = None
        return v
    
    def hasNext(self) -> bool:  # TC O(1)
        while self.stack and not self.currVal:
            peek = self.stack[-1]  
            nestedInt = next(peek, None)

            if nestedInt is None: 
                self.stack.pop()
                continue

            if nestedInt.isInteger():
                self.currVal = nestedInt.getInteger()
                return True

            self.stack.append(iter(nestedInt.getList()))
        return False
        
         

# Your NestedIterator object will be instantiated and called as such:
# i, v = NestedIterator(nestedList), []
# while i.hasNext(): v.append(i.next())