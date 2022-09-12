'''
Time Complexity - O(n)
Space Complexity - O(h) where h is height of stack
'''
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
        self.stack = []
        self.stack.append(iter(nestedList))
        self.nextEl = None

    def next(self) -> int:
        value = self.nextEl
        self.nextEl = None
        return value

    def hasNext(self) -> bool:
        while self.stack:
            iterator = self.stack[-1]
            curr = next(iterator, None)
            if curr == None:
                self.stack.pop()
                continue
            currEl = curr
            if currEl.isInteger():
                self.nextEl = currEl.getInteger()
                return True
            else:
                self.stack.append(iter(currEl.getList()))
        return False


# Your NestedIterator object will be instantiated and called as such:
# i, v = NestedIterator(nestedList), []
# while i.hasNext(): v.append(i.next())
