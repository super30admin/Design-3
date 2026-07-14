## Problem 1: Flatten Nested List Iterator (https://leetcode.com/problems/flatten-nested-list-iterator/)
class NestedIterator:
    def __init__(self, nestedList: [NestedInteger]):
        # Logic: We initialize a stack to hold iterators of the nested lists.
        # Starting with an iterator for the main nestedList.
        # Time Complexity: O(1) - creating an iterator takes constant time.
        # Space Complexity: O(D) - where D is the maximum nesting depth of the list,
        #                   as that is the maximum number of iterators on the stack.
        self.stack = [iter(nestedList)]
        self.nextElement = None
    
    def next(self) -> int:
        # Logic: hasNext() does the heavy lifting of finding the next integer.
        # By the time next() is called, self.nextElement is guaranteed to hold an integer.
        # Time Complexity: O(1)
        # Space Complexity: O(1)
        return self.nextElement.getInteger()
    
    def hasNext(self) -> bool:
        # Logic: We peek at the top iterator on the stack and attempt to grab the next item.
        # - If exhausted, we pop the iterator.
        # - If it's an integer, we pause and return True (ready for next() to consume it).
        # - If it's a list, we convert it to an iterator and push it onto the stack to dive deeper.
        #
        # Time Complexity: Amortized O(1). In the worst case (e.g., deeply nested empty lists [[[]]]), 
        #                  a single call could take O(D) time to unnest. However, since every list 
        #                  is pushed and popped from the stack exactly once across all calls, 
        #                  the average time per call is O(1).
        # Space Complexity: O(D) for the call stack, where D is the maximum nesting depth.
        while self.stack:
            # Advance the iterator at the top of the stack
            self.nextElement = next(self.stack[-1], None)
            
            if self.nextElement is None:
                # Top iterator is exhausted, remove it
                self.stack.pop()
            else:
                if self.nextElement.isInteger():
                    # Found an integer, ready to be consumed
                    return True
                else:
                    # Found a list, unpack it by adding its iterator to the stack
                    self.stack.append(iter(self.nextElement.getList()))
        
        # Stack is empty, no elements remaining
        return False

# Your NestedIterator object will be instantiated and called as such:
# i, v = NestedIterator(nestedList), []
# while i.hasNext(): v.append(i.next())