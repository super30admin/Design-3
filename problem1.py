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

# Space Complexity: O(1) amortized
# Time Complexity: O(1) amortized
class NestedIterator:
    def __init__(self, nestedList: [NestedInteger]):

        self.st = [iter(nestedList)]
        self.nextEl = None
        
    
    def next(self) -> int:
        return self.nextEl.getInteger()
        
    def hasNext(self) -> bool:
        while self.st: 
            try: 
                curr = next(self.st[-1])
            except: 
                self.st.pop()
                continue

            if curr.isInteger(): 
                self.nextEl = curr
                return True

            else: 
                self.st.append(iter(curr.getList()))
        return False
         

# Your NestedIterator object will be instantiated and called as such:
# i, v = NestedIterator(nestedList), []
# while i.hasNext(): v.append(i.next())
