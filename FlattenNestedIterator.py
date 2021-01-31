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
from collections import deque
class NestedIterator:
    # Time Complexity - O(N)
    # Space Complexity - O(N)
    # Algorithm - 
    def __init__(self, nestedList: [NestedInteger]):
        self.q=deque() # Stack in which we keep last at first
        self.flatten(nestedList) # Adding the nestedList after first unfolding
    
    def next(self) -> int:
        return self.q.popleft()
    
    def hasNext(self) -> bool: # If integer, then O(1) else O(N) times prepare stack will be called
         return (len(self.q)>0)

    def flatten(self,nestedList): # Appending all element from the list to stack we have used recursion
        for i in nestedList:
            if i.isInteger():
                self.q.append(i.getInteger())
            else:
                self.flatten(i.getList())
         

# Your NestedIterator object will be instantiated and called as such:
# i, v = NestedIterator(nestedList), []
# while i.hasNext(): v.append(i.next())