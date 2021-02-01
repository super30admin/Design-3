# Time Complexity : O(N)
# Space Complexity : O(N)
# Did this code successfully run on Leetcode : Yes
# Any problem you faced while coding this : No.. but I need to try iterator approach. This is not an appropriate 
# solution. 


# Your code here along with comments explaining your approach


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
        self.queue = deque()
        self.flatten(nestedList)
        
    
    def next(self) -> int:
        return self.queue.popleft() 
        
    def hasNext(self) -> bool:
        return len(self.queue) > 0
    
    def flatten(self, nestedList):
        for el in nestedList:
            if el.isInteger():
                self.queue.append(el.getInteger())
            else:
                self.flatten(el.getList())
         

# Your NestedIterator object will be instantiated and called as such:
# i, v = NestedIterator(nestedList), []
# while i.hasNext(): v.append(i.next())