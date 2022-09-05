# 341. Flatten Nested List Iterator

'''
Leetcode all test cases passed: Yes
class NestedIterator:
    def next(self):
      Time Complexity: O(1) 
    def hasNext(self) -> bool:
      Time Complexity: O(1) 
'''
class NestedIterator:
    def __init__(self, nestedList: [NestedInteger]):
        self.global_stack = []
        self.global_stack.append(iter(nestedList))
        self.next_element = nestedList
    def next(self) -> int:
        return self.next_element
    
    def hasNext(self) -> bool:
            count = 0
            while self.global_stack :
                try:
                    temp = next(self.global_stack[-1])
                    if temp.isInteger():
                        self.next_element = temp.getInteger()
                        return True
                    else:
                        self.global_stack.append(iter(temp.getList()))
                except Exception as e: 
                    self.global_stack.pop()
            return False
