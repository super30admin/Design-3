# Time Complexity : O(m) no of integers
# Space Complexity : O(m) size 
# Did this code successfully run on Leetcode : Yes
# Any problem you faced while coding this : No

class NestedIterator(object):
    
    
    
    def __init__(self, nestedList):
        """
        Initialize your data structure here.
        :type nestedList: List[NestedInteger]
        """
        
        self.queue = []
        self.dfs(nestedList)
        

    def next(self):  #O(1)
        """
        :rtype: int
        """
        return self.queue.pop(0)

    def hasNext(self):  # O(1)
        """
        :rtype: bool
        """
        return len(self.queue) > 0
    
    def dfs(self, nestedList):
            if nestedList is None:
                return

            for data in nestedList:
                if data.isInteger():

                    self.queue.append(data.getInteger())
                else:
                    self.dfs(data.getList())

