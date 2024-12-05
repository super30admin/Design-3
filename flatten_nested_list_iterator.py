# Explain your approach in briefly only at top of your code
# Approach:
# The NestedIterator class flattens a nested list of integers using an explicit stack for depth-first traversal.
# The stack stores elements from the nested list in reverse order. The hasNext method ensures that the next 
# element is an integer by unrolling nested lists as needed. The next method simply retrieves the next integer.

# Time Complexity : O(n), where n is the total number of integers in the nested list.
# Space Complexity : O(n) for the stack, in the worst case where all elements are integers.
# Did this code successfully run on Leetcode : Yes
# Any problem you faced while coding this : None

# """
# This is the interface that allows for creating nested lists.
# You should not implement it, or speculate about its implementation
# """
# class NestedInteger:
#     def isInteger(self) -> bool:
#         """
#         @return True if this NestedInteger holds a single integer, rather than a nested list.
#         """
#
#     def getInteger(self) -> int:
#         """
#         @return the single integer that this NestedInteger holds, if it holds a single integer
#         Return None if this NestedInteger holds a nested list
#         """
#
#     def getList(self) -> [NestedInteger]:
#         """
#         @return the nested list that this NestedInteger holds, if it holds a nested list
#         Return None if this NestedInteger holds a single integer
#         """

class NestedIterator:
    def __init__(self, nestedList: [NestedInteger]):
        # Initialize a stack with elements of nestedList in reverse order
        self.stack = nestedList[::-1]
    
    def next(self) -> int:
        # Pop and return the next integer from the stack
        return self.stack.pop().getInteger()
    
    def hasNext(self) -> bool:
        # Ensure the stack's top element is an integer
        while self.stack:
            top = self.stack[-1]
            if top.isInteger():  # If top is an integer, hasNext is true
                return True
            # If top is a list, unroll it by replacing it with its elements
            self.stack.pop()
            self.stack.extend(top.getList()[::-1])  # Add elements in reverse order
        return False

# Your NestedIterator object will be instantiated and called as such:
# i, v = NestedIterator(nestedList), []
# while i.hasNext(): v.append(i.next())
