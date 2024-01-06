"""
This technique uses the inbuilt iterator and its next() method. In Python iterator does not have hasNext() method so required modifications are done.

Steps:
1. Iteration is controlled recursion, so use stack
2. Append iterator(nestedIntegerList) and use its next method to keep the track of the current List
3. Top of the stack should always have the next output element. 
4. Whenever element is integer assigned it to global variable and return True. Returning True from the hasNext() tells the next() method that there are still elements left
5. If the currEle is list then append it to the stack,
6. At the end return False, indicating there are no elements left  

Time Complexity: O(1) for next() and on an average O(1) for hasnext() but in worst case it is a depth of the nested list
Space Complexity: o(1)

Accepted on LeetCode: Yes
"""

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


class NestedIterator:
    def __init__(self, nestedList: [NestedInteger]):
        self.stack = []
        if nestedList:
            self.stack.append(iter(nestedList))
        self.cursor = None

    def next(self) -> int:
        value = self.cursor
        self.cursor = None
        return value

    def hasNext(self) -> bool:
        while self.stack and self.cursor == None:
            iterator = self.stack[-1]
            # None is returned instead of standard exception when next is out of bound
            currNestedEle = next(iterator, None)

            if currNestedEle == None:
                self.stack.pop()
                continue

            if currNestedEle.isInteger():
                self.cursor = currNestedEle.getInteger()
                return True
            else:
                self.stack.append(iter(currNestedEle.getList()))

        return False

# Your NestedIterator object will be instantiated and called as such:
# i, v = NestedIterator(nestedList), []
# while i.hasNext(): v.append(i.next())
