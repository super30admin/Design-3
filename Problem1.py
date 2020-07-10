# Time Complexity : O(n) where n is the number of nestings inside the nestedList
# Space Complexity : O(n) 
# Did this code successfully run on Leetcode : Yes
# Any problem you faced while coding this : No
# Your code here along with comments explaining your approach


# """
# This is the interface that allows for creating nested lists.
# You should not implement it, or speculate about its implementation
# """
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
# Method 1 
# class NestedIterator:
#     q = None 
#     def __init__(self, nestedList: [NestedInteger]):
#         self.q = []
#         self.flatten(nestedList)
        
#     def next(self) -> int:
#         return self.q.pop(0)
    
#     def hasNext(self) -> bool:
#         return len(self.q) > 0
         
#     def flatten(self,nestedList):
#         for item in nestedList:
#             if item.isInteger():
#                 self.q.append(item.getInteger())
#             else:
#                 self.flatten(item.getList())

class NestedIterator:
    def __init__(self, nestedList):
        self.stack = []
        self.stack.append(nestedList)


    def next(self):
        self.hasNext()
        return self.stack[-1].next().getInteger()


    def hasNext(self):
        while len(self.stack) != 0:
            if self.stack[-1].hasNext():
                self.stack.pop()
            else:
                x = self.stack[-1].next()
                if x.isInteger():
                    return self.stack[-1].previous()==x
                self.stack.append(x.getList())
        return False 
    
# # Your NestedIterator object will be instantiated and called as such:
# # i, v = NestedIterator(nestedList), []
# # while i.hasNext(): v.append(i.next())

# """
# This is the interface that allows for creating nested lists.
# You should not implement it, or speculate about its implementation
# """
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

class NestedIterator:
    stack = None 
    cursor = None 
    def __init__(self, nestedList: [NestedInteger]):
        
        # 1) Create a stack for storing all the iteators.  
        self.stack = [] 
        
        # 2) append all iterators
        if nestedList:
            self.stack.append(iter(nestedList))

    
    def next(self) -> int:
        # Save the cursor value because the value cannot be same for each next function call.
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
            
         

# Your NestedIterator object will be instantiated and called as such:
# i, v = NestedIterator(nestedList), []
# while i.hasNext(): v.append(i.next())