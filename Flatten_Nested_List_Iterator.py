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

# Time and Space Complexity : Written beside each function
class NestedIterator:
    def __init__(self, nestedList: [NestedInteger]): # Takes O(1) time and space
        self.stack = []
        if nestedList != None:
            self.stack.append(iter(nestedList))
        self.nextEl = None
    
    def next(self) -> int: # Takes O(1) time and space
        val = self.nextEl 
        self.nextEl = None # Return nextEl value and make it as none
        return val
    
    def hasNext(self) -> bool: # Takes O(d) time and O(d) space where d is the maximum depth of nesting in the given i/p
        while(self.stack):
            iterator = self.stack[-1] # Take the top of the stack which is an iteator object
            curr = next(iterator,None) # Get the current element in this iterator by calling next on iterator
            
            if curr == None:
                self.stack.pop() # If curr is None, move to the next iterator object in the stack
                continue
                
            curr_el = curr # If curr is not none, store it in curr_e;
            
            if curr_el.isInteger():
                self.nextEl = curr_el.getInteger() # If it is an integer, make this as the nextEl and return true
                return True
            else:
                self.stack.append(iter(curr_el.getList())) # If its not an iteger it is a list. Add it as an iterator object to the stack
            
        return False
         

# Your NestedIterator object will be instantiated and called as such:
# i, v = NestedIterator(nestedList), []
# while i.hasNext(): v.append(i.next())