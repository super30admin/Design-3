# Time Complexity :average O(1)
# Space Complexity :O(n)
# Did this code successfully run on Leetcode : yes
# Any problem you faced while coding this : no


# Your code here along with comments explaining your approach
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
        #stack
        self.stack = []
        #next element
        self.nextel = None
        #insert nested list as iterator
        self.stack.append(iter(nestedList))

    def next(self):
        """
        :rtype: int
        """
        #before doing next has next is always called
        #return next element
        return self.nextel
        

    def hasNext(self):
        """
        :rtype: bool
        """
        # while there is anything in stack
        while self.stack:
            #get last iterator in stack
            iterator = self.stack[-1]
            #check its next element if there is none then put none in current
            current = next(iterator,None)
            # if this list is finihed pop it from stack and go to beggining of loop
            if not current:
                self.stack.pop()
                continue
            # if element is Integer
            elif current.isInteger():
                #next element is this integer
                self.nextel = current.getInteger()
                # return True
                return True
            # if next element is list append list to stack
            else:
                self.stack.append(iter(current.getList()))
        # if empty stack return False
        return False

# Your NestedIterator object will be instantiated and called as such:
# i, v = NestedIterator(nestedList), []
# while i.hasNext(): v.append(i.next())