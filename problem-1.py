# Time Complexity - O(D) where D is the max depth of the nesting made
# Space Complexity - O(D)where D is the max depth of the nesting made

"""
Approach:
Create a iterator object on the list and initialise it in a stack
next funbction will return the value present in self.nextelement
hasnext function will check given the stack is not empty, the next value is a integer or a list, if it is a
integer it will update the value of nextelement and if its a list, it will push the list on the stack
"""

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

        self.stackIteratorNestedList = [(iter(nestedList))]
        self.nextElement = None

    def next(self) -> int:

        return self.nextElement

    def hasNext(self) -> bool:

        while self.stackIteratorNestedList:
            currentElement = self.stackIteratorNestedList[-1]

            try:
                nextElement = next(currentElement)
            except StopIteration:
                self.stackIteratorNestedList.pop()
                continue

            if nextElement.isInteger():
                self.nextElement = nextElement.getInteger()
                return True

            else:
                self.stackIteratorNestedList.append(
                    iter(nextElement.getList()))

        return False
