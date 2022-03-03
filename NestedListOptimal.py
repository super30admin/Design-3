# Time Complexity o(N)
# Space Complexity o(N)



# """
#
# 
#  This is the interface that allows for creating nested lists.
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
#    Initialize a stack to store the native iterators     
        self.stack = []
        
        if nestedList != None:
            
            self.stack.append(iter(nestedList))
#       Initialize the cursor to store the value of curr element sice when next is called in a native iterator we loose reference to the current value
        self.cursor = None

    
    def next(self) -> int:
        # print(self.cursor)
# Return the value of cursor when next is called
        val = self.cursor
        
        return val
        
        
    
    def hasNext(self) -> bool:

        
        while(len(self.stack)>0):
#         Take the top most element and initialize it with a native iterator    
            itera = self.stack[-1]
#      next function returns current element moves the  pointer to the next element.
            currentne = next(itera,None)
#     Pop the stack if the iterator is exhasted
            if currentne == None:
                self.stack.pop()
                continue
             
            nestedInt = currentne
#      If the current element is an integer initialize the cursor to the current element and return true       
            if nestedInt.isInteger():
                self.cursor = nestedInt.getInteger()
                return True
            
            else:
#                 If it is a  list initilize an iterator and add it to the top of the stack.
                self.stack.append(iter(nestedInt.getList()))
        return False
            
         

# Your NestedIterator object will be instantiated and called as such:
# i, v = NestedIterator(nestedList), []
# while i.hasNext(): v.append(i.next())