"""
FAANMG Problem #80 {Medium} 

341. Flatten Nested List Iterator



Time complexity : O(n)
Space complexity : O(d) 

Did this code successfully run on Leetcode : Yes


@name: Rahul Govindkumar_RN27JUL2022
""" 

# """
# This is the interface that allows for creating nested lists.
# You should not implement it, or speculate about its implementation
# """
#class NestedInteger:
#    def isInteger(self) -> bool:
#        """
#        @return True if this NestedInteger holds a single integer, rather than a nested list.
#        """
#
#    def getInteger(self) -> int:
#        """
#        @return the single integer that this NestedInteger holds, if it holds a single integer
#        Return None if this NestedInteger holds a nested list
#        """
#
#    def getList(self) -> [NestedInteger]:
#        """
#        @return the nested list that this NestedInteger holds, if it holds a nested list
#        Return None if this NestedInteger holds a single integer
#        """

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
            curr_element = curr

            # we check that if the element at cursor is an integer, then we move the cursor there and return true stating that we have a next element
            if curr_element.isInteger():
                self.cursor = curr_element.getInteger()
                return True
            else:
              # if we do not find an integer then we append the nested list to the stack and iterate over it until it is completed
                self.stack.append(iter(curr_element.getList()))
        # return false when we no longer have integer values
        return False
    

# Your NestedIterator object will be instantiated and called as such:
# i, v = NestedIterator(nestedList), []
# while i.hasNext(): v.append(i.next())