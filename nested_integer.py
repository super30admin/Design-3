"""
Approach : A naive implementation would be to flatten the array on initialization and store it in the object.
The problem with that approach is that initialization of the iterator would take a very long time.

Instead what is done is that we keep store the array as is and flatten out the head element every time we call
the has next operator. This will distribute the time to flatten over each iteration versus at the construction time.

Time complexity : O(n)
Space Complexity : O(n)

Works on leet code.

I made a bunch of errors in the piece of code . did not handle empty arrays initially.

"""


class NestedIterator(object):
    stack = []
    
    def __init__(self, nestedList):  # [1,2,3]
        """
        Initialize your data structure here.
        :type nestedList: List[NestedInteger]
        """
        self.stack = nestedList  # stack = [1,2,3]
    
    def next(self):
        """
        :rtype: int
        """
        return self.stack.pop(0)
    
    def flatten(self):
        
        if self.stack:
            head = self.stack.pop(0)
            # head is an integer
            if not head.isInteger():
                head_list = head.getList()
                if head_list :
                    top = head_list.pop(0)
                    self.stack = [top] + head_list + self.stack
    
    def hasNext(self):
        """
        :rtype: bool
        """
        while not self.isFlattened():
            self.flatten()
        
        return True if self.stack else False
    
    def isFlattened(self):
        if not self.stack:
            return True
        if self.stack[0].isInteger():
            return True
        return False

