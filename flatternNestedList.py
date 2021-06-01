class NestedIterator(object):
    stack=None
    El=None

    def __init__(self, nestedList):

        self.stack=[]

        self.stack.append(iter(nestedList))


    def next(self):
        """
        :rtype: int
        """
        return (self.nextEl.getInteger())

    def hasNext(self):
        """
        :rtype: bool
        """
        while self.stack:
            self.El=next(self.stack[-1],None)
            if(self.El==None):
                self.stack.pop()
            else:
                if(self.El.isInteger()):
                    return True
                else:
                    self.stack.append(iter(self.El.getList()))
        return False