# Time Complexity : O(n)
# Space Complexity : O(n)
# Did this code successfully run on Leetcode : yes
# Any problem you faced while coding this : no


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


class NestedIterator(object):

    def __init__(self, nestedList):
        self.nextInt = None
        self.store = [iter(nestedList)]

    def next(self):
        return self.nextInt.getInteger()

    def hasNext(self):
        while len(self.store) > 0:

            it = self.store[-1]
            curr = next(it, None)

            if not curr:
                self.store.pop()
            elif curr.isInteger():
                self.nextInt = curr
                return True
            else:
                self.store.append(iter(curr.getList()))

        return False

# Your NestedIterator object will be instantiated and called as such:
# i, v = NestedIterator(nestedList), []
# while i.hasNext(): v.append(i.next())
