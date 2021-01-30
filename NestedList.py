'''
    Time Complexity:
        O(1) for next()
        O(l) for hasNext() (where l = max number of nested lists)

    Space Complexity:
        O(l) (where l = max number of nested lists)

    Did this code successfully run on LeetCode?:
        Yes

    Problems faced while coding this:
        None

    Approach:
        Store iterators of the list in the stack.
        -> If next of your top is an an integer, this would be your next integer,
            return True.
        -> Else if next of your top is a list, push the iterator of this list to
            the stack.
        -> Else, the list of the top of your stack isn't going to yield anything,
            pop it out of the stack.
'''

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
        self.next_element = None

    def next(self) -> int:
        return self.next_element.getInteger()

    def hasNext(self) -> bool:
        while self.stack:
            top = self.stack[-1]
            nxt = next(top, None)

            if not nxt:
                self.stack.pop()
            elif nxt.isInteger():
                self.next_element = nxt
                return True
            else:
                nxt_list = nxt.getList()
                self.stack.append(iter(nxt_list))

        return False


# Your NestedIterator object will be instantiated and called as such:
# i, v = NestedIterator(nestedList), []
# while i.hasNext(): v.append(i.next())
