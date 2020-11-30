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

class NestedIterator:
    def __init__(self, nestedList):
        #   initialize a stack to maintain all iterators of nestedLists in order, also a cursor
        self.stack = []
        if (nestedList != None):                    #   push the main iterator to the stack
            self.stack.append(iter(nestedList))
        self.cursor = None
    def next(self):
        #   just extract the int inside the cursor and make cursor null
        value = self.cursor
        self.cursor = None
        return value
    def hasNext(self):
        #   iterate until the stack is empty and cursor doesn't contain any int
        while (len(self.stack) > 0 and self.cursor == None):
            iterator = self.stack[-1]               #   peek the stack's top iterator
            currentNI = next(iterator, None)        #   replacement for hasNext() and store it in a variable, otherwise might fall
                                                    #   into a trap of calling next() twice which skips one element forward.
            if (currentNI == None):                #   if iter doesn't have next element => pop that iterator
                self.stack.pop()
                continue
            nestedInteger = currentNI              #   else reinitialize to current object (nestedInteger)
            if (nestedInteger.isInteger()):        #    if integer => move cursor to this int and return true
                self.cursor = nestedInteger.getInteger()
                return True
            else:                                  #   else push the iterator of list of nestedInteger
                self.stack.append(iter(nestedInteger.getList()))
        return False

# Your NestedIterator object will be instantiated and called as such:
# i, v = NestedIterator(nestedList), []
# while i.hasNext(): v.append(i.next())

#time, space-O(n)