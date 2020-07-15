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

#---------------------------------------------------------x------------------------------------------------------------#

# Time Complexity : O(n), where n is the length of the nestedList
# Space Complexity : O(n)
# Did this code successfully run on Leetcode : yes
# Any problem you faced while coding this : no

# Your code here along with comments explaining your approach

# This approach does not flatten rather makes a wiser use of hasNext. Iterators for every
# list are stored in a stack, if the iterator does not have a next it is discarded,
# otherwise if an integer is encountered it updates the global variable to be used by next.
# else if a list is encounter, the iterator for that list is added to the stack.
class NestedIteratorGoodImplementation(object):

    def __init__(self, nestedList):
        self.nextInt = None
        self.store = [iter(nestedList)]

    def next(self):
        return self.nextInt.getInteger()

    def hasNext(self):
        while len(self.store) > 0:

            it = self.store[-1]
            curr = next(it, None)

            if not curr:
                self.store.pop()
            elif curr.isInteger():
                self.nextInt = curr
                return True
            else:
                self.store.append(iter(curr.getList()))

        return False
