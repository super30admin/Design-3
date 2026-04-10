# Question: Given a nested list of integers, implement an iterator to flatten it.

# The data structure that can handle dynamicity is an iterator itself. 
# So we can use a stack to store the iterators of the nested lists. 
# Now the way we control this stacks adding and removing is we first try to get the next element of the iterator stack that is on the top. If we can't get it then we pop it from the stack and try to get the next element of the new top of the stack.
# and whenever we encounter a nested list, we get that list add an iterator on it and then push it onto the stack and whenever we encounter an integer, we store the value of it for next() function and return true for hasNext(). 
# If the current iterator is exhausted, we pop it from the stack and continue with the next iterator on the stack.

class NestedIterator:
    def __init__(self, nestedList: [NestedInteger]):
        self.stack = [iter(nestedList)]
        self.nextEl = None
    
    def next(self) -> int:
        # Time: O(1)
        return self.nextEl.getInteger()
    
    def hasNext(self) -> bool:
        # Time: O(h) => Depth of the max nesting. Amortised O(1) because we won't be having nesting everywhere
        while self.stack:
            try:
                # We try to get the next element from the current iterator on the top of the stack.
                val = next(self.stack[-1])
            except StopIteration:
                # If the current iterator is exhausted, we pop it from the stack and continue with the next iterator on the stack.
                self.stack.pop()
                continue
            if val.isInteger():
                # If it is an integer, we store it in nextEl for the next call to return correct element and return true.
                self.nextEl = val
                return True
            else:
                # If it is a list, we get an iterator on that list and push it onto the stack.
                self.stack.append(iter(val.getList()))
            
        return False