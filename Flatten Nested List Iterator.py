#  Time Complexity : O(N)
#  Space Complexity : O(1)
#  Did this code successfully run on Leetcode : Yes
#  Three line explanation of solution in plain english : This NestedIterator flattens a nested list of integers into a simple list during initialization using the recursive helper function. The flattened list is stored in self.list, and an index self.idx tracks the current position. next() returns the next integer while advancing the index, and hasNext() checks if more integers remain.

class NestedIterator:
    def __init__(self, nestedList: [NestedInteger]):
        self.list = []
        self.idx = 0
        self.helper(nestedList)
    
    def helper(self, nestedList):
        for ne in nestedList:
            if ne.isInteger():
                self.list.append(ne.getInteger())
            else:
                self.helper(ne.getList())
        
    def next(self) -> int:
        val = self.list[self.idx]
        self.idx += 1
        return val
        
    def hasNext(self) -> bool:
        return self.idx < len(self.list)