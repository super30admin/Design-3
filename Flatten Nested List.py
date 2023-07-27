# Time complexity : O(1) for next function and O(1) average for the hasnext function
# Space complexity : O(n) to maintain the stack

# The code ran on Leetcode

# Maintain a stack to store nested iterators.

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
        self.nextEl = None
        
    
    def next(self) -> int:
        return self.nextEl.getInteger()
        
    
    def hasNext(self) -> bool:
        while(self.stack):
            if(self.stack[-1].hasNext == False):
                self.stack.pop()
            elif((self.nextEl := self.stack[-1].next()).isInteger()):
                return True
            else:
                self.stack.append(iter(self.nextEl.getList()))
        return False

# Your NestedIterator object will be instantiated and called as such:
# i, v = NestedIterator(nestedList), []
# while i.hasNext(): v.append(i.next())