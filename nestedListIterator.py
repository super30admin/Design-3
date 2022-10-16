#Time Complexity: O(1)
#Space Complexity: O(1)
#Did it run on leetcode: Yes

class NestedIterator:
    def __init__(self, nestedList: [NestedInteger]):
        self._generator = self._int_generator(nestedList)
        self._peeked = None

    def _int_generator(self, nested_list):
        for nested in nested_list:
            if nested.isInteger():
                yield nested.getInteger()
            else:
                yield from self._int_generator(nested.getList())
    
    def next(self) -> int:
        if not self.hasNext(): return None
        next_integer, self._peeked = self._peeked, None
        return next_integer
        
    def hasNext(self) -> bool:
        if self._peeked is not None: return True
        try: 
            self._peeked = next(self._generator)
            return True
        except:
            return False