#Flatten Nested List Iterator

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

# Time Complexity -> hasNext -> O(1), next -> Ammortized O(1) since each element is traversed only once,
#  __init__ -> O(d)  to find the 1st integer depeding on the depth
# Space Complexity -> stackSpace ->O(d) max depth in the give nested list
# Logic -> push the iterator in the stack, everytime nested list is encountered keep pushing in the stack until integer is found
# Once nested list is finished pop out the nested list and advance to find next integer if any

# Recursive Approach
class NestedIterator:
    # iteratorIndex = [0]
    def __init__(self, nestedList: [NestedInteger]):
            self.iteratorStack = [iter(nestedList)]
            self.nextInteger = None
            self.advance()


    def advance(self):
        # if self.iteratorStack[-1].hasNext():
        nextElement = next(self.iteratorStack[-1], None)
        if nextElement != None:
            if nextElement.isInteger():
                self.nextInteger = nextElement.getInteger()
                return
            else:
                self.iteratorStack.append(iter(nextElement.getList()))
                self.advance()
        else:
            self.iteratorStack.pop()
            if self.iteratorStack:
                self.advance()
            else:
                self.nextInteger = None
        
    def next(self) -> int:
        result = self.nextInteger
        self.advance()
        return result
        
    
    def hasNext(self) -> bool:
          return self.nextInteger != None


# Iterative approachclass NestedIterator:
    def __init__(self, nestedList: [NestedInteger]):
            self.iteratorStack = [iter(nestedList)]
            self.nextInteger = None
            self.advance()


    def advance(self):
        while self.iteratorStack:
            nextElement = next(self.iteratorStack[-1], None)
            if nextElement != None:
                if nextElement.isInteger():
                    self.nextInteger = nextElement.getInteger()
                    return
                else:
                    self.iteratorStack.append(iter(nextElement.getList()))
            else:
                self.iteratorStack.pop()
        self.nextInteger = None
        
    def next(self) -> int:
        result = self.nextInteger
        self.advance()
        return result
        
    
    def hasNext(self) -> bool:
          return self.nextInteger != None     

# Your NestedIterator object will be instantiated and called as such:
# i, v = NestedIterator(nestedList), []
# while i.hasNext(): v.append(i.next())