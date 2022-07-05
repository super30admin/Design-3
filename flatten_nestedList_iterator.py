# Time Complexity : O(n); n --> total number of NestedIntegers in the list
# Space Complexity : O(d); d --> depth of largest NestedInteger.
# Did this code successfully run on Leetcode : Yes
# Any problem you faced while coding this : No
#
#
# Your code here along with comments explaining your approach

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
    # iterator -- process one element at a time
    # we should do controlled recursion for any kind of iterator
    # so, use a stack
    # append native iterator on given nestedList -- why?
    # because iterator processes one at a time and doesn't care about rest
    # Since we are having nestedList(type->class) in a stack, we are not actually storing it, we are storing its reference
    # if somebody changes in nestedList while we are processing something, it changes in our stack also since it is a reference
    # a data-structure in a data-structure is a reference.
    # [1, [2, 3], 4, [5, 6]], if we are at 1 and user changes 4 to 14,
    # it changes in our stack because it is not actually integer, it is type NestedInteger
    def __init__(self, nestedList):
        self.stack = []
        self.stack.append(iter(nestedList))
        self.result = None  # --> for storing the output given by the native iterator on nestedList

    def next(self) -> int:
        return self.result

    def hasNext(self) -> bool:
        while self.stack:
            # this gives the nestedInteger from native iterator in the stack
            # given sentinal is None, if next stops iteration it gives None
            current = next(self.stack[-1], None)
            # if current is None, which means native iterator on the nestedInteger on the top of stack stopped iteration
            # so pop that nestedInterger.
            if current is None:
                self.stack.pop()
            # if current is integer, get that integer and store it in result and return True.
            # else, that nestedInteger must be a list, extract that and append the native iterator of it into the stack
            elif current.isInteger():
                self.result = current.getInteger()
                return True
            else:
                self.stack.append(iter(current.getList()))
        return False

# Your NestedIterator object will be instantiated and called as such:
# i, v = NestedIterator(nestedList), []
# while i.hasNext(): v.append(i.next())


# not an iterator at all
# class NestedIterator:
#     def helper(self, nestedList):
#         # logic
#         for i in range(len(nestedList)):
#             if nestedList[i].isInteger():
#                 self.result.append(nestedList[i].getInteger())
#             else:
#                 self.helper(nestedList[i].getList())

#     def __init__(self, nestedList: [NestedInteger]):
#         self.result = deque()
#         self.helper(nestedList)

#     def next(self) -> int:
#         return self.result.popleft()

#     def hasNext(self) -> bool:
#          return len(self.result) != 0
