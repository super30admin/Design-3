#To flatten the nested iterator, I  used simple list to solve the problem
#Time complexity: O(1)
#Space Complexity: O(n)
class NestedIterator:
    def __init__(self, nestedList: [NestedInteger]):
        self.q = list()
        self.flatten(nestedList)
        
    
    def next(self) -> int:
        return self.q.pop(0)
        
    
    def hasNext(self) -> bool:
        if len(self.q) == 0:
            return False
        else:
            return True
        
    def flatten(self,nestedList):
        for el in nestedList:
            if el.isInteger():
                self.q.append(el.getInteger())
            else:
                self.flatten(el.getList())