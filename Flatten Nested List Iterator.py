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
    #Approach: Stack of iterators
    #Time Complexity: O(l / n)
    #Space Complexity: O(d)
    #where l is number of lists and n is the number of integers in the nestedList
    #and, d is the maximum nesting depth
    
    def __init__(self, nestedList: [NestedInteger]):
        self.st = []
        self.st.append(iter(nestedList))
    
    def next(self) -> int:
        return self.nextEl.getInteger()
    
    def hasNext(self) -> bool:
        while self.st:
            try:
                self.nextEl = next(self.st[-1])
            except:
                self.st.pop()
                continue

            if self.nextEl.isInteger():
                return True
            else:
                self.st.append(iter(self.nextEl.getList()))
        return False

"""
from collections import deque
class NestedIterator:
    #Approach: Flattening using recursion
    #Time Complexity: O(1)      // flattening is a one time operation
    #Space Complexity: O(n)
    #where, n is the total number of integers in the nestedList
    
    def __init__(self, nestedList: [NestedInteger]):
        self.de = deque()
        self.flatten(nestedList)
    
    def next(self) -> int:
        return self.de.popleft()
    
    def hasNext(self) -> bool:
        return len(self.de) > 0
    
    def flatten(self, nestedList):
        for el in nestedList:
            if el.isInteger():
                self.de.append(el.getInteger())
            else:
                self.flatten(el.getList())
        return
""" 

# Your NestedIterator object will be instantiated and called as such:
# i, v = NestedIterator(nestedList), []
# while i.hasNext(): v.append(i.next())