"""
Time Complexity : O(n) where n is no. of integers in the list
Space Complexity : O(n)
Did this code successfully run on Leetcode : Yes
Any problem you faced while coding this : No
"""
class NestedIterator:
    def __init__(self, nestedList: [NestedInteger]):
        def flatten_list(nested_list):
            for nested_integer in nested_list:
                if nested_integer.isInteger():
                    self._integers.append(nested_integer.getInteger())
                else:
                    flatten_list(nested_integer.getList()) 
        self._integers = []
        self._position = -1 # Pointer to previous returned.
        flatten_list(nestedList)
    
    def next(self) -> int:
        self._position += 1
        return self._integers[self._position]
        
    def hasNext(self) -> bool:
        return self._position + 1 < len(self._integers)
         