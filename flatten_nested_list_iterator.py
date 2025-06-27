# Time Complexity : O(1) for next() and hasNext(), amortized over n total elements
# Space Complexity : O(n), in the worst case if all elements are integers
# Did this code successfully run on Leetcode : Yes
# Any problem you faced while coding this : No

# Your code here along with comments explaining your approach
# I used a stack to store the nested list elements. We process elements lazily,
# only expanding nested lists when hasNext() is called. This keeps memory usage efficient.

class NestedIterator:
    def __init__(self, nestedList: [NestedInteger]):
        self.stack = nestedList[::-1]  # Reverse for stack-like access (LIFO)

    def next(self) -> int:
        # Assumes hasNext() was just called and ensured top is integer
        return self.stack.pop().getInteger()

    def hasNext(self) -> bool:
        while self.stack:
            top = self.stack[-1]
            if top.isInteger():
                return True
            self.stack.pop()
            self.stack.extend(top.getList()[::-1])  # Push list elements in reverse order
        return False

