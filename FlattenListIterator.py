class NestedIterator:
    def __init__(self, nestedList: [NestedInteger]):
        self.st = [nestedList]
        self.nextVal = [0]
    
    def addIntegerToFront(self):
        while(len(self.st)>0 and self.nextVal[-1]>=len(self.st[-1])):
                self.nextVal.pop()
                self.st.pop()
        while(len(self.st)>0) and (not self.st[-1][self.nextVal[-1]].isInteger()):
            
            v = self.st[-1][self.nextVal[-1]].getList()
            
            if(len(v)>0):
                self.st.append(v)
                self.nextVal.append(0)
                self.nextVal[-2] += 1
            else:
                self.nextVal[-1] += 1
                while(len(self.st)>0 and self.nextVal[-1]>=len(self.st[-1])):
                    self.nextVal.pop()
                    self.st.pop()
                
    def next(self) -> int:
        self.addIntegerToFront()
        self.nextVal[-1] += 1
        return self.st[-1][self.nextVal[-1]-1].getInteger()
                
    def hasNext(self) -> bool:
        self.addIntegerToFront()
        if(len(self.nextVal)==0):
            return False
        else:
            return True
