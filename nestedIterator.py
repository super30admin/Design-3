'''
Time Complexity: 0(1)
Space Complexity: 0(k) where k is the count of list in a nested iterator obj
Run on leet-code: Yes
'''

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
        
        # create an empty stackList
        self.stackList = []
        
        # convert nestedList to an iterator
        self.nestedList_iter = iter(nestedList)
        
        # push "nestedList_iter" to the stack
        self.stackList.append(self.nestedList_iter)
        
        # update to the integer value
        self.nextEle = None
        
    def next(self) -> int:
        # get the integer from nextEle
        if self.nextEle.isInteger():
            return self.nextEle.getInteger()
    
    def hasNext(self) -> bool:
        '''You call next() repeatedly untill hasNext() return FALSE'''
        
        # iterate the stackList
        while len(self.stackList) != 0:
            
            # get the nextElement
            self.nextEle = next(self.stackList[-1],None)
            
            # chk self.nextEle == None
            if self.nextEle == None:
                # my iterator is out of bounds
                # pop from the stack
                self.stackList.pop()
                continue
            
            # chk self.nextEle == Integer
            elif self.nextEle.isInteger():
                # True-case
                return True
            
            # chk self.nextEle != Integer
            else:
                # I have a list
                # convert list to the iterator obj and push it to the stack
                iterList = iter(self.nextEle.getList())
                self.stackList.append(iterList)
                continue
        
        '''my stack is empty'''
        
        return False

# Your NestedIterator object will be instantiated and called as such:
# i, v = NestedIterator(nestedList), []
# while i.hasNext(): v.append(i.next())