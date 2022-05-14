#flatten list iterator
# // Time Complexity :  O(N) - n is the all the elements combined from the list
# // Space Complexity : O(N) - for stack
# // Did this code successfully run on Leetcode :yes
# // Any problem you faced while coding this :no


class NestedIterator(object):

    def __init__(self, nestedList):                     #reverse the nested list
        self.stack = nestedList[::-1]
        
    def next(self):

        return self.stack.pop().getInteger()            #return the last integer from the stack
        
    def hasNext(self):
  
        while self.stack:
            top = self.stack[-1]
            if top.isInteger():                              #   if the last element is integer, then return true
                return True
            self.stack = self.stack[:-1] + top.getList()[::-1]              #else use the getlist on the last element and append to the rest of the stack
        return False