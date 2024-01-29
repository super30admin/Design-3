
# Solution(DONOT IMPLEMENT THE RECURSION WHERE WE FLATTEN THE ARRAY AT FIRST ITSELF. ITERATOR SHOULD ALWAYS PROCESS DATA 
# DYNAMICALLY ONE BY ONE, NOT PROCESS EVERYTHING AT ONCE AND GIVE RESULT)

# // Time Complexity : Iterator: Amortized O(1) but if we dig deep below is the explanation
#                      hasNext->O(D) D is max depth of nestedList. That means, let's assume first element of nestedList
#                      is another nestedList. Then the second one has first element which again as a nestedList. So this depth
#                      till we find first element will be the time complexity for hasNext
#                      Next->O(D) if hasNext was not triggered before this. Else O(1)

#                      BEST SOLUTION FOR PYTHON IS GENERATOR FUNCTION 
#                      Generator Function: O(1), since we are using generator function and yield
# 
#                      recursion: O(N+L) since we go integer by integer and add it into our local list so that it is flattened
#                      N is all the integers and L is the number of lists within nested list

# // Space Complexity : Iterator Function: O(D) -> Max depth, since we keep adding iterators of lists to stack till we find
#                       first element.
#                       
#                       Generator Function: O(M) M is the number of nested elements inside main nestedList, since recursion will 
#                       be equal to that
# 
#                       recursion:O(M+N), M is the number of nested elements inside main nestedList, since recursion will be equal to
#                       that and that would be the implicit stack size. N is the size of list used to save all the integers
# // Did this code successfully run on Leetcode : Yes
# // Any problem you faced while coding this : On how to access the nestedList. But later realised, it is just a list of multiple
# NestedInteger objects and the interface for NestedInteger is provided in question. So using for loop we can get access to each
# object and then once we have access to object, we need to use functions within the interface to get required details

# // Your code here along with comments explaining your approach
# Approach1(plain recursion to create list in constructor): Using recursion to flatten the nested list. Once flattened, all the 
#                                                           access will be on the flattened list
# BEST SOLUTION FOR PYTHON IS APPROACH2
# Approach2 is (using generator function, look in notes on how to use generator function)
# Approach2: This is very similar to approach1, but the main difference is we use generator function. Generator functions use
# "yield" which acts like return but it does not come out. System will remember where it stopped and the next time the generator
# is called using "next" function, it continues from where it left off.
# 
# Approach3: S30, using iterators and stack. Here we create iterators for nestedLists. We will have one iterator for main
# nestedList and append that to stack. Now, in hasNext function we run while loop till stack is empty OR we find an integer
# During this While loop is running, we first take the main iterator and take the next of that iterator. We check if next
# is interger or list. If integer then we just return that, next time hasNext is called this iterator will continue from the
# next element since that is how iterators work. If we find nestedList, then create a iterator for that and add it in stack
# Now stack will give the latest iterator, which will be for the nestedList within the main nestedList. Now we start breaking that
# so that we get an integer and return. Once that iterator is completed we pop that from stack, now we again will have only one
# iterator which is the main one and also that iterator had already given one integer and one list, so it will already be pointing
# to third element when we use next.

# Below code cannot be run directly since I have not created the NestedInteger part which leetcode has done in background

# Using iterators and saving iterators in stack
class NestedIterator:
    def __init__(self, nestedList: [NestedInteger]):
        self.itrStack = [iter(nestedList)]
        self.nextEl = None

    def next(self) -> int:
        if not self.hasNext():
            return 0
        
        result = self.nextEl
        self.nextEl = None
        return result
    
    def hasNext(self) -> bool:
        if self.nextEl != None:
            return True

        while self.itrStack:
            element = next(self.itrStack[-1],None)
            if element == None:
                self.itrStack.pop()
            else:
                if element.isInteger():
                    self.nextEl = element.getInteger()
                    return True
                else:
                    self.itrStack.append(iter(element.getList()))
        
        return False

# Using generators, Best solution for python.
# class NestedIterator:
#     def __init__(self, nestedList: [NestedInteger]):
#         self.nextEl = None
#         self.generator = self.generatorFunc(nestedList)
        
#     def generatorFunc(self,nestedList):
#         for element in nestedList:
#             if element.isInteger():
#                 yield element.getInteger()
#             else:
#                 yield from self.generatorFunc(element.getList())

#     def next(self) -> int:
#         if self.nextEl != None:
#             result = self.nextEl
#             self.nextEl = None
#             return result
#         elif not self.hasNext():
#             return None
        
    
#     def hasNext(self) -> bool:
#         if self.nextEl != None:
#             return True
#         try:
#             self.nextEl = next(self.generator)
#             return True
#         except:
#             return False

# Recursion to just prepare list in the beginning itself, but this is not a good solution since iterator should get value one
# by one when requested for next
# class NestedIterator:
#     def __init__(self, nestedList: [NestedInteger]):
#         def helper(nestedList):
#             for integerNest in nestedList:
#                 if integerNest.isInteger():
#                     self.integerList.append(integerNest.getInteger())
#                 else:
#                     helper(integerNest.getList())
        
#         self.integerList = []
#         self.idx = 0
#         helper(nestedList)

    
#     def next(self) -> int:
#         result = self.integerList[self.idx]
#         self.idx += 1
#         return result
        
    
#     def hasNext(self) -> bool:
#         if self.idx >= len(self.integerList):
#             return False
#         else:
#             return True
         
# Your NestedIterator object will be instantiated and called as such:
# i, v = NestedIterator(nestedList), []
# while i.hasNext(): v.append(i.next())