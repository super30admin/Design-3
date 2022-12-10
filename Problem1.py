#Time complexity: O(Nesting Level + Number of elemns)
#Space complexity: O(Maximum depth nested level)

#Accepted on Leetcode

#Approach
#Create a controlled recursion approach by creating an explicit stack -> Each element of the stack will be a dict containing {nestedInteger List, curIndex i.e where we are in that array}
#We know every call of next() is preceeded by hasNext(), so whenever hasNext() is called -> Keep iterating until we are exactly on an integer -> For each element if it is an integer return true -> if it is a nestedList i.e not an integer, put it on top of the stack (with index zero) -> if you have finished iterating over a given list pop it from the stack
#Thus in this way we solve this via controlled recursion -> ensuring each call of hasNext() puts our custom iterator exactly on top of an integer  



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
        self.nextEl = None #will be a nestedInteger object
        self.stack = [{'elem': nestedList, 'index':0}]
        
    
    def next(self) -> int:
        return self.nextEl.getInteger()
        
    
    def hasNext(self) -> bool:
        while len(self.stack):
            #is an iter obj
            stackTopElem = self.stack[-1]
            curIndex = stackTopElem['index']
            self.nextEl = None if curIndex >= len(stackTopElem['elem']) else stackTopElem['elem'][curIndex]
            stackTopElem['index'] += 1
            if self.nextEl == None:
                self.stack.pop()
            elif self.nextEl.isInteger():
                return True
            else: #is a nestedInteger list
                self.stack.append({'elem':self.nextEl.getList(), 'index':0})
        
        return False
            
            

         

# Your NestedIterator object will be instantiated and called as such:
# i, v = NestedIterator(nestedList), []
# while i.hasNext(): v.append(i.next())