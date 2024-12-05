class NestedIterator:
    def __init__(self, nestedList: [NestedInteger]):
        self.stack = []  # Stack to store NestedInteger objects
        self._flatten(nestedList)
    
    def _flatten(self, nestedList):
        for item in reversed(nestedList):
            self.stack.append(item)

    def next(self) -> int:
        if self.hasNext():
            return self.stack.pop().getInteger()
        
    def hasNext(self) -> bool:
        while self.stack:
            top = self.stack[-1]  # Peek at the top of the stack
            if top.isInteger():  # If it's an integer, we're ready
                return True
            else:  # If it's a list, unpack it
                self.stack.pop()  # Remove the list from the stack
                self._flatten(top.getList())
        return False
