
# Time Complexity: 

# Constructor: In the constructor, we only create a generator object. Simply creating a generator object doesn't invoke any code in the generator function itself (only calls to next do).
# Because the time taken to create the generator doesn't vary with the size of the input, the time complexity is O(1).

# next() / hasNext() : If the top of the stack is an integer, 
# then this function does nothing; taking O(1)O(1) time.
# Otherwise, it needs to process the stack until an integer is on top. 
# The best way of analyzing the time complexity is to look at the total cost 
# across all calls to makeStackTopAnInteger() and then divide by the number of calls made. 
# Once the iterator is exhausted,
#  makeStackTopAnInteger() must have seen every integer at least once, 
# costing O(N) time. Additionally, it has seen every list (except the first)
#  on the stack at least once also, so this costs O(L)O(L) time. 
# Adding these together, we get O(N+L) time.
# The amortized time of a single makeStackTopAnInteger is the total cost, O(N+L), 
# divided by the number of times it's called. In order to get all integers, 
# we need to have called it N times. 
# This gives us an amortized time complexity of O((N+L)/N) = O(N/L)

# Space Complexity:

# We recursively call _int_generator within itself for nested lists. 
# Therefore, the runtime stack uses memory proportional to the current depth of the list.
# If the largest depth is D, the space complexity is O(D).

# Did it run successfully on Leetcode: Yes
# Any issues faced while coding: Yes, took some time to understand generators

# Explain your approach: here I am using generators to yield multiple values(integers here)
# For this approach, we also need to add a peeked field, 
# much like in the Peeking Iterator problem. 
# This is because the only way to know if there is a next value is
#  to take it out of the generator, and generators can only go forwards, not backward.


class NestedIterator:
    def __init__(self, nestedList):
        self._generator = self._int_generator(nestedList)
        # All values are placed here before being returned, similar to nextEL?
        self._peeked = None
    
    def _int_generator(self, nested_list):
        # this part is recursive DFS
        for nesting in nested_list:
            if nesting.isInteger():
                yield nesting.getInteger()
            else:
                # use yield from here, _int_generator is a generator method, so
                # the recursive calls to it create a nre generator, 
                # to get the value from the generator, use a for loop,
                # or use yield from in Python
                yield from self._int_generator(nesting.getList())
            # will automatically raise a StopIteration
    
    def next(self):
        # Check if Integers left, if integers left, then this will also put one in 
        # self._peeked
        if not self.hasNext():
            return None
        next_integer, self._peeked = self._peeked, None
        return next_integer
    
    def hasNext(self):
        if self._peeked is not None:
            return True
        try:
            # Get another integer out of the generator.
            self._peeked = next(self._generator)
            return True
        except:
            return False
