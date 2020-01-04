# """
# This is the interface that allows for creating nested lists.
# You should not implement it, or speculate about its implementation
# """

# class NestedInteger:
#    def isInteger(self) -> bool:
#        """
#        @return True if this NestedInteger holds a single integer, rather than a nested list.
#        """

#    def getInteger(self) -> int:
#        """
#        @return the single integer that this NestedInteger holds, if it holds a single integer
#        Return None if this NestedInteger holds a nested list
#        """

#    def getList(self) -> [NestedInteger]:
#        """
#        @return the nested list that this NestedInteger holds, if it holds a nested list
#        Return None if this NestedInteger holds a single integer
#        """


# APPROACH: Using a Queue 

# Time Complexity : O(N)
# Space Complexity : O(N) because of Recursion stack
# Did this code successfully run on Leetcode : YES


class NestedIterator:
    def __init__(self, nestedList):
        self.l = self.fillQueue(nestedList)

    def fillQueue(self, nestedList):
        temp = []
        for nest in nestedList:
            if nest.isInteger():
                temp.append(nest.getInteger())       
            else:
                temp.extend(self.fillQueue(nest.getList()))
        return temp
    
    def next(self):
        return self.l.pop(0)

    def hasNext(self):
        return len(self.l) != 0



         

