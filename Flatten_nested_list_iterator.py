# Time Complexity : O(n)     # n is total number of NestedIntegers in the list
# Space Complexity : O(d)    #d is the depth of largest NestedInteger
# Did this code successfully run on Leetcode : Yes
# Any problem you faced while coding this : No


class NestedIterator:
    def __init__(self, nestedList: [NestedInteger]):
        self.stack = []
        self.stack.append(iter(nestedList))
        self.result = 0
    
    def next(self) -> int:
        return self.result
    
    def hasNext(self) -> bool:
        while self.stack:
            
            current = next(self.stack[-1], None)
            
            if current is None:
                self.stack.pop()
                
            elif current.isInteger():
                self.result = current.getInteger()
                return True
            
            else:
                self.stack.append(iter(current.getList()))
                
        return False
