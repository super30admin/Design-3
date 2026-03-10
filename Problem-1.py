# Time Complexity : O(N)
# N = total number of integers across the nested structure
# Space Complexity : O(N)
# Did this code successfully run on Leetcode : Yes
# Any problem you faced while coding this : No

# Explanation:
# 1. Flatten the entire nested list using DFS during initialization.
# 2. Store all integers in a list in the correct order.
# 3. next() returns the next element and hasNext() checks if more elements exist.

class NestedIterator:
    def __init__(self, nestedList: [NestedInteger]):
        self.flat_list = []
        self.index = 0
        
        def dfs(nested):
            for ni in nested:
                if ni.isInteger():
                    self.flat_list.append(ni.getInteger())
                else:
                    dfs(ni.getList())
        
        dfs(nestedList)

    def next(self) -> int:
        val = self.flat_list[self.index]
        self.index += 1
        return val

    def hasNext(self) -> bool:
        return self.index < len(self.flat_list)


# Your NestedIterator object will be instantiated and called as such:
# i, v = NestedIterator(nestedList), []
# while i.hasNext(): 
#     v.append(i.next())