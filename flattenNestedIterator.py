from collections import deque
# Time Complexity : O(n), where n is the length of the nestedList
# Space Complexity : O(n)
# Did this code successfully run on Leetcode : yes
# Any problem you faced while coding this : no

# Your code here along with comments explaining your approach

# This approach flattens all the lists into one single
# list and removes and returns when next is called
class NestedIteratorBadImplementation(object):

    def __init__(self, nestedList):
        self.q = deque()
        self.flatten(nestedList)
    # O(1)
    def next(self):
        return self.q.pop()
    # O(1)
    def hasNext(self):
        return len(self.q) > 0
    # O(n)
    def flatten(self, arr):
        for i in arr:
            if i.isInteger():
                self.q.appendleft(i.getInteger())
            else:
                self.flatten(i.getList())
