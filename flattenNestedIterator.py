# Time Complexity : O(1) 
# Space Complexity : O(1) 
# Did this code successfully run on Leetcode : Yes
# Any problem you faced while coding this : NA
# Approach is to recursively find the elements in every nested integer list and append it to result list.

class NestedIterator:
    def __init__(self, nestedList):
        self.res=[]
        self.i=0
        self.dfs(nestedList)
        
    def dfs(self, li):

        for i in li:
            if(i.isInteger()):
                self.res.append(i.getInteger())
            else:
                self.dfs(i.getList())

    def next(self) -> int:
        r=self.res[self.i]
        self.i+=1
        return r
        
    def hasNext(self) -> bool:
        return self.i<len(self.res)