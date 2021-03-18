# Created by Aashish Adhikari at 12:05 PM 2/4/2021

# Although this solutoin passes the test, this is not what we want as a solution. An iterator should only be concerned about
# the next element. Here, we have traversed through all the elements in the constructor itself.

'''
Time Complexity:
O(1) for both hasNext() and next()

Space Complexity:
O(1) for both hasNext() and next()
'''

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
        from collections import deque
        self.flattened_list = deque()

        self.flatten(nestedList)



    def flatten(self, nestedList):

        for elem in nestedList:
            if elem.isInteger():
                self.flattened_list.append(elem.getInteger())
            else:

                self.flatten(elem.getList())

    def next(self):
        """
        :rtype: int
        """

        return self.flattened_list.popleft()



    def hasNext(self):
        """
        :rtype: bool
        """
        if len(self.flattened_list) == 0:
            return False
        return True

# Your NestedIterator object will be instantiated and called as such:
# i, v = NestedIterator(nestedList), []
# while i.hasNext(): v.append(i.next())