# Time Complexity : Add - O(1) Ignoring what is happening in the constructor
# Space Complexity : O(1)
# Did this code successfully run on Leetcode : Yes
# Any problem you faced while coding this : No
'''
1. Flatten the list and store it in an attribute inside the constructur
2. use a dummy positional attribute to return the element when net is called
'''

class NestedIterator:
    def __init__(self, nestedList: [NestedInteger]):
        
        
        self.result = []
        self._helper(nestedList)
        self.pos = 0

    def next(self) -> int:
        
        curr = self.result[self.pos]
        self.pos += 1
        return curr
                       
    def _helper(self, list_):
        
        if list_ == []:
            return
        
        for nestint in list_:
            if nestint.isInteger():
                self.result.append(nestint.getInteger())
            else:
                self._helper(nestint.getList())
            
    
    def hasNext(self) -> bool:
        if self.pos < len(self.result):
            return True
        return False