#Time Complexity : O(1)
#Space Complexity : O(depth) where depth is the deepest nesting in the main list
#Did this code successfully run on Leetcode : Yes


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
        self.stack = [iter(nestedList)]
        self.pointer = None

    def next(self) -> int:
        curr = self.pointer
        self.pointer = None
        return curr

    def hasNext(self) -> bool:
        while self.stack and self.pointer == None:
            #peek form the stack
            iterator = self.stack[-1]
            currNext = next(iterator, None)

            #if no elements are left to traverse in the list iterator, we can pop the element off
            if currNext == None:
                self.stack.pop()
                continue

            nestedInteger = currNext

            #if we do come across an integer, save the current element so we don't lose it on moving to the next element and return true from there
            if (nestedInteger.isInteger()):
                self.pointer = nestedInteger.getInteger()
                return True
            #if it's a nested list, then we make the list into an iterator and store at the top of the stack
            else:
                self.stack.append(iter(nestedInteger.getList()))

        return False

# Your NestedIterator object will be instantiated and called as such:
# i, v = NestedIterator(nestedList), []
# while i.hasNext(): v.append(i.next())
