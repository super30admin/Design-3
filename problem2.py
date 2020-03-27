'''
Time Complexity: O(n)
Space Complexity: O(n)
Did this code successfully run on Leetcode : Yes
Explanation: Create a stack to save all the iterators. Cursor points to the element currently in the stack. For hasNext
iterate through the stack, as we move through the stack we check if the top of the stock has a next element if it does
not we just pop it from the stack else we check if the element at the top is an integer if it is we set cursor to it.
If its a list then we create an iterator for the list and put the list inside the stack. Next Iteration we get the
individual value of the element in the list and return it to the user.
'''

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

    def __init__(self, nestedList):
        """
        Initialize your data structure here.
        :type nestedList: List[NestedInteger]
        """
        self.stack = []
        self.cursor = None
        if nestedList != None:
            self.stack.append(iter(nestedList))

    def next(self):
        """
        :rtype: int
        """
        value = self.cursor
        self.cursor = None
        # print(value)
        return value

    def hasNext(self):
        """
        :rtype: bool
        """
        while len(self.stack) > 0 and self.cursor == None:
            iterator = self.stack[-1]
            hasNext = next(iterator, None)
            if hasNext == None:
                self.stack.pop()
                continue

            nestedInteger = hasNext
            if nestedInteger.isInteger():
                self.cursor = nestedInteger.getInteger()
                return True
            else:
                self.stack.append(iter(nestedInteger.getList()))

        return False

# Your NestedIterator object will be instantiated and called as such:
# i, v = NestedIterator(nestedList), []
# while i.hasNext(): v.append(i.next())