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
    def __init__(self, nestedList: [NestedInteger]):
        self.queue=deque()
       
        self.helper(nestedList)#Flattening list happening here
    def next(self) -> int:
        if self.hasNext():
            return self.queue.popleft()
        
    def hasNext(self) -> bool:
        if len(self.queue)>0:
            return True
        return False
         
    def helper(self,cur):
        
        for each in cur:
            if each.isInteger():
                self.queue.append(each.getInteger())
            else:
                self.helper(each.getList())
    #Time O(n), n is number of elecments in nestedlist as we are flattening all elements
    #We can optimize this too
    #Space O(n)
                
        
# Your NestedIterator object will be instantiated and called as such:
# i, v = NestedIterator(nestedList), []
# while i.hasNext(): v.append(i.next())
