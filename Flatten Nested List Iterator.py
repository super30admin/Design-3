""""// Time Complexity : O(1)
// Space Complexity : O(k) where k is the count of list in a nested iterator obj
// Did this code successfully run on Leetcode : Yes
// Any problem you faced while coding this : No
"""


# """
# This is the interface that allows for creating nested lists.
# You should not implement it, or speculate about its implementation
# """
# class NestedInteger:
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
        self.stack = []
        self.stack.append(iter(nestedList))
        self.nextEl = None

    def next(self) -> int:
        return self.nextEl.getInteger()

    def hasNext(self) -> bool:
        '''You call next() repeatedly untill hasNext() return FALSE'''
        while self.stack:
            # get the nextElement
            self.nextEl = next(self.stack[-1], None)
            # chk self.nextEle == None
            if not self.nextEl:
                self.stack.pop()

            # chk self.nextEle == Integer
            elif self.nextEl.isInteger():
                return True
            # chk self.nextEle != Integer
            else:
                self.stack.append(iter(self.nextEl.getList()))
        return False

# class NestedIterator:
#     def __init__(self, nestedList: [NestedInteger]):
#         self.q=deque()
#         self.dfs(nestedList)

#     def next(self) -> int:
#         return self.q.popleft()


#     def hasNext(self) -> bool:
#         return len(self.q)!=0

#     def dfs(self, nestedList):
#         #base
#         #logic

#         for el in nestedList:
#             if el.isInteger():
#                 self.q.append(el)
#             else:
#                 self.dfs(el.getList())


# Your NestedIterator object will be instantiated and called as such:
# i, v = NestedIterator(nestedList), []
# while i.hasNext(): v.append(i.next())