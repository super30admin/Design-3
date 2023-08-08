# Definition for NestedInteger is provided in the question.
# Time Complexity: O(N)
# Space Complexity: O(N)
class NestedIterator:
    def __init__(self, nestedList: [NestedInteger]):
        self.stack = []
        # Flatten the nestedList and store it in reverse order in the stack.
        for i in range(len(nestedList) - 1, -1, -1):
            self.stack.append(nestedList[i])

    def next(self) -> int:
        # Since the stack is already in reverse order, we just pop the last element to get the next integer.
        return self.stack.pop().getInteger()

    def hasNext(self) -> bool:
        # Keep popping elements from the stack until we find an integer.
        while self.stack:
            top = self.stack[-1]
            if top.isInteger():
                return True
            # If the top element is a nested list, flatten it and add its elements to the stack.
            self.stack.pop()
            for i in range(len(top.getList()) - 1, -1, -1):
                self.stack.append(top.getList()[i])
        return False
