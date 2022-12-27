class NestedIterator(object):

    def __init__(self, nestedList):
        self.stack = []
        for i in range(len(nestedList) - 1, -1, -1):
            self.stack.append(nestedList[i])
        

    def next(self):
        if self.hasNext() :
            return self.stack.pop().getInteger()
        return None
        

    def hasNext(self):
        if len(self.stack) == 0:
            return False
        while not self.stack[-1].isInteger():
            nestedList = self.stack.pop().getList()
            for i in range(len(nestedList) - 1, -1, -1):
                self.stack.append(nestedList[i])
            if len(self.stack) == 0:
                return False
        return True
