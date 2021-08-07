# Time Complexity : O(L/N)
# Space Complexity : O(n)

class NestedIterator:
    def __init__(self, nestedList):
        self.stack = []
        # Append main Iterator
        self.stack.append(iter(nestedList))

    def next(self) -> int:
        return self.nextElement.getInteger()

    def hasNext(self) -> bool:
        while self.stack:
            try:
                self.nextElement = next(self.st[-1])
            except:
                self.stack.pop()
                continue

            if self.nextElement.isInteger():
                return True
            else:
                self.stack.append(iter(self.nextElement.getList()))
        return False