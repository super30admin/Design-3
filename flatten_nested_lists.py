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

# KeyIdea : use native iterator instead of created iterator such as for loop or recursive function (https://www.w3schools.com/python/ref_func_iter.asp)
# Building an iterator means controlled recursion and for that we need to use stack either in form of queue or arraylist
# Given : hasNext is called before next
# Can use deque or normal array. Both would work in the same code

class NestedIterator:
    def __init__(self, nestedList: [NestedInteger]):
        
        # to maintain current element since as we call next, pointer next will move to next element and we wont be able to get current element
        self.next_element=NestedInteger
        self.stack=collections.deque()
        self.stack.append(iter(nestedList))
        
    def next(self) -> int:
        """
        TC:O(1)
        """
        return self.next_element
    
    def hasNext(self) -> bool:
        """
        TC:O(1)
        """
        while self.stack:
            next_iter=next(self.stack[-1], None)
            # case 1- if next does not point to any element 
            if not next_iter:
                self.stack.pop()
            
            # isInteger and getList given interface function
            # case 2
            elif next_iter.isInteger():
                self.next_element=next_iter.getInteger()
                return True
            # case 3 - if not integer, get list and put on top of stack with iterator
            else:
                self.stack.append(iter(next_iter.getList()))
        return False
  
