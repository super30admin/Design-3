#// Time Complexity : O(1) 
# // Space Complexity : O(n ) where n is depth of the nestedllist
# // Did this code successfully run on Leetcode : yes
# // Any problem you faced while coding this : no because i saw the class video and then did the problem.

class NestedIterator:
    def __init__(self, nestedList: [NestedInteger]):
        self.stack = [iter(nestedList)]
        self.nextval = None
    
    def next(self) -> int:
        return self.nextval.getInteger()

    def hasNext(self) -> bool:
        while self.stack:
            curr = next(self.stack[-1],None)
            if curr == None:
                self.stack.pop()
            elif curr.isInteger():
                self.nextval = curr
                return True
            else:
                self.stack.append(iter(curr.getList()))
        return False


         

# Your NestedIterator object will be instantiated and called as such:
# i, v = NestedIterator(nestedList), []
# while i.hasNext(): v.append(i.next())

# Approach:
# The problem can be solved by using a stack data structure. The idea is to push the nested list
# into the stack and then pop it out one by one. When we pop out a nested list,
# we check if it is an integer or a list. If it is an integer, we return it
# If it is a list, we push it back into the stack. We repeat this process until we
# pop out an integer.

# But there is a native iterator on each list in the stack whcih is doing the magic