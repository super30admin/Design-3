# Time Complexity: O(1) 
# Space Complexity: O(n) where n is the number of elements in the stack
# Does this code run on Leetcode: Yes
# Does you face any problem while solving this problem: No


class NestedInteger:
   def isInteger(self) -> bool:
       """
       @return True if this NestedInteger holds a single integer, rather than a nested list.
       """

   def getInteger(self) -> int:
       """
       @return the single integer that this NestedInteger holds, if it holds a single integer
       Return None if this NestedInteger holds a nested list
       """

   def getList(self) -> [NestedInteger]:
       """
       @return the nested list that this NestedInteger holds, if it holds a nested list
       Return None if this NestedInteger holds a single integer
       """

class NestedIterator:
    def __init__(self, nestedList: [NestedInteger]):
        self.stack = []
        if nestedList:
            self.stack.append(iter(nestedList))
        self.next_value = None

    def next(self) -> int:  
        return self.next_value

    def hasNext(self) -> bool:
        while self.stack:
            curr_iter = self.stack[-1]
            currentNI = next(curr_iter,None)

            if currentNI is None:
                self.stack.pop()
                continue
            nestedInteger = currentNI
            if nestedInteger.isInteger():
                self.next_value = nestedInteger.getInteger()
                return True
            else:
                self.stack.append(iter(nestedInteger.getList()))
        return False
