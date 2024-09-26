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

#Time Complexity:O(n), n is the number of integer
#Space Complexity:O(n)

#Ran successfully on Leetcode:Yes
#Main goal of this program is that, whatever list iterator is present at the top pf the stack, the next pointer in that list iterator should be pointing to the integer.
class NestedIterator:
    def __init__(self, nestedList):
        """
        Initialize your data structure here.
        :type nestedList: List[NestedInteger]
        """
        self.stack = []
        for i in range(len(nestedList)-1, -1, -1):
            self.stack.append(nestedList[i])
        self.nextValue = None

    def next(self):
        #This function moves the pointer to the next element in the nested list of integers
        """
        :rtype: int
        """
        ans = None
        if self.hasNext():
            ans = self.nextValue
            self.nextValue = None #reset self.nextValue
        return ans

    def hasNext(self):
        #this function checks if the next integer exists. It ensures that it always the next pointer in this list iterator  points to the next integer 
        """
        :rtype: bool
        """
        #We check if there exists a next list iterator in the stack . If yes we return True
        if self.nextValue is not None:
            return True
        #Here we take the top element in the stack i.e a list iterator and chec if it is a integer or not.
        while len(self.stack) > 0:
            ni = self.stack.pop()
            #If ni is integer, we set the next value as ni and next pointer is set to that integer.
            if ni.isInteger():
                self.nextValue = ni.getInteger()
                return True
            # If ni is not a integer, then definetly it has to be a nested list iterator, so we get the list and append it on the top of the stack, so that the next pointer in that list iterator points to the integer in it.
            else:
                niList = ni.getList()
                for i in range(len(niList) - 1, -1, -1):
                    self.stack.append(niList[i])
        return False

                
# Your NestedIterator object will be instantiated and called as such:
# i, v = NestedIterator(nestedList), []
# while i.hasNext(): v.append(i.next())
