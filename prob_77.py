#leetcode 77
# """
# This is the interface that allows for creating nested lists.
# You should not implement it, or speculate about its implementation
# """
# class NestedInteger(object):
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
    q = None  # initialising queue in the class

    def __init__(self, nestedList):
        NestedIterator.q = []
        """
        Initialize your data structure here.
        :type nestedList: List[NestedInteger]
        """
        self.fillqueu(nestedList)

    def next(self):
        """
        :rtype: int
        """
        return NestedIterator.q.pop(0)

    def hasNext(self):
        """
        :rtype: bool
        """
        if NestedIterator.q:
            return True
        else:
            return False

    def fillqueu(self, nested_list):  # extra recursive function made by me
        for i in nested_list:
            if i.isInteger():
                NestedIterator.q.append(i.getInteger())
            else:
                self.fillqueu(i.getList())

# Your NestedIterator object will be instantiated and called as such:
# i, v = NestedIterator(nestedList), []
# while i.hasNext(): v.append(i.next())