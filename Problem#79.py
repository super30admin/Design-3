'''
341. Flatten Nested List Iterator
Problem Link: https://leetcode.com/problems/flatten-nested-list-iterator/description/
Description:
You are given a nested list of integers nestedList. Each element is either an integer or a list whose elements may also be integers or other lists.
Implement an iterator to flatten it.
Implement the NestedIterator class:
- NestedIterator(List<NestedInteger> nestedList) Initializes the iterator with the nested list nestedList.
- int next() Returns the next integer in the nested list.
- boolean hasNext() Returns true if there are still some integers in the nested list and false otherwise.
Note that you may assume that all calls to next() are valid. That is, there are at least some more integers in the nested list when hasNext() is called.
'''

# Time Complexity : O(1) for next() and hasNext() operations
# Space Complexity : O(n) for storing the flattened list
# Did this code successfully run on Leetcode : Yes
# Any problem you faced while coding this : Yes, handling nested lists can be tricky, especially when they are deeply nested.

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

class NestedIterator: # humne ek NestedIterator class banaya hai jo ki nested list ko flatten karega (we have created a NestedIterator class that will flatten the nested list)
    '''
    Intuition:
    We can use a stack to keep track of the nested lists and flatten them as we iterate through the elements.
    The stack will help us manage the nested structure and allow us to access the next integer efficiently.
    '''

    def __init__(self, nestedList: [NestedInteger]): # idhar hum constructor banate hain jo ki nested list ko initialize karega (here we create a constructor that will initialize the nested list)
        self.stack = [] # ye stack hai jo ki nested elements ko store karega (this is a stack that will store the nested elements)
        self.index = 0 # ye index hai jo ki current position ko track karega (this is an index that will track the current position)
        self._flatten(nestedList) # hum nested list ko flatten karte hain (we flatten the nested list)
    
    def next(self) -> int: # ye method next integer ko return karega (this method will return the next integer)
        self.hasNext()  # ye check karega ki next element hai ya nahi (this will check if there is a next element)
        result = self.stack[self.index] # hum stack se current element ko result me store karte hain (we store the current element from the stack in result)
        self.index += 1 # aur index ko increment karte hain (and we increment the index)
        return result # return result
    
    def hasNext(self) -> bool: # ye method check karega ki next element hai ya nahi (this method will check if there is a next element)
        while self.index >= len(self.stack): # jab tak index stack ke length se zyada hai tab tak (while the index is greater than or equal to the length of the stack)
            if not self.stack: # agar stack khali hai to (if the stack is empty)
                return False # to hum False return karte hain (we return False)
            current = self.stack.pop() # hum stack se current element ko pop karte hain (we pop the current element from the stack)
            if current.isInteger(): # agar current element integer hai to (if the current element is an integer)
                self.stack.append(current.getInteger()) # to hum usko stack me add karte hain (we add it to the stack)
            else: # agar current element list hai to (if the current element is a list)
                self._flatten(current.getList()) # to hum usko flatten karte hain (we flatten it)
        return True # agar index stack ke length se kam hai to (if the index is less than the length of the stack) then we return True 

# Your NestedIterator object will be instantiated and called as such:
# i, v = NestedIterator(nestedList), []
# while i.hasNext(): v.append(i.next())