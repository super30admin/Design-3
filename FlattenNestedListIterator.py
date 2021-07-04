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
    """NestedIterator using Iter and next functions
    Time complexity-O(d) where d is the depth of the iterables
    but averagely it will be O(1)
    Space Complexity- O(d) where d is the number of lists as we are using stack to store each list for iterating---Not sure"""
    def __init__(self, nestedList: [NestedInteger]):
        self.nextEl:NestedInteger
        self.arr=[]
        self.arr.append(iter(nestedList))
        
    
    def next(self) -> int:
        return self.nextEl
        
    
    def hasNext(self) -> bool:
        while self.arr:
            nextIter=next(self.arr[-1], None)
            if nextIter==None:
                self.arr.pop()
            elif nextIter.isInteger():
                self.nextEl=nextIter.getInteger()
                return True
            else:
                self.arr.append(iter(nextIter.getList()))
        return False
  

        """Time complexity-O(1) for both next and hasnext
    As we are doing dfs in constructor, we are not bothered about complexity for it
    Space Complexity-O(n1+k) where n1 is the maximum number of elements present in list and k is the number of lists---Not sure"""
#     def __init__(self, nestedList: [NestedInteger]):
#         self.q=deque()
#         self.dfs(nestedList)
    
#     def next(self) -> int:
#         return self.q.popleft()
    
#     def hasNext(self) -> bool:
#         return self.q
        
#     def dfs(self, nestedList):
#         for i in nestedList:
#             # if isinstance(i._integer, int):
#             if i.isInteger():
#                 #can use getList() or _list and getInteger() or _integer
#                 self.q.append(i.getInteger())
#             else:
#                 self.dfs(i._list)

            
            
# Your NestedIterator object will be instantiated and called as such:
# i, v = NestedIterator(nestedList), []
# while i.hasNext(): v.append(i.next())