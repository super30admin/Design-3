# Time Complexity : O(L/N)
# Space Complexity : O(n)

class NestedIterator:
    def __init__(self, nestedList: [NestedInteger]):
        self.stack = []
        self.stack.append(iter(nestedList))
        
    
    def next(self) -> int:
        return self.nextitem.getInteger()

    
    def hasNext(self) -> bool:
        while(self.stack):
            #case 1
            try:
                self.nextitem = next(self.stack[-1])
            except:
                self.stack.pop()
                continue
                
            if (self.nextitem).isInteger():
                return True
            
            else:
                self.stack.append(iter(self.nextitem.getList()))
    
        return False