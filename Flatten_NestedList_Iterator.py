# Time Complexity : O(1), O(M)
# Space Complexity : O(M)
# Did this code successfully run on Leetcode : Yes
# Any problem you faced while coding this : No

# Your code here along with comments explaining your approach
# Instead of flattening the entire nested list upfront, we flatten elements only when needed.
# __init__() initializes the stack in reverse order.
# hasNext() while the stack is not empty we check if the top element is an Integer then return True else expands nested lists until an integer reaches the top.
# next() pops and returns that integer if hasNext is True.

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
        for i in range(len(nestedList)-1, -1, -1):
            self.stack.append(nestedList[i])
    
    def next(self) -> int:
        if self.hasNext():
            return self.stack.pop().getInteger()
    
    def hasNext(self) -> bool:
        while self.stack:
            if self.stack[-1].isInteger():
                return True
            else:
                li = self.stack.pop().getList()
                for i in range(len(li)-1, -1, -1):
                    self.stack.append(li[i])
        return False

         

# Your NestedIterator object will be instantiated and called as such:
# i, v = NestedIterator(nestedList), []
# while i.hasNext(): v.append(i.next())

class NestedIterator:
    def __init__(self, nestedList: [NestedInteger]):
        self.st = [iter(nestedList)]
        self.nextEl = None

    def next(self) -> int:
        return self.nextEl.getInteger()
        # self.nextEl = None
        # return e

    def hasNext(self) -> bool:
        # if self.nextEl:
        #     return True
        while self.st:
            self.nextEl = next(self.st[-1], None)
            if self.nextEl is None:
                self.st.pop()
            else:
                if self.nextEl.isInteger():
                    return True
                else:
                    self.st.append(iter(self.nextEl.getList()))
        return False
