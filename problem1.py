#TIME AND SPACE Complexity O(1)

class NestedIterator:
    def __init__(self, nestedList: [NestedInteger]):

        self.stack = []

        if (nestedList != None):
            self.stack.append(iter(nestedList))

        self.nextEl = None

    def next(self) -> int:
        value = self.nextEl
        self.nextEl = None
        return value

    def hasNext(self) -> bool:

        while(len(self.stack) > 0 and self.nextEl == None):
            iterator = self.stack[-1]
            currentNI = next(iterator, None)

            if(currentNI == None):
                self.stack.pop()
                continue

            nestedInteger = currentNI

            if (nestedInteger.isInteger()):
                self.nextEl = nestedInteger.getInteger()
                return True

            else:
                self.stack.append(iter(nestedInteger.getList()))

        return False
