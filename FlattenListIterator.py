#Time Complexity: O(1)
#Space Complexity: O(n) n-Length of nestedInteger

class NestedIterator:
    def __init__(self, nestedList: [NestedInteger]):
        self.pointer = None #holds the value of integer when integer is found
        self.stack = [iter(nestedList)]
        
    def next(self) -> int:
        value = self.pointer
        self.pointer = None
        return value
    
    def hasNext(self) -> bool:
        while self.stack and self.pointer is None:
            peek = self.stack[-1]
            current = next(peek,None)
            if current is None:
                self.stack.pop()
                continue
            if current.isInteger():
                self.pointer = current
                return True
            else:
                self.stack.append(iter(current.getList()))
        return False