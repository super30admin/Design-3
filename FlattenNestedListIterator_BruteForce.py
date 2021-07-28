# TC: O(N) where N is the number of lists in the nested list. Iterating over them recursively will take O(N) time. 
# SC: O(D) where D is the maximum depth of the recursion, which will also be the height of the stack. 

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
        def flattenList(int_list): 
            for i in int_list: 
                if i.isInteger(): 
                    self.int_list.append(i)
                else: 
                    flattenList(i.getList())
            
        self.int_list = []
        self.curr = -1
        flattenList(nestedList)
    
    def next(self) -> int:
        self.curr += 1
        return self.int_list[self.curr]
          
    def hasNext(self) -> bool:
        if self.curr + 1 < len(self.int_list):
            return True
        return False
              

# Your NestedIterator object will be instantiated and called as such:
# i, v = NestedIterator(nestedList), []
# while i.hasNext(): v.append(i.next())
