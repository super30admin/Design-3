"""
Problem : 1

Time Complexity : O(n)
Space Complexity : O(n)

Did this code successfully run on Leetcode : Yes
Any problem you faced while coding this : No

Iterating over the nestedList, checking if the element is integer, if yes, appending to result stack,
if no, then getting its list form and recursively iterating the list, to get all the elements of
integer type and using a global iterator to check if the next element exists in the result stack, by
checking if its value fall within the range of stack's size


"""

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
        self.result=[]
        self.it=0
        self.dfs(nestedList)
    def dfs(self,nestedList):
        for el in nestedList:
            if el.isInteger():
                self.result.append(el.getInteger())
            else:
                self.dfs(el.getList())

    def next(self):
        """
        :rtype: int
        """
        num=self.result[self.it]
        self.it+=1
        return num

        

    def hasNext(self):
        """
        :rtype: bool
        """
        return self.it<len(self.result)
        

# Your NestedIterator object will be instantiated and called as such:
# i, v = NestedIterator(nestedList), []
# while i.hasNext(): v.append(i.next())