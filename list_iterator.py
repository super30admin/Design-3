#Constructor taking O(n) TIme
#next:O(1)
#hasNext:O(1)
#Space: O(n) Recursion stack
class NestedIterator(object):

    def __init__(self, nestedList):
        """
        Initialize your data structure here.
        :type nestedList: List[NestedInteger]
        """
        self.final = []
        self.position = -1
        self.dfs(nestedList)
        
    def dfs(self,nested_list):
        for nested_integer in nested_list:
            if nested_integer.isInteger():
                self.final.append(nested_integer.getInteger())
            else:
                self.dfs(nested_integer.getList())

    def next(self):
        """
        :rtype: int
        """
        # if self.hasNext(self.position):
        self.position+=1
        return self.final[self.position]
        

    def hasNext(self):
        """
        :rtype: bool
        """
        return len(self.final)>self.position+1