# Created by Aashish Adhikari at 12:09 PM 2/4/2021
class MyStack(object):


    def __init__(self):
        """
        Initialize your data structure here.
        """
        from collections import deque
        self.q = deque()
        self.is_empty = True

    def push(self, x):
        """
        Push element x onto stack.
        :type x: int
        :rtype: None
        """
        if len(self.q) == 0:
            self.is_empty = False

        self.q.append(x)

    def pop(self):
        """
        Removes the element on top of the stack and returns that element.
        :rtype: int
        """

        if len(self.q) != 0:
            return self.q.pop()


    def top(self):
        """
        Get the top element.
        :rtype: int
        """
        if len(self.q) > 0:
            return self.q[len(self.q)-1]



'''
Time Complexity:
next(): O(1)
hasNext(): If k is the maximum number of nested pairs within which a single integer or a sequence of integers lie in the original input, O(k).

Space Complexity:
next(): O(1)
hasNext():If k is the maximum number of nested pairs within which a single integer or a sequence of integers lie in the original input, O(k).
'''


class NestedIterator(object):

    def __init__(self, nestedList):
        """
        Initialize your data structure here.
        :type nestedList: List[NestedInteger]
        """
        self.stack = MyStack()
        self.stack.push(iter(nestedList)) # put the native iterator of the original list at the bottom of the stack
        self.current_NextedInteger = None


    def next(self):
        """
        :rtype: int
        """
        return self.current_NextedInteger.getInteger()


    def hasNext(self): # does two things : tells if there are any elements left and also makes sure that there always is an integer on top
        # of the stack
        """
        :rtype: bool
        """

        while len(self.stack.q) != 0:


            # Saved the current element pointed by next(iterator)
            stack_top_native_iterator = self.stack.top()
            self.current_NextedInteger = next(stack_top_native_iterator, None) # VVI syntax to not stop when None is encounted when next() is called on an iterator.


            if self.current_NextedInteger is None:
                _ = self.stack.pop()
            elif self.current_NextedInteger.isInteger():
                return True
            else:
                self.stack.push( iter(self.current_NextedInteger.getList()) )

        return False






