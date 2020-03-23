// Did this code successfully run on Leetcode : Yes
// Any problem you faced while coding this : None


// Your code here along with comments explaining your approach
we add the given nested list as iterator into the stack and try to traverse each element one by one and if it is a list then it is converted to a iterator and then added to the stack.If the element is a single integer element then we return the element.

# Time complexity --> next() and hasnext() are done in o(1)
# space complexity --> o(number of elements in the input array)
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
        self.stack=[]
        list_iter=iter(nestedList)
        self.stack.append(list_iter)
        self.val=None

    def next(self):
        """
        :rtype: int
        """
        val=self.val
        self.val=None
        return val

    def hasNext(self):
        """
        :rtype: bool
        """
        while len(self.stack)>0:
            try:
                x=self.stack[-1].next()
                if x.isInteger():
                    self.val=x
                    return True
                self.stack.append(iter(x.getList())) 
            except:
                self.stack.pop()
        return False

# Your NestedIterator object will be instantiated and called as such:
# i, v = NestedIterator(nestedList), []
# while i.hasNext(): v.append(i.next())