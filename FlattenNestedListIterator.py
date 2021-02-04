# Time Complexity :
# Space Complexity :
# Did this code successfully run on Leetcode : Yes
# Any problem you faced while coding this :

# Your code here along with comments explaining your approach

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
        self.nestedList = nestedList
        self.position = 0
        self.lists = []

    def next(self) -> int:
        integer = self.nestedList[self.position].getInteger()
        self.position += 1
        return integer

    def hasNext(self) -> bool:
        while not self.isComplete():
            currentNode = self.nestedList[self.position]
            if currentNode.isInteger():
                return True
            else:
                self.position += 1
                self.lists.append((self.nestedList, self.position))
                self.position = 0
                self.nestedList = currentNode.getList()
        if self.isComplete() and not self.listEmpty():
            self.nestedList, self.position = self.lists.pop()
            return self.hasNext()

        return False

    def isComplete(self):
        return self.position >= len(self.nestedList)

    def listEmpty(self):
        return len(self.lists) == 0


# Your NestedIterator object will be instantiated and called as such:
# i, v = NestedIterator(nestedList), []
# while i.hasNext(): v.append(i.next())