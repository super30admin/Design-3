# time complexity is o(1)
# space complexity is o(1) in next() and hasnext() methods, o(n) stack space in __init__ method, where 'n' is the depth of the nestedList., # eg:[[[4]]] depth is 2

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
    
    nextelement = 0
    def __init__(self, nestedList: [NestedInteger]):
        self.stack = list()
        self.stack.append(iter(nestedList))
        
    def next(self) -> int: #o(1)
        return self.nextelement.getInteger()
        
            
    def hasNext(self) -> bool: #o(1)
        while(len(self.stack) > 0):
            iterator = self.stack[-1]
            curritr = next(iterator, None)
            if(curritr == None):
                self.stack.pop()
                continue
            self.nextelement = curritr
            if(self.nextelement.isInteger()):
                return True
            else:
                self.stack.append(iter(self.nextelement.getList()))
        return False
        

# Your NestedIterator object will be instantiated and called as such:
# i, v = NestedIterator(nestedList), []
# while i.hasNext(): v.append(i.next())