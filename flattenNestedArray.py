# TC: O(D) 
# SC: O(D) 
# Did this code successfully run on Leetcode : Yes
# Any problem you faced while coding this : No

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
        self.stack = deque()
        self.stack.append(iter(nestedList))
        self.nextElement = None

    def next(self) -> int:
        return self.nextElement

    def hasNext(self) -> bool:
        while (self.stack):
            next_ = next(self.stack[-1], False)
            # case 1: if not next element
            if not next_:
                self.stack.pop()

            # case 2: if next element is integer

            elif next_.isInteger():
                self.nextElement = next_.getInteger()
                return True

            # case 3: if next element if List
            else:
                self.stack.append(iter(next_.getList()))

        return False

# Your NestedIterator object will be instantiated and called as such:
# i, v = NestedIterator(nestedList), []
# while i.hasNext(): v.append(i.next())