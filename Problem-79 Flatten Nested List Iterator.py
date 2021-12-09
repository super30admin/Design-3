# 341. Flatten Nested List Iterator
# https://leetcode.com/problems/flatten-nested-list-iterator/

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

# Logic: We reverse the input and store in the stack. When hasNext() is called, 
# we check if stack has data and if the top element is an int. If it is an 
# integer, we return true. If the top element is a list, we reverse the list 
# and store it in the stack. When next() is called, we pop from the stack and 
# return. hasNext() makes sure that the top element will always be an int.
class NestedIterator:
    # Time Complexity: O(n)
    # Space Complexity: O(n)
    def __init__(self, nestedList: [NestedInteger]):
        self.stack = nestedList[::-1]
    
    # Time Complexity: O(1)
    def next(self) -> int:
        return self.stack.pop()
    
    # Time Complexity: O(n)
    def hasNext(self) -> bool:
        while self.stack and self.stack[-1].getInteger() == None:
            itr = self.stack.pop()
            itr_list = itr.getList()
            
            for i in itr_list[::-1]:
                self.stack.append(i)
        return len(self.stack) > 0
         

# Your NestedIterator object will be instantiated and called as such:
# i, v = NestedIterator(nestedList), []
# while i.hasNext(): v.append(i.next())