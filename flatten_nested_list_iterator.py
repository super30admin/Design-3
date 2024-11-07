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


class NestedIterator:

    def __init__(self, nestedList: [NestedInteger]):
        self.stack = [iter(nestedList)]
        self.nextEl = None

    def next(self) -> int:
        return self.nextEl

    def hasNext(self) -> bool:

        while self.stack:
            iterator = self.stack[-1]
            current = next(iterator, None)

            if current is None:
                self.stack.pop()
            elif current.isInteger():
                self.nextEl = current.getInteger()
                return True
            else:
                self.stack.append(iter(current.getList()))
        return False


# time complexity is O(n)
# space complexity is O(D) where D is the depth of nested structure
# Your NestedIterator object will be instantiated and called as such:
# i, v = NestedIterator(nestedList), []
# while i.hasNext(): v.append(i.next())
