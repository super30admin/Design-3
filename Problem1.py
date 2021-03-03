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
"""
341. Flatten Nested List Iterator
Time Complexity - O(n) n is no of elements in list i.e. considering all nesting
Space Complexity -O(n) n is n = no of integer + no of iterator list
Here I have created a dfs function that will go through elements in given list and if there is nesting it calls itself and goes through that list again and again until it is empty.
next returns top element in queue
hasNext returns if there is next element or not
"""
class NestedIterator:
    def __init__(self, nestedList: [NestedInteger]):
        self.queue = []
        self.dfs(nestedList)
        
    def dfs(self,elems):
        for elem in elems:
            if elem.isInteger():
                self.queue.append(elem.getInteger())
            else:
                self.dfs(elem.getList())
    def next(self) -> int:
        return self.queue.pop(0)
        
    
    def hasNext(self) -> bool:
        return len(self.queue) > 0        

# Your NestedIterator object will be instantiated and called as such:
# i, v = NestedIterator(nestedList), []
# while i.hasNext(): v.append(i.next())