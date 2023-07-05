# Time Complexity : O(1)
#Space Complexity : O(h)
# #// Did this code successfully run on Leetcode :Yes
# // Any problem you faced while coding this :NO

class NestedIterator:
    def __init__(self, nestedList: List[NestedInteger]):
        def get(currList: List[NestedInteger]) -> Generator[int, None, None]:
            for nestedInteger in currList:
                if nestedInteger.isInteger():  # nestedInteger has a single integer value
                    yield nestedInteger.getInteger()
                else:                          # nestedInteger is a list of NestedIntegers
                    yield from get(nestedInteger.getList())
        self.generator = get(nestedList)               # Initialise the generator object with the given NestedInteger list
        self.nextInteger = next(self.generator, None)  # Obtain the next (first) NestedInteger pre-emptively
    
    def next(self) -> int:
        result = self.nextInteger                      # store the current NestedInteger integer value
        self.nextInteger = next(self.generator, None)  # Obtain the next NestedInteger pre-emptively
        return result
    
    def hasNext(self) -> bool:
        return self.nextInteger is not None