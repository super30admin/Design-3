'''
Solution:
1.  Maintain a stack to maintain all iterators of nestedLists in order, also a cursor which is set inside hasNext() always.
2.  Directly use cursor's value to get next int as next() is always called after hasNext()
3.  Stack all iterators in order in hasNext() function
hasNext()--
Time Complexity:    O(n)    |   Space Complexity:   O(n) where n is maximum number of nestings inside main nestedInteger
Constant Time and Space for next() method
--- Passed all testcases successfully on Leetcode. '''

# This is the interface that allows for creating nested lists.
# You should not implement it, or speculate about its implementation
# """
# class NestedInteger:
#    def isInteger(self) -> bool:
#        """
#        @return True if this NestedInteger holds a single integer, rather than a nested list.
#        """

#    def getInteger(self) -> int:
#        """
#        @return the single integer that this NestedInteger holds, if it holds a single integer
#        Return None if this NestedInteger holds a nested list
#        """

#    def getList(self) -> [NestedInteger]:
#        """
#        @return the nested list that this NestedInteger holds, if it holds a nested list
#        Return None if this NestedInteger holds a single integer


class NestedIterator:
    def __init__(self, nestedList: [NestedInteger]):
        
        self.stack = []
        
        if (nestedList != None):
            self.stack.append(iter(nestedList))
        
        self.nextEl = None
    
    def next(self) -> int:
        value = self.nextEl
        self.nextEl = None
        return value
    
    def hasNext(self) -> bool:
        
        while(len(self.stack) > 0 and self.nextEl == None):
            iterator = self.stack[-1]
            currentNI = next(iterator, None)
            
            if(currentNI == None):
                self.stack.pop()
                continue
            
            nestedInteger = currentNI
            
            if (nestedInteger.isInteger()):
                self.nextEl = nestedInteger.getInteger()
                return True
            
            else:
                self.stack.append(iter(nestedInteger.getList()))
        
        return False
                
                
            
         
# #Your NestedIterator object will be instantiated and called as such:
# i, v = NestedIterator(nestedList), []
# while i.hasNext(): v.append(i.next())