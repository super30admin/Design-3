# Time Complexity: O(1)
# Space Complexity: O(2*n)
# Approach: Create a stack of iterators. In the hasNext method, if the next element is an integer - return True, if it is a list - push it on the stack, if it is None - (java equivalent of hasNext - False) - pop.
# """
# This is the interface that allows for creating nested lists.
# You should not implement it, or speculate about its implementation
# """
#class NestedInteger(object):
#    def isInteger(self):
#        """
#        @return True if this NestedInteger holds a single integer, rather than a nested list.
#        :rtype bool
#        """
#
#    def getInteger(self):
#        """
#        @return the single integer that this NestedInteger holds, if it holds a single integer
#        Return None if this NestedInteger holds a nested list
#        :rtype int
#        """
#
#    def getList(self):
#        """
#        @return the nested list that this NestedInteger holds, if it holds a nested list
#        Return None if this NestedInteger holds a single integer
#        :rtype List[NestedInteger]
#        """

class NestedIterator(object):
    
    def __init__(self, nestedList):
        """
        Initialize your data structure here.
        :type nestedList: List[NestedInteger]
        """
        self.stack = []
        self.nextEl = None
        self.stack.append(iter(nestedList))
        

    def next(self):
        """
        :rtype: int
        """
        return self.nextEl.getInteger()
        

    def hasNext(self):
        """
        :rtype: bool
        """
        while self.stack:
            self.nextEl = next(self.stack[-1], None)
            if (self.nextEl == None):
                self.stack.pop()
            elif(self.nextEl.isInteger()):
                return True
            else:
                self.stack.append(iter(self.nextEl.getList()))
            
        

# Your NestedIterator object will be instantiated and called as such:
# i, v = NestedIterator(nestedList), []
# while i.hasNext(): v.append(i.next())