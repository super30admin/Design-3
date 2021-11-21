#Time O(n), space O(n)
class NestedIterator:
    def __init__(self, nestedList: [NestedInteger]):
        self.stack= nestedList[::]
        
    def next(self) -> int:
        return self.stack.pop(0).getInteger()
        
    def hasNext(self) -> bool:
        
        while self.stack:
            top=self.stack[0]
            
            if top.isInteger():
                return True
            self.stack = top.getList() + self.stack[1:] 
            
        return False
