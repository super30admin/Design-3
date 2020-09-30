# Leetcode problem link : https://leetcode.com/problems/flatten-nested-list-iterator/
# Time Complexity:    O(n)
# Space Complexity:   O(n) for queue
#  Did this code successfully run on Leetcode : Yes
# Any problem you faced while coding this : No

# Your code here along with comments explaining your approach
'''
    1. Create a queue and flatten the nested list as soon as iterator is created
    2. If current element is integer, add to queue else do a recursion call with the current list as nested list

'''
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
        self.queue = collections.deque()
        self.flattenList(nestedList)
    
    def next(self) -> int:
        return self.queue.popleft()
    
    def hasNext(self) -> bool:
        return True if len(self.queue) > 0 else False
    
    def flattenList(self,nestedList):
        if nestedList is None:
            return
        for nested_integer in nestedList:
            if nested_integer.isInteger():
                self.queue.append(nested_integer.getInteger())
            else:
                self.flattenList(nested_integer.getList())
    

# Your NestedIterator object will be instantiated and called as such:
# i, v = NestedIterator(nestedList), []
# while i.hasNext(): v.append(i.next())