"""
*THIS IS NOT AN ACCEPTABLE SOLUTION FOR ITERATORS*

TC: hasnext and next - O(1). Constructor - O(n) n is total number of elements in the flattened list
SC: O(n) since we put everything into the queue initially.
"""

from collections import deque
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
        self.q = deque()
        self.dfs(nestedList)

    def next(self) -> int:
        return self.q.popleft()

    def hasNext(self) -> bool:
        if self.q:
            return True
        else:
            return False

    def dfs(self, nestedList):
        for element in nestedList:
            if element.isInteger():
                self.q.append(element)
            else:
                self.dfs(element.getList())

# Your NestedIterator object will be instantiated and called as such:
# i, v = NestedIterator(nestedList), []
# while i.hasNext(): v.append(i.next())

"""
Approach 2 : *ACCEPTABLE SOLUTION* Use stack and native iterator
Since hasNext is always called before next, we write the functions such that a variable nextElement will always store the 
next element to be returned.

TC: O(D) or amortized O(1)
SC: O(D)
where D = max number of nested calls
"""

# approach 2: using stack and native iterator
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
