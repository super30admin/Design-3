class NestedIterator:
    def __init__(self, nestedList):
        self.st = [iter(nestedList)]
        self.nextEl = None

    def next(self):
        return self.nextEl.getInteger()

    def hasNext(self):
        while self.st:
            self.nextEl = next(self.st[-1], None)

            if self.nextEl is None:
                self.st.pop()

            elif self.nextEl.isInteger():
                return True

            else:
                self.st.append(iter(self.nextEl.getList()))

        return False

    # TC - amortized O(1)
    # SC - O(d) where d is the stack size