class NestedIterator:
    def __init__(self, nestedList: [NestedInteger]):
        
        def flatten(nestedList):
            tmp=[]
            for i in nestedList:
                if i.isInteger():
                    tmp.append(i.getInteger())
                else:
                    tmp.extend(flatten(i.getList()))
            return tmp
                    
        self.n=collections.deque(flatten(nestedList))
        
        
    def next(self) -> int:
        return self.n.popleft()
        
    
    def hasNext(self) -> bool:
        return self.n
        
        
    
        
        
         

# Your NestedIterator object will be instantiated and called as such:
# i, v = NestedIterator(nestedList), []
# while i.hasNext(): v.append(i.next())