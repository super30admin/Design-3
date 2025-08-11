class NestedIterator:
    def __init__(self, nestedList: [NestedInteger]):
        self.st=[iter(nestedList)]
        self.nextEl=None

    
    def next(self) -> int:
        return self.nextEl.getInteger()
    
    def hasNext(self) -> bool:
        while self.st:
            try:
                nxt= next(self.st[-1])
                if nxt.isInteger():
                    self.nextEl=nxt
                    return True
                else:
                    self.st.append(iter(nxt.getList()))

            except StopIteration:
                self.st.pop()
        
        return False
