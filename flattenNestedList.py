# Approach: Use basic recursion approach to flatten the lists
# Not the best way -- it does not actually make use of the iterator concept, since we flatten whole list at one go
# Instead we want to only be concerned about the next immediate element always
# Time : Written in line
# Space - O(N + L)
class NestedIterator:

    def __init__(self, nestedList: [NestedInteger]):
        self.queue = collections.deque()
        self.flattenLists(nestedList)
        
    
    # O(1) Time
    def next(self) -> int:
        #check whether there is a next element
        return self.queue.popleft()
        
    # O(1) Time
    def hasNext(self) -> bool:
        # used to check if the queue is empty or not
        return len(self.queue) > 0

    # O(N + L) since we call this function on lists within nested list times, L being the number of the lists and N number of elements in list
    def flattenLists(self, nestedList):
        
        for element in nestedList:
            if element.isInteger():
                self.queue.append(element.getInteger())

            else:
                self.flattenLists(element.getList())

        return


# Approach - Using an iterator & controlled recursion -- we use stack as data structure
# Time - O( L / N) where L the number of lists and N is thr number of integers in the list
    # next - O(1)
    # hasNext - O(l) - levels of nested list

# Space - O(levels)

''' Approach:
         Store iterators of the list in the stack.
         -> If next of your top is an an integer, this would be your next integer,
             return True.
         -> Else if next of your top is a list, push the iterator of this list to
             the stack.
         -> Else, the list of the top of your stack isn't going to yield anything,
             pop it out of the stack.
'''

class NestedIterator:

    def __init__(self, nestedList: [NestedInteger]):
        self.st = []
        # in BST we had left right pointers, here we cant iterate just simply
        # so create an iterator on the list and add to the stack
        self.st.append(iter(nestedList))

    # O(1)
    def next(self) -> int:
        # also moves pointer to next element egs is [1,4] pointer at 1 first then moves to 4 in this call itself
        return self.nextElement.getInteger()

    # O(l) nested levels
    def hasNext(self) -> bool: # not only checks if stack is empty or not but also moves to next element
        while self.st:
            # try catch so as to check if we have elements left to traverse or we finished doing so
            try:
                self.nextElement = next(self.st[-1]) # peek iterator on stack and apply next on it to get the element
            except:
                # if no elements left on top iterator ie top of stack - remove the iterator as all elements have been processed
                # example - [1,4] on top we finished 1, 4 now pointer is out of bounds, if above if condition failed, that is no more next to call, pop it
                self.st.pop()
                continue

            if self.nextElement.isInteger():
                return True
            else:
                # if element we got was a list, apply iterator on that list and add to stack
                self.st.append(iter(self.nextElement.getList()))
        return False
        
         
     