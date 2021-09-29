# // Time Complexity :O(1),O(h) for next and hasnext
# // Space Complexity :O(h)
# // Did this code successfully run on Leetcode :yes
# // Any problem you faced while coding this :no


# // Your code here along with comments explaining your approach





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
        self.res = []
        if nestedList is not None:
            self.res.append(iter(nestedList))
        self.nextEl = None
        
        
    
    def next(self) -> int:
        value=self.nextEl
        self.nextEl=None
        return value
       
   
        
        # return True        
    
    def hasNext(self) -> bool:
        while(len(self.res)>0 ):
            iiterator=self.res[-1]
            currentNI = next(iiterator, None)
            if (currentNI == None):
                self.res.pop()
                continue
            nestedInteger = currentNI 
            if nestedInteger.isInteger():
                self.nextEl = nestedInteger.getInteger()

                return True
            else:
                self.res.append(iter(nestedInteger.getList()))
        return False
            
        
                
                
         

# Your NestedIterator object will be instantiated and called as such:
# i, v = NestedIterator(nestedList), []
# while i.hasNext(): v.append(i.next())