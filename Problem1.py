'''
Problem: Flatten Nested List Iterator
Time Complexity: O(n+m) for constructor here m is lists and n is integers. For rest it is O(1)
Space Complexity: O(n+m), on stack
Did this code successfully run on Leetcode: Yes
Any problem you faced while coding this: No
Your code here along with comments explaining your approach:
        controlled recurrsion is performed
        put nestedList on stack and return True for hasNext when next element is integer
        else put the nestedList again on stack
'''

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
        self.st = []
        for i in reversed(range(len(nestedList))):
            self.st.append(nestedList[i])
        
    def next(self) -> int:
        return self.st.pop().getInteger()
        
    def hasNext(self) -> bool:
        while self.st:
            if self.st[-1].isInteger():
                return True
            else:
                curr = self.st.pop().getList()
                for i in reversed(range(len(curr))):
                    self.st.append(curr[i])
        return False

# Your NestedIterator object will be instantiated and called as such:
# i, v = NestedIterator(nestedList), []
# while i.hasNext(): v.append(i.next())