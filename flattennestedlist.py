# Time Complexity : O(1) amortized 
# Space Complexity : O(N) where N is the number of elements in the nested list in stack
# Did this code successfully run on Leetcode : Yes
# Any problem you faced while coding this : No

# Your code here along with comments explaining your approach:
# I am using a stack to store the nested list and an index list to store the current index of each nested list in the stack.
# In the next function I return the next element in the nested list that is stored in the nextel variable.
# In that I am using the getInteger function to return the integer value of the NestedInteger object.
#In hasNext function I am checking if the stack is not empty. And then checking if the current index is equal to the length of the current nested list.
# If it is, I pop the current nested list and the current index from the stack and index list respectively.
# If it is not, I get the current nested list and the current index.
# I increment the current index by 1 and update it in the index list.
# I get the current element in the current nested list using the current index.
# If the current element is an integer, I store it in the nextel variable and return True.
# If it is not, I push the nested list of the current element to the stack and push 0 to the index list.
# I repeat this process until I find an integer or the stack is empty.
# If the stack is empty, I return False.
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
        self.stack = [nestedList]
        self.idx = [0]
        self.nextel = None
        
    
    def next(self) -> int:
        return self.nextel.getInteger()
        
    
    def hasNext(self) -> bool:
        while self.stack:
            if len(self.stack[-1]) == self.idx[-1]:
                self.stack.pop()
                self.idx.pop()
            else:
                curr = self.stack[-1]
                currIdx = self.idx.pop()
                self.idx.append(currIdx + 1)
                self.nextel = curr[currIdx]
                if self.nextel.isInteger():
                    return True
                else:
                    self.stack.append(self.nextel.getList())
                    self.idx.append(0)
        return False



# Your NestedIterator object will be instantiated and called as such:
# i, v = NestedIterator(nestedList), []
# while i.hasNext(): v.append(i.next())