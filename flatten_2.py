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
    #controlled recursion using iterators
    def __init__(self, nestedList: [NestedInteger]):
        self.nextint = None
        self.stack = [iter(nestedList)]
    
    def next(self) -> int:
        return self.nextint.getInteger()
    
    def hasNext(self) -> bool:
        while self.stack:
            element = self.stack[-1]
            curr = next(element, None)
            if not curr:
                self.stack.pop()
            elif curr.isInteger():
                self.nextint = curr
                return True
            else:
                self.stack.append(iter(curr.getList()))

        return False
         

# Your NestedIterator object will be instantiated and called as such:
# i, v = NestedIterator(nestedList), []
# while i.hasNext(): v.append(i.next())



#time complexity - O(n), n = length of list

#space complexity - O(n)

#all test cases passed