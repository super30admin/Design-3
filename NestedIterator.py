#341. Flatten Nested List Iterator
# Time Complexity : O(n) where n is the number of nestings inside the nestedList
# Space Complexity : O(n) 
# Did this code successfully run on Leetcode : Yes
# Any problem you faced while coding this : No
#class NestedInteger:
#    def isInteger(self) -> bool:
#        """
#        @return True if this NestedInteger holds a single integer, rather than a nested list.
#        """
#
#    def getInteger(self) -> int:
#        """
#        @return the single integer that this NestedInteger holds, if it holds a single integer
#        Return None if this NestedInteger holds a nested list
#        """
#
#    def getList(self) -> [NestedInteger]:
#        """
#        @return the nested list that this NestedInteger holds, if it holds a nested list
#        Return None if this NestedInteger holds a single integer
#        """
#class NestedIterator:
#    queue = None
#    def __init__(self, nestedList: [NestedInteger]):
#        self.queue = []
#        self.flatten(nestedList)
#        
#    
#    def next(self) -> int:
#        return self.queue.pop(0)
#        
#    
#    def hasNext(self) -> bool:
#        return len(self.queue) != 0
#    def flatten(self,nestedList):
#        for el in nestedList:
#            if el.isInteger():
#                self.queue.append(el.getInteger())
#            else:
#                self.flatten(el.getList())
#

class NestedIterator:
    stack = None 
    cursor = None 
    def __init__(self, nestedList: [NestedInteger]):

        
        self.stack = [] 

       
        if nestedList:
            self.stack.append(iter(nestedList))


    def next(self) -> int:
        
        value = self.cursor 
        self.cursor = None 
        return value 

    def hasNext(self) -> bool:
        while self.stack and self.cursor == None:

            iterator = self.stack[-1]
            temp = next(iterator,None)

            if temp == None: # that means there is not next 
                self.stack.pop()
                continue

            nestedInt = temp 

            if temp.isInteger():
                self.cursor = nestedInt.getInteger() 
                return True 
            else:
                self.stack.append(iter(nestedInt.getList()))

        return False