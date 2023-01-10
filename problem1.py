#time complexity: O(n)
#space complexity: O(n+d), d = max. nesting depth
#ran on leetcode: Yes
#Keep on expanding until a integr is reached. While expanding, place the iterators in a stack. 
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
        self.stk=[]
        self.stk.append(iter(nestedList))
        
    
    def next(self) -> int:
        if(not self.ele):
            return None
        return self.ele.getInteger()

        
    
    def hasNext(self) -> bool:
        while(self.stk):
            self.ele=next(self.stk[-1],None)
            if(self.ele==None):
                #print("HERE!!")
                del(self.stk[-1])
                continue
            if( self.ele.isInteger() ):
                return True
            else:
                self.stk.append(iter(self.ele.getList()))

        return False
            

         

# Your NestedIterator object will be instantiated and called as such:
# i, v = NestedIterator(nestedList), []
# while i.hasNext(): v.append(i.next())
