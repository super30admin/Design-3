# Time complexity : O(n)
# Space complexity : O(d) 
# Leetcode : Solved and submitted
class NestedIterator:
    def __init__(self, nestedList: [NestedInteger]):
        # declaring all the default values 
        self.stack = []
        
        # checking if the nestedList is not none, if so then we place the whole list as iter object into the stack
        if nestedList != None:
            self.stack.append(iter(nestedList))
        # starting the cursor from None as it does not point to anything right now
        self.cursor = None
    
    def next(self) -> int:
        # to fetch the next value, which will be on the cursor
        value = self.cursor
        # put the cursor as None and return the value
        self.cursor = None
        return value
    
    def hasNext(self) -> bool:
       # we execute the while until the stack is not empty and also we do not encounter an end of the list, which means we have to 
        # traverse till the depth and make all the elements even nested ones into a flat list
        while len(self.stack) > 0 and self.cursor == None:
          
            # fetch the top element of the stack
            iterator = self.stack[-1]
            
            # making the curr as the first element of the element that we peeked
            curr = next(iterator, None)
            
            # if the current element is none, which means we have travered all the elements from the stack, then simply pop the top of stack
            # and continue
            if curr == None:
                self.stack.pop()
                continue
            
            # store the curr into another variable
            curr_el = curr
            
            # we check that if the element at cursor is an integer, then we move the cursor there and return true stating that we have a next element
            if curr_el.isInteger():
                self.cursor = curr_el.getInteger()
                return True
            else:
              # if we do not find an integer then we append the nested list to the stack and iterate over it until it is completed
                self.stack.append(iter(curr_el.getList()))
        # return false when we no longer have integer values
        return False
