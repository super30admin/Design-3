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
#Space complexity: O(N+L)
class NestedIterator(object):
    def __init__(self, nestedList):
        """
        Initialize your data structure here.
        :type nestedList: List[NestedInteger]
        """
        #time complexity: O(1)
        self.st = [iter(nestedList)]
        self.nextVal = None
    # time: O(1)
    def next(self):
        """
        :rtype: int
        """
        return self.nextVal
    # Time: O(D) where d is the depth of nesting
    def hasNext(self):
        """
        :rtype: bool
        """
        #time complexity: O(d) where d is depth of nested list
        # space : O(l)
        while self.st:
            #save next as there is no hasNext in python
            result = next(self.st[-1],None)
            # if next returns none, just pop from list
            if result is None:
                self.st.pop()
            else:
                # if not none, save result
                self.nextVal = result
                #if integer, update nextval and return
                if result.isInteger():
                    self.nextVal = result.getInteger()
                    return True
                else:
                    # if list, add back to the stack
                    self.st.append(iter(result.getList()))
        return False

# Your NestedIterator object will be instantiated and called as such:
# i, v = NestedIterator(nestedList), []
# while i.hasNext(): v.append(i.next())
