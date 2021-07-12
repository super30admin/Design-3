# """
# This is the interface that allows for creating nested lists.
# You should not implement it, or speculate about its implementation
# """
# class NestedInteger:
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
# Time Complexity: O(n + l) - n - no of integers, l - no of lists
# Space Complexity: O(n + l)
class NestedIterator:
    #     Initializing the integers and the position
    integers = []
    positions = -1

    #
    def getlist(self, nestedList):
        for element in nestedList:
            # If current element is integer add it to the interger list or
            # else call the function recursively on the list
            if element.isInteger():
                self.integers.append(element.getInteger())
            else:
                self.getlist(element.getList())

    # Calling sorting function where we will sort integers in integers list and call the
    # function recursively on rets of the list

    def __init__(self, nestedList):
        self.integers = []
        self.positions = -1

        self.getlist(nestedList)

    #     Get next element in the list with incremented position
    def next(self):
        self.positions += 1
        return self.integers[self.positions]

    #     if current position is greater than the integers then we wil return False else true
    def hasNext(self):
        if self.positions + 1 < len(self.integers):
            return True
        return False

# Your NestedIterator object will be instantiated and called as such:
# i, v = NestedIterator(nestedList), []
# while i.hasNext(): v.append(i.next())