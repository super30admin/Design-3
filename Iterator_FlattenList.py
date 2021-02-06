# Recursion
# It does not actually make use of the iterator concept
# TC: O(N+L) for flatten list func rest operations - O(1)
# SC: O(N+L)
# Go accross all elements(integers and lists), append them to a queue to handle hasnext and next operations with queue.
from collections import deque
class NestedIterator:
    def __init__(self, nestedList):
        self.queue = deque()
        self.flattenLists(nestedList)
    def next(self) -> int:
        return self.queue.popleft()
    def hasNext(self) -> bool:
        return len(self.queue) > 0
    # O(N + L) since we call this function on lists within nested list times, L being the number of the lists and N number of elements in list
    def flattenLists(self, nestedList):
        for element in nestedList:
            if element.isInteger():
                self.queue.append(element.getInteger())
            else:
                self.flattenLists(element.getList())
        return


# Iterator 
# TC: O(L/N) where L the number of lists and N is number of integers in the list
class NestedIterator:
    def __init__(self, nestedList):
        self.stack = []
        # Append main Iterator 
        self.stack.append(iter(nestedList))

    def next(self) -> int:
        return self.nextElement.getInteger()

    def hasNext(self) -> bool:
        # Run the while loop until stack is not empty
        # Motive: To store next element as well as ascertain if next elements exist
        while self.stack:
            # Move the next pointer of iterator, if end of iterator- pop the iterator from stack
            try:
                self.nextElement = next(self.st[-1])
            except:
                self.stack.pop()
                # we completed a inner list- we move to next elements in the stack
                continue
            
            # if integer- return True(Note: Existence of list as well is True but we need to assign next element value before ending iteration)
            if self.nextElement.isInteger():
                return True
            else:
                # append inner list to stack for extraction
                self.stack.append(iter(self.nextElement.getList()))
        return False