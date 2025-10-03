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

'''
Solution : using stack<iterator<NestedInteger>> and native iterator on list
    - Peek in the stack and store next element (of type NestedInteger) in a global variable 'nextElement'
    - 3 cases for HashNext:
        - iterator on top of stack has completed
            - pop the top of stack
        - 'nextElement' has integer
            - return True
        - 'nextElement' has list
            - get the list from 'nextElement', create a new native iterator on this list
              and stack it up
Time complexity: 
    - hasNext: O(h) , h = maximum depth of the NestedInteger  in the NestedList
Space complexity:
    - hasNext: O(N), N = total nestedInteger in NestedList. we create iterator 
               on each of them
'''
class NestedIterator:
    def __init__(self, nestedList: [NestedInteger]):
        self.stack = [] # type -> stack<iterator<NestedInteger>> 
        self.stack.append(iter(nestedList)) 
        self.nextElement = NestedInteger() # this will store Next element, right now creating a dummy
    
    def next(self) -> int:
        return self.nextElement.getInteger()
    
    def hasNext(self) -> bool:
        while self.stack:
            self.nextElement = next(self.stack[-1],None) # stack.peek.next 
            if self.nextElement is None:
                # iterator has completed on current NestedInteger 
                self.stack.pop()
            elif self.nextElement.isInteger():
                # we have an integer 
                return True
            else:
                # extract the list from the nextElement (which is of type NestedInteger)
                # and create a new iterator on this List and add this iterator
                # to the stack
                self.stack.append(iter(self.nextElement.getList())) 

        return False


# Your NestedIterator object will be instantiated and called as such:
# i, v = NestedIterator(nestedList), []
# while i.hasNext(): v.append(i.next())