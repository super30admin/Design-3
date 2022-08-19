#Time Complexity :  O(N) - n is the all the elements combined from the list
#  Space Complexity : O(N) - for stack
#  Did this code successfully run on Leetcode :yes
# Any problem you faced while coding this :no


class NestedIterator(object):

    def __init__(self, nestedList):                     
        self.stack = nestedList[::-1]

    def next(self):

        return self.stack.pop().getInteger()           

    def hasNext(self):

        while self.stack:
            top = self.stack[-1]
            if top.isInteger():                            
                return True
            self.stack = self.stack[:-1] + top.getList()[::-1]              
        return False 