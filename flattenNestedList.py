# """
# This is the interface that allows for creating nested lists.
# You should not implement it, or speculate about its implementation
# """
#class NestedInteger:
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
        if self.nextEl is None:
            self.hasNext()
        temp = self.nextEl
        self.nextEl = None
        return temp

    def hasNext(self) -> bool:
        while self.stack:
            iterator = self.stack[-1]
            nested_integer = next(iterator, None)
            if nested_integer is None:
                self.stack.pop()
            elif nested_integer.isInteger():
                self.nextEl = nested_integer.getInteger()
                return True
            else:
                self.stack.append(iter(nested_integer.getList()))
        return False


         

# Your NestedIterator object will be instantiated and called as such:
# i, v = NestedIterator(nestedList), []
# while i.hasNext(): v.append(i.next())