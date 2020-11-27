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
""" 
Controlled recursion
Time complexity O(N)
Space complexity 0(N) O(Number of nested list ie the depth)


"""
class NestedIterator:
    def __init__(self, nestedList: [NestedInteger]):
        self.st=[]
        if nestedList:
            self.st.append(iter(nestedList))
            self.ptr=None
            
        
    
    def next(self) -> int:
        val= self.ptr
        self.ptr = None
        return val
        
    
    def hasNext(self) -> bool:
        while(len(self.st) >0 and self.ptr==None):
            iterator = self.st[-1] 
            currentNI = next(iterator,None)
            if (currentNI == None):
                self.st.pop()
                #print(self.st.pop())
                continue
            nestedInteger = currentNI
            if (nestedInteger.isInteger()):
                self.ptr=nestedInteger.getInteger()
                return True
            else:
                self.st.append(iter(nestedInteger.getList()))
        return False
                
         

# Your NestedIterator object will be instantiated and called as such:
# i, v = NestedIterator(nestedList), []
# while i.hasNext(): v.append(i.next())