# TC: O(1) as we just create a generator/iterator in the constructor. The creation of generator does not require any extra time, unless the next() is called. 
# SC: O(D), as we are calling the convertGenerator function recursively, D will the height of the recursive stack.


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
        self.peeked = None
        self.generator = self.convertGenerator(nestedList)
        
    def convertGenerator(self, nestedList):
        for elem in nestedList: 
            if elem.isInteger():
                yield elem
            else: 
                yield from self.convertGenerator(elem.getList())       
    
    def next(self) -> int:
        if not self.hasNext(): 
            return None
        
        next_val = self.peeked
        self.peeked = None
        return next_val
        
    
    def hasNext(self) -> bool:
        if self.peeked is not None: 
            return True
        
        try: 
            self.peeked = next(self.generator)
            return True
        except: 
            return False
         
# Your NestedIterator object will be instantiated and called as such:
# i, v = NestedIterator(nestedList), []
# while i.hasNext(): v.append(i.next())
