#
# @lc app=leetcode id=341 lang=python3
#
# [341] Flatten Nested List Iterator
#

# @lc code=start
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

'''
Time Complexity - O(N). Number of Integers
Space Complexity - O(N) we are using a stack to the iterator

This code works on Leetcode
'''
from collections import deque
class NestedIterator:
    def __init__(self, nestedList: [NestedInteger]):
        self.st = deque()
        self.nextElement = None 
        self.it = iter(nestedList)
        self.st.append(self.it)
    
    def next(self) -> int:
        if self.nextElement.isInteger(): #return the next Element if its integer
            return self.nextElement.getInteger()
        
    
    def hasNext(self) -> bool:
        while self.st:
            self.nextElement = next(self.st[-1], None) #peek at the top of the stack
            if self.nextElement == None:#if we have iterated the list we get none
                self.st.pop() #we pop the current list of NestedIntegers
            elif self.nextElement.isInteger(): #if we have an Integer we return True
                return True
            else:
                iterList = iter(self.nextElement.getList()) #else we allow the iterator to iterate over next nestedIntegers and add it to stack
                self.st.append(iterList)

        return False

# Your NestedIterator object will be instantiated and called as such:
# i, v = NestedIterator(nestedList), []
# while i.hasNext(): v.append(i.next())
# @lc code=end

