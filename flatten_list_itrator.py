# Time Complexity : O(n) where n is all the elements
# Space Complexity : O(n) 
# Did this code successfully run on Leetcode : Yes
# Three line explanation of solution in plain english
# I iterate through trie for each word and return the prefix where I find the first root

class NestedIterator:
    def __init__(self, nestedList: [NestedInteger]):
        self.stack = [iter(nestedList)]
        self.nextElement = None
    
    def next(self) -> int:
        return self.nextElement
    
    def hasNext(self) -> bool:
        while(len(self.stack)):
            try:
                stack_top = next(self.stack[-1])
                if stack_top.isInteger():
                    self.nextElement = stack_top.getInteger()
                    return True
                else:
                    self.stack.append(iter(stack_top.getList()))
            except StopIteration:
                self.stack.pop()
                continue
        return None