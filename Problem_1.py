"""
Time Complexity : O(n) where n is the length of nestedlist
Space Complexity : O(n) 
Did this code successfully run on Leetcode : Yes
Any problem you faced while coding this : No


Your code here along with comments explaining your approach
We will use iterators in Python for this solution. We need to maintain a stack or a list for storing the
nestedList as an iterator. Also, we maintain a global element for storing the current integer value.
Firstly, the hasnext method runs while we still have the stack. We peek to top of the stack and store 
in current variable the next value in form of an iterator. If that iterator can be converted into an integer, the
global element is updated and tRUE IS RETURNED. In case it is not an integer, we get the list out of
it, convert it to an iterator and push to the stack. In case, there is no next value anymore, the top 
value is popped.
"""


class NestedIterator:
    def __init__(self, nestedList: [NestedInteger]):
        self.nextElement = None
        self.stack = [iter(nestedList)]

    def next(self) -> int:
        return self.nextElement.getInteger()

    def hasNext(self) -> bool:
        while self.stack:
            iterator = self.stack[-1]
            curr = next(iterator, None)
            if not curr:
                self.stack.pop()
            elif curr.isInteger():
                self.nextElement = curr
                return True
            else:
                self.stack.append(iter(curr.getList()))
