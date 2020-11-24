#Time: O(1)
#Space: O(N+L)

class NestedIterator:
    def __init__(self, nestedList: [NestedInteger]):

        self.stack = []
        if (nestedList != None):
            self.stack.append(iter(nestedList))
        self.cursor = None

    def next(self) -> int:

        value = self.cursor
        self.cursor = None
        return value

    def hasNext(self) -> bool:

        while (len(self.stack) > 0 and self.cursor == None):
            iterator = self.stack[-1]
            currentNI = next(iterator, None)

            if (currentNI == None):
                self.stack.pop()
                continue
            nestedInteger = currentNI
            if (nestedInteger.isInteger()):
                self.cursor = nestedInteger.getInteger()
                return True
            else:
                self.stack.append(iter(nestedInteger.getList()))
        return False
