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
        self.stack.append((nestedList, 0))

    def next(self):
        curr, curr_index = self.stack.pop()
        if curr_index + 1 < len(curr):
            self.stack.append((curr, curr_index + 1))
        return curr[curr_index].getInteger()

    def hasNext(self):
        while self.stack:
            curr, curr_index = self.stack[-1]
            # this means that the current index is at the end of the list
            if curr_index == len(curr):
                self.stack.pop() 
            # check if the current element is an integer
            elif curr[curr_index].isInteger():
                return True
            else: 
                self.stack[-1] = (curr, curr_index + 1)
                self.stack.append((curr[curr_index].getList(), 0))
        return False
         

# Your NestedIterator object will be instantiated and called as such:
# i, v = NestedIterator(nestedList), []
# while i.hasNext(): v.append(i.next())

'''
Time Complexity: 
next: O(1)
hasNext: O(1)
'''