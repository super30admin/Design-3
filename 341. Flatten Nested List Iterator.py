# T = O(1)
# S = O(1)

# Approach:
# 1.Push the original iterator to a stack
# 2.Whenever the iterator inside the main array hits a list then take it and push that to the stack
# 3.leave the parent array and start iterating the sub array once done pop and return back

class NestedIterator:
    def __init__(self, nestedList: [NestedInteger]):
        self.Stack = []
        self.Stack.append(iter(nestedList))
    
    def next(self) -> int:
        return self.nextElement.getInteger()
    
    def hasNext(self) -> bool:
        while self.Stack:
            try:
                self.nextElement = next(self.Stack[-1])
            except:
                self.Stack.pop()
                continue
            if self.nextElement.isInteger():
                return True
            else:
                self.Stack.append(iter(self.nextElement.getList()))
        return False 

# Your NestedIterator object will be instantiated and called as such:
# i, v = NestedIterator(nestedList), []
# while i.hasNext(): v.append(i.next())