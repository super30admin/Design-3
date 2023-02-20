# Time Complexity :- O(k) where k is the depth of list  for hasNext
#Space Complexity :- O(k) where it depends  on depth of list  for hasNext

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
        self.stack = []
        self.stack.append(iter(nestedList))
        self.nextElement = None
        
    
    def next(self) -> int:
        return self.nextElement.getInteger()
        
    
    def hasNext(self) -> bool:
        while(len(self.stack) > 0):
            self.nextElement = next(self.stack[-1], None)
            if self.nextElement == None:
                self.stack.pop()
                continue
            if(self.nextElement.isInteger()):
                return True 
            else:
                self.stack.append(iter(self.nextElement.getList()))
         

# Your NestedIterator object will be instantiated and called as such:
# i, v = NestedIterator(nestedList), []
# while i.hasNext(): v.append(i.next())