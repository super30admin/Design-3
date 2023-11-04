# // Time Complexity :O(depth)
# // Space Complexity :O(dpth)
# // Did this code successfully run on Leetcode :Yes
# // Any problem you faced while coding this :No

# implemented both methods using dfs and stack. using stack could actually adapt the dynamic properties of an iterator.
# we push the iterators in to the stack and we take the top of the stack and move to the next element of the top of stack. this way
# we recurse over just the current and next element in the nested integer list
class NestedInteger(object):
   def isInteger(self):
       """
       @return True if this NestedInteger holds a single integer, rather than a nested list.
       :rtype bool
       """

   def getInteger(self):
       """
       @return the single integer that this NestedInteger holds, if it holds a single integer
       Return None if this NestedInteger holds a nested list
       :rtype int
       """

   def getList(self):
       """
       @return the nested list that this NestedInteger holds, if it holds a nested list
       Return None if this NestedInteger holds a single integer
       :rtype List[NestedInteger]
       """

# class NestedIterator(object):
#     def dfs(self,nestedList):
#         for el in nestedList:
#             if(el.isInteger()):
#                 self.li.append(el.getInteger())
#             else:
#                 self.dfs(el.getList())

    
#     def __init__(self, nestedList):
#         """
#         Initialize your data structure here.
#         :type nestedList: List[NestedInteger]
#         """
#         self.li=[]
#         self.idx=0
#         self.dfs(nestedList)

    
        

#     def next(self):
#         """
#         :rtype: int
#         """
#         toreturn = self.li[self.idx]
#         self.idx+=1
#         return toreturn
        

#     def hasNext(self):
#         """
#         :rtype: bool
#         """
#         return self.idx!=len(self.li)

class NestedIterator(object):
    def __init__(self, nestedList):
        """
        Initialize your data structure here.
        :type nestedList: List[NestedInteger]
        """
        self.stack = []
        self.flatten(nestedList)

    def flatten(self, nestedList):
        for i in range(len(nestedList) - 1, -1, -1):
            self.stack.append(nestedList[i])

    def next(self):
        """
        :rtype: int
        """
        if self.hasNext():
            return self.stack.pop().getInteger()
        return None

    def hasNext(self):
        """
        :rtype: bool
        """
        while self.stack:
            if self.stack[-1].isInteger():
                return True
            nestedList = self.stack.pop().getList()
            self.flatten(nestedList)
        return False
        

# Your NestedIterator object will be instantiated and called as such:
# i, v = NestedIterator(nestedList), []
# while i.hasNext(): v.append(i.next())