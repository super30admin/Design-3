'''
====== Submission Details =======
Student Name: Pavan Kumar K. N.
S30 SlackID : RN32MAY2021
=================================
'''

# 341. Flatten Nested List Iterator

# https://leetcode.com/problems/flatten-nested-list-iterator/

#-----------------
# Time Complexity: 
#-----------------
# O(1) - Constructor
# O(L/N) - next() and hasNext() where L is the number of lists 
#          within nested list and D is the maximum nesting depth

#------------------
# Space Complexity: 
#------------------
# O(D): Stack space = Depth of recursion = Depth of nesting

#-----------------------
# Leet Code Performance: 
#-----------------------
# Ran Successfully?: Yes


# """
# This is the interface that allows for creating nested lists.
# You should not implement it, or speculate about its implementation
# """
#class NestedInteger:
#    def isInteger(self) -> bool:
#        """
#        @return True if this NestedInteger holds a single integer, 
#        rather than a nested list.
#        """
#
#    def getInteger(self) -> int:
#        """
#        @return the single integer that this NestedInteger holds, 
#        if it holds a single integer
#        Return None if this NestedInteger holds a nested list
#        """
#
#    def getList(self) -> [NestedInteger]:
#        """
#        @return the nested list that this NestedInteger holds, if it holds a nested list
#        Return None if this NestedInteger holds a single integer
#        """

class NestedIterator:

    def __init__(self, nestedList):
        # Get a generator object from the generator function, passing in
        # nestedList as the parameter.
        self.nested_gen_obj = self.nested_gen_func(nestedList)
        # Object to be returned upon peeking.
        self.peek_obj = None

    # This is the generator function used to create generator objects.
    # Generator function is a normal function but with at least 1 "yield" statement
    def nested_gen_func(self, nested_list):
        for nested in nested_list:
            if nested.isInteger():
                yield nested.getInteger()
            else:
                # Always use "yield from" on recursive generator calls.
                # It is equivalent to:
                for integer in self.nested_gen_func(nested.getList()):
                    yield integer
                # yield from self.nested_gen_func(nested.getList())
        
        # Will automatically raise a StopIteration -> Property of generator func
    
    def next(self):
        # Check there are any objects left to iterate
        # and if so, then hasNext() will add that in self.peek_obj.
        if not self.hasNext(): 
            return None
        
        # Return the value of self.peek_obj.
        temp = self.peek_obj
        
        # Clear value of peeked_obj
        self.peek_obj = None
        
        return temp
        
    def hasNext(self):
        # There's already an object to be peeked
        if self.peek_obj is not None: 
            return True
        try: 
        # Until generator throws StopIteration exception
            # Resume exectution of nested_gen_obj from previous yield statement.
            self.peek_obj = next(self.nested_gen_obj) 
            return True
        except: 
            # Generator raised StopIteration so no next object in the iteration.
            return False         

# Your NestedIterator object will be instantiated and called as such:
# i, v = NestedIterator(nestedList), []
# while i.hasNext(): v.append(i.next())