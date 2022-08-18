"""
Runtime Complexity:O(N+L) - where 'L' is the number of lists and 'N' is the number of elements in the given list.
Space Complexity: O(k) - recursive stack space
Yes, the code worked on leetcode
Issues while coding - No
"""

class NestedIterator:
    def __init__(self, nestedList: [NestedInteger]):
        def flatten(list_):
			
            for i in range(len(list_)):
                if list_[i].isInteger():
                    yield list_[i].getInteger()
                else:
                    for x in flatten(list_[i].getList()):
                        yield x
            return None
        self.nestedList = flatten(nestedList)
        
        self.next_ = None
    
    def next(self) -> int:
        assert self.next_ is not None        
        next_ = self.next_
        self.next_ = None
        return next_
    
    def hasNext(self) -> bool:
        if self.next_ is not None:
            return True
        try:
            self.next_ = next(self.nestedList)
        except StopIteration:
            return False
        else:
            return True