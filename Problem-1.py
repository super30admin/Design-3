#Time Complexity :o(l) l is elemnt in array for hasnext oprtation
#Space Complexity :o(1) as stack is primary DS 
#Did this code successfully run on Leetcode :yes
#Any problem you faced while coding this :no
class NestedIterator(object):
    stack=None
    nextEl=None

    def __init__(self, nestedList):
        """
        Initialize your data structure here.
        :type nestedList: List[NestedInteger]
        """
        self.stack=[]
        
        self.stack.append(iter(nestedList))
        self.nextEl=NestedInteger()

    def next(self):
        """
        :rtype: int
        """
        return (self.nextEl.getInteger())
        
    def hasNext(self):
        """
        :rtype: bool
        """
        while self.stack:
            self.nextEl=next(self.stack[-1],None)
            if(self.nextEl==None):
                self.stack.pop()
            else:
                if(self.nextEl.isInteger()):
                    return True
                else:
                    self.stack.append(iter(self.nextEl.getList()))
        return False