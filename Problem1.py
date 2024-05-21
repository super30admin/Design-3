# Design-3

## Problem 1: Flatten Nested List Iterator (https://leetcode.com/problems/flatten-nested-list-iterator/)

#Approach
# makeStackTopAnInteger() function contains the algorithm to make the stack top an integer. The top of the stack can be integer or empty
# hasnext() and next() function calls the makeStackTopAnInteger() function. if stack contains items, hasNext() return true else false
# In next() function, if the stack contains items, then the top is an integer.  This integer is popped and returned.

# Time Complexity : Average T.C- O(1)
# Space Complexity : O(N+L)
# Did this code successfully run on Leetcode : Yes
# Any problem you faced while coding this : No


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
         