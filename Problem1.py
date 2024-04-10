'''
1. We use dfs to reach max depth until we find an integer. Everytime we find one, we append it to a stack, else we do dfs on this list.
2. Finally our elements are all in the LIFO order i.e reversed, so to maintain correct order we reverse our elements stack.
3. Now for next operation, it is simply popping the last element from the stack. and hasnext is true until stack has no elements.

TC: O(1) -> for next and hasNext
SC: O(n) -> Stack has all the elements
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
        if not nestedList or len(nestedList) == 0:
            return
        self.stack = []
        self.dfs(nestedList)
        self.stack.reverse()
        
    def next(self) -> int:
        return self.stack.pop()
        
    def hasNext(self) -> bool:
        return len(self.stack) > 0

    def dfs(self, nestedList):        
        for i in nestedList:
            if i.isInteger():
                self.stack.append(i.getInteger())        
                          
            else:
                self.dfs(i.getList())
        
        return 
         

# Your NestedIterator object will be instantiated and called as such:
# i, v = NestedIterator(nestedList), []
# while i.hasNext(): v.append(i.next())