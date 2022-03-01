# """
# This is the interface that allows for creating nested lists.
# You should not implement it, or speculate about its implementation
# """
# class NestedInteger:
#    def isInteger(self) -> bool:
#        """
#        @return True if this NestedInteger holds a single integer, rather than a nested list.
#        """
#
#    def getInteger(self) -> int:
#        """
#        @return the single integer that this NestedInteger holds, if it holds a single integer
#        Return None if this NestedInteger holds a nested list
#        """
#
#    def getList(self) -> [NestedInteger]:
#        """
#        @return the nested list that this NestedInteger holds, if it holds a nested list
#        Return None if this NestedInteger holds a single integer
#        """


# TC = O(1) for put and get
# SC = o(n) where n is depth of nested array


class NestedIterator:
    def __init__(self, nestedList: [NestedInteger]):
        self.stack = []
        self.nextEl = None
        self.stack.append(iter(nestedList))

    def next(self) -> int:
        return self.nextEl.getInteger()

    def hasNext(self) -> bool:
        while self.stack:
            self.nextEl = next(self.stack[-1], None)
            if self.nextEl is None:
                self.stack.pop()
            elif self.nextEl.isInteger():
                return True
            else:
                self.stack.append(iter(self.nextEl.getList()))

        return False

# Your NestedIterator object will be instantiated and called as such:
# i, v = NestedIterator(nestedList), []
# while i.hasNext(): v.append(i.next())