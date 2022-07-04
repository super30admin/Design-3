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
        # TC O(1) Pushing a list onto a stack is by reference. This means that instead of creating a new list, 
        # some information about how to get to the existing list is put onto the stack. The list is not traversed, 
        # as it doesn't need reversing this time, and we're not pushing the items on one-by-one

        if nestedList:
            self.currVal = None
            self.stack = [iter(nestedList)]
            
        
    
    def next(self) -> int:  # TC and SC both O(1) 
        v = self.currVal
        self.currVal = None
        return v
    
    def hasNext(self) -> bool:  # TC O(1) in avg, worst case O(D) where D is depth of the list, SC O(D)
        while self.stack and not self.currVal:
            peek = self.stack[-1]  # we got ourselves an iterator
            nestedInt = next(peek, None)

            if nestedInt is None: # meaning we didn't get a valid value for iterator.next()
                self.stack.pop()  # iterator is exhausted and no longer useful, pop it
                continue

            if nestedInt.isInteger():
                self.currVal = nestedInt.getInteger()  # set value
                return True

            # if we didn't return, we have a List in nestedInt,so add it with its own iterator to stack
            self.stack.append(iter(nestedInt.getList()))
        return False
                

# Your NestedIterator object will be instantiated and called as such:
# i, v = NestedIterator(nestedList), []
# while i.hasNext(): v.append(i.next())