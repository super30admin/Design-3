# """
# This is the interface that allows for creating nested lists.
# You should not implement it, or speculate about its implementation
# """
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
        self.nextElement = None
    
    def next(self) -> int:
        return self.nextElement.getInteger()
    
    def hasNext(self) -> bool:
        while len(self.stack) > 0:
            iterator = self.stack[-1]
            currentNextElement = next(iterator, None)

            if currentNextElement is None:
                self.stack.pop()
            else:
                self.nextElement = currentNextElement
                if self.nextElement.isInteger():
                    return True
                else:
                    self.stack.append(iter(self.nextElement.getList()))
        return False
         

# Your NestedIterator object will be instantiated and called as such:
# i, v = NestedIterator(nestedList), []
# while i.hasNext(): v.append(i.next())


# class NestedIterator:
#     def __init__(self, nestedList: [NestedInteger]):
#         self.li = []
#         self.dfs(nestedList)
#         self.i = 0

#     def dfs(self, nestedList):
#         for ele in nestedList:
#             if ele.isInteger():
#                 self.li.append(ele.getInteger())
#             else:
#                 self.dfs(ele.getList())
        
    
#     def next(self) -> int:
#         value = self.li[self.i]
#         self.i+=1
#         return value
    
#     def hasNext(self) -> bool:
#         return self.i!=len(self.li)

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
    def __init__(self, nestedList: [NestedInteger]):
        self.stack = []
        if nestedList:
            self.stack.append(iter(nestedList))
        self.nextElement = None
    
    def next(self) -> int:
        return self.nextElement.getInteger()
    
    def hasNext(self) -> bool:
        while self.stack:
            iterator = self.stack[-1]
            currentNextElement = next(iterator, None)

            if not currentNextElement:
                self.stack.pop()
            else:
                self.nextElement = currentNextElement
                if self.nextElement.isInteger():
                    return True
                else:
                    self.stack.append(iter(self.nextElement.getList()))
        return False
         

# Your NestedIterator object will be instantiated and called as such:
# i, v = NestedIterator(nestedList), []
# while i.hasNext(): v.append(i.next())

         