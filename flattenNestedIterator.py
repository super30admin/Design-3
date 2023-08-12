#TC - O(N+L)
#SC - O(N+L)
class NestedIterator:
    
    def __init__(self, nestedList: [NestedInteger]):
        self.stack = list(reversed(nestedList))
        
        
    def next(self) -> int:
        self.make_stack_top_an_integer()
        return self.stack.pop().getInteger()
    
        
    def hasNext(self) -> bool:
        self.make_stack_top_an_integer()
        return len(self.stack) > 0
        
        
    def make_stack_top_an_integer(self):
        while self.stack and not self.stack[-1].isInteger():    
            self.stack.extend(reversed(self.stack.pop().getList()))
         

# Your NestedIterator object will be instantiated and called as such:
# i, v = NestedIterator(nestedList), []
# while i.hasNext(): v.append(i.next())