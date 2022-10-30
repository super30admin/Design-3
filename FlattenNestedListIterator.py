##Time Complexity : O(n + l)
##Space Complexity : O(n) 
##Did this code successfully run on Leetcode : Yes
class NestedIterator(object):
    
    def __init__(self, nestedList):
        """
        Initialize your data structure here.
        :type nestedList: List[NestedInteger]
        """
        self.stack = []
        while nestedList:
            self.stack.append(nestedList.pop())
        

    def next(self):
        """
        :rtype: int
        """
        return self.stack.pop().getInteger()   
        

    def hasNext(self):
        """
        :rtype: bool
        """
        while self.stack:
            top = self.stack[-1]
            if top.isInteger():
                return True
            self.stack = self.stack[:-1] + top.getList()[::-1]
        return False