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
    
    """
        
        Student : Shahreen Shahjahan Psyche
        Time :O(N)
        Space:O(N)
        
        Passed All LC Test Cases : Yes
    
    """
    
    
    def __init__(self, nestedList: [NestedInteger]):
        from collections import deque
        self.q = deque()
        self.get_vals(nestedList)
        
    def get_vals(self, nested_list):
        # flattening the data inside a queue
        for i in nested_list:
            if i.isInteger():
                self.q.append(i.getInteger())
            else:
                self.get_vals(i.getList())

    def next(self) -> int:
        return self.q.popleft()
    
    def hasNext(self) -> bool:
        if len(self.q) == 0:
            return False
        return True
         

# Your NestedIterator object will be instantiated and called as such:
# i, v = NestedIterator(nestedList), []
# while i.hasNext(): v.append(i.next())
