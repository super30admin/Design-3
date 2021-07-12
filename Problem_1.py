class NestedIterator:
    def __init__(self, nestedList):
        self.stack = []
        while nestedList:
            a = nestedList.pop()
            print(a)
            self.stack.append(a)

    def next(self):
        return self.stack.pop().getInteger()     

    def hasNext(self):
        while self.stack:
            top = self.stack[-1]
            if top.isInteger():
                return True
            self.stack = self.stack[:-1] + top.getList()[::-1]
        return False
    
    