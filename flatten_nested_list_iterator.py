# Time Complexity : O(1) amortized per call to next() and hasNext() (since each element is processed once)
# Space Complexity : O(N), where N is the total number of nested integers across all levels
# Did this code successfully run on Leetcode : Yes
# Any problem you faced while coding this : Yes, had to make sure the stack is processed lazily to preserve efficiency

# I used a stack to hold elements from the nested list, pushing them in reverse order so that I could access them in order.
# In hasNext(), I made sure the top of the stack was an integer before allowing next() to return it.
# If the top was a list, I expanded it in-place until I found an integer (or ran out).

class NestedIterator:
    def __init__(self, nestedList: [NestedInteger]):
        # Initialize the stack with the elements in reverse order
        self.stack = nestedList[::-1]

    def next(self) -> int:
        # Assumes that hasNext() has been called and returned True
        return self.stack.pop().getInteger()

    def hasNext(self) -> bool:
        # Unpack the top of the stack until the top is an integer
        while self.stack:
            top = self.stack[-1]
            if top.isInteger():
                return True
            self.stack.pop()
            self.stack.extend(top.getList()[::-1])  # Push nested list in reverse
        return False
