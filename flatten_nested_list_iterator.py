# Time Complexity : O(1)
# Space Complexity : O(N+L)
# Did this code successfully run on Leetcode : Yes
# Any problem you faced while coding this : No
class NestedIterator:
    def __init__(self, nestedList):
        self.stack = []
        self.stack.append(iter(nestedList))
        self.next_el = None

    def next(self) -> int:
        return self.next_el.getInteger()

    def hasNext(self) -> bool:
        while self.stack:
            if not self.stack[-1]:
                self.stack.pop()
            else:
                self.next_el = next(self.stack[-1], None)
                if self.next_el is None:
                    self.stack.pop()
                elif self.next_el.isInteger():
                    return True
                else:
                    self.stack.append(iter(self.next_el.getList()))
        return False

# Time Complexity : O(1)
class NestedIterator:
    def __init__(self, nestedList):
        self.li = []
        self.idx = 0
        self.dfs(nestedList)

    def dfs(self, nestedList):
        #logic 
        for el in nestedList:
            if el.isInteger():
                self.li.append(el.getInteger())
            else:
                self.dfs(el.getList())
        
    def next(self) -> int:
        re = self.li[self.idx]
        self.idx+=1
        return re
    
    def hasNext(self) -> bool:
         return self.idx != len(self.li)