#Problem 2: Flatten Nested List Iterator
class NestedIterator:
    def __init__(self, nestedList: [NestedInteger]):
        self.st=[]
        self.st.append(iter(nestedList))
        

    def next(self) -> int:
        #append to stack such that we are
        #pointing to an integer in the top element in stack
        self.hasnext()
        #we return the next element in the top iterator in st
        #integer is a nestedInteger, so make sure to convert
        return self.st[-1].next().getInteger()

    def hasNext(self) -> bool:
        while self.st:
            if not self.st[-1].hasnext():
                self.st.pop()
            else:
                x=self.st[-1].next()
                if x.isInteger():
                    return (self.st[-1].prev==x)
                else:
                    self.st.append(iter(x.getlist()))
