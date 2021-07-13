# // Time Complexity : O(L/N)
# // Space Complexity : O(n)
# // Did this code successfully run on Leetcode : Yes
# // Any problem you faced while coding this : No
#
#
# // Your code here along with comments explaining your approach

class NestedIterator:
    def __init__(self, nestedList):
        self.stack = []
        # Append main Iterator
        self.stack.append(iter(nestedList))

    def next(self) -> int:
        return self.nextElement.getInteger()

    def hasNext(self) -> bool:
        while self.stack:
            try:
                self.nextElement = next(self.st[-1])
            except:
                self.stack.pop()
                continue

            if self.nextElement.isInteger():
                return True
            else:
                self.stack.append(iter(self.nextElement.getList()))
        return False
