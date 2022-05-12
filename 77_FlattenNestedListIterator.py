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
'''
 Accepted on leetcode(341)
 time - O(N), space - O(N)
Taking a queue and filling it will flatted array elements by using a recursive method and then popping out elements from queue to show final output.
'''


class NestedIterator(object):

    def __init__(self, nestedList):
        """
        Initialize your data structure here.
        :type nestedList: List[NestedInteger]
        """
        self.queue = []
        self.fillQueue(nestedList)

    # this method is used to pop all elements from the queue to show final output.
    def next(self):
        """
        :rtype: int
        """
        return self.queue.pop(0)

    # this method is used to check if the iterator has next element or not.
    def hasNext(self):
        """
        :rtype: bool
        """
        return len(self.queue) > 0

    # this method is used to iterate over the nested list and check if it is integer append to queue or else recurse through the method to get integers out from nested list.
    def fillQueue(self, nestedList):
        for i in nestedList:
            if i.isInteger():
                self.queue.append(i.getInteger())
            else:
                self.fillQueue(i.getList())

# Your NestedIterator object will be instantiated and called as such:
# i, v = NestedIterator(nestedList), []
# while i.hasNext(): v.append(i.next())