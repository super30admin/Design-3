
class NestedIterator:
    def __init__(self, nestedList: [NestedInteger]):
        self.stack = [iter(nestedList)]
        self.nextEl = None

    def next(self) -> int:
        return self.nextEl.getInteger()

    def hasNext(self) -> bool:
        while self.stack:
            top = self.stack[-1]
            try:
                nextEl = next(top)
            except StopIteration:
                self.stack.pop()
                continue

            if nextEl.isInteger():
                self.nextEl = nextEl
                return True
            else:
                self.stack.append(iter(nextEl.getList()))
        
        return False
