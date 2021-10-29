# Time Complexity: O(n), where n - number of integers in the list
# Space Complexity: O(n)

class NestedIterator:
    def __init__(self, nestedList: [NestedInteger]):
        self.stack = []
        self.nextElement = None
        # Append the nestedList as an iterator into the stack
        self.stack.append(iter(nestedList))

    def next(self) -> int:
        return self.nextElement.getInteger()

    def hasNext(self) -> bool:
        while self.stack:
            self.nextElement = next(self.stack[-1], None)

            # If there is no next
            if not self.nextElement:
                self.stack.pop()

            # If next is integer
            elif self.nextElement.isInteger():
                return True

            # If next is list
            else:
                self.stack.append(iter(self.nextElement.getList()))

        return False