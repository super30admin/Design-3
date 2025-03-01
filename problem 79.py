## Problem 1: Flatten Nested List Iterator (https://leetcode.com/problems/flatten-nested-list-iterator/)


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
        self.st = [] # Stack to hold iterators for nested lists
        self.nextEl = None  # Holds the next element to return 
        self.st.append(iter(nestedList))
        
    
    def next(self) -> int:
        return self.nextEl.getInteger()        
    
    def hasNext(self) -> bool:
        while self.st:
            try:
                # Get the next element from the top iterator in the stack
                current = next(self.st[-1])
                
                if current.isInteger():
                    # If it's an integer, set nextEl and return True
                    self.nextEl = current
                    return True
                else:
                    # If it's a nested list, push its iterator onto the stack
                    self.st.append(iter(current.getList()))
            except StopIteration:
                # If the current iterator is exhausted, pop it off the stack
                self.st.pop()
        
        # If no valid integer is found, return False
        return False
         
# Time Complexity:

# hasNext(): O(n) (where n is the total number of integers across all nested lists)
# next(): O(1)
# Space Complexity: O(d) (where d is the maximum depth of the nested list)







