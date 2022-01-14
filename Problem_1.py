"""
TC - O(n)
sc - O(n)
"""
class NestedIterator:
    def __init__(self, nestedList):
        self.q = []
        self.dfs(nestedList)
    # O(1)
    def next(self) -> int:
        return self.q.pop(0)
    #O(1)
    def hasNext(self) -> bool:
        if len(self.q) > 0:
            return True
        return False
        
    def dfs(self,nl):
        for el in nl:
            if el.isInteger():
                self.q.append(el.getInteger())
            else:
                self.dfs(el.getList())
         