# // Time Complexity : O(1) to get next element 
# // Space Complexity : O(n) all the elements can be list. stack will contain them all
# // Did this code successfully run on Leetcode : Yes
# // Any problem you faced while coding this : No

# EXPLAINATION
# We use the iterator interface
# in python we can convert any list to iterator using iter(list_name)
# we convert every list to iterator and iterate on it
# We keep next_element updated and return its value on next() call
# in hasNext function we handle 3 cases
# when indexed element is Integer, None or List
# when its integer we update next_element value and return true
# when its list we append it to the stack with iter(list.getList())
# whem its none we are out of list hence we pop that list from the stack
# so... 
#  1. append to stack
#  2. upodate value and return True
#  3. pop from stack
class NestedIterator:
    def __init__(self, nestedList: [NestedInteger]):
        self.nextE = None
        # convert list to the iterator 
        self.stack = [iter(nestedList)]
        
    
    def next(self) -> int:
        # return the integer
        return self.nextE.getInteger()       
        
    
    def hasNext(self) -> bool:
        while self.stack:
            iterator = self.stack[-1]
            curr = next(iterator, None)
            if curr is None:
                self.stack.pop()
            elif curr.isInteger():
                self.nextE = curr
                return True
            else:
                self.stack.append(iter(curr.getList()))
         

# Your NestedIterator object will be instantiated and called as such:
# i, v = NestedIterator(nestedList), []
# while i.hasNext(): v.append(i.next())