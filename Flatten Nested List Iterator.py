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
        self.stack = [iter(nestedList)] # Keep on appending nested lists to stack           
        self.cursor = None              # Cursor should always point to next valid integer
    
    def next(self) -> int:
        return self.cursor.getInteger()
    
    def hasNext(self) -> bool:
        while self.stack:
            # Using Try-Except Technique to make up for hasNext Method
            try:
                self.cursor = next(self.stack[-1])
                if self.cursor.isInteger():
                    return True
                else:
                    self.stack.append(iter(self.cursor.getList()))
            except:
                self.stack.pop()
        return False

# Your NestedIterator object will be instantiated and called as such:
# i, v = NestedIterator(nestedList), []
# while i.hasNext(): v.append(i.next())

### Complexity Analysis:
# Time Complexity: 
    # Constructor: O(1)
    # hasNext: O(1)
    # Next: O(1) --> Asymptotically

# Space Complexity: O(D) --> Max Depth of Nested Lists.