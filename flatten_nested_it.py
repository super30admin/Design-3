class NestedIterator:
    def __init__(self, nestedList: [NestedInteger]):
        self.st = [iter(nestedList)]
        self.nextEl = 0

    def next(self):
        return self.nextEl.getInteger()

    def hasNext(self):
        while self.st:
            self.nextEl = next(self.st[-1], None)
            if self.nextEl is None:
                self.st.pop()
            else:
                if self.nextEl.isInteger():
                    return True
                else:
                    self.st.append(iter(self.nextEl.getList()))
        return False

# Time: Amortized O(1) for hasNext() and O(1) for next().
# Space: O(d), where d is the maximum depth of nested lists