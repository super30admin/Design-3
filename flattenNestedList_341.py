# Time Complexity : O(n)
# Space Complexity : O(n)
# Did this code successfully run on Leetcode : Yes 
# Any problem you faced while coding this : No

# Approach:

# We want to convert all of them into a single list and then go through the list
# Two ways to go about it (depends on interviewer):
# We can initialise iterator with O(n), which will make our hasNext() and next() -> O(1)
# or, we can initialise with O(1), with next() and hasNext() -> O(n) 

# Below implementation uses queue and recursion:
# We'll recurse through each element in the list, check if its a list or int. 
# If its an int, we add it to queue, else
# if its a list we make the recursive call.

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
        self.queue = []
        self.recur(nestedList)
    
    def next(self) -> int:
        return self.queue.pop(0)
        
    def hasNext(self) -> bool:
        if self.queue:
            return True
        return False
        
    def recur(self, nestedList):
        if nestedList is None:
            return
        
        for item in nestedList:
            if item.isInteger():
                self.queue.append(item)
            else:
                self.recur(item.getList())
         

# Your NestedIterator object will be instantiated and called as such:
# i, v = NestedIterator(nestedList), []
# while i.hasNext(): v.append(i.next())