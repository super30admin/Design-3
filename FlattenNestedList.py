class NestedIterator(object):

    def __init__(self, nestedList):
        """
        Initialize your data structure here.
        :type nestedList: List[NestedInteger]
        """
        self.queue = []
        self.dfs(nestedList)
    
    def dfs(self, nestedList):
        for el in nestedList:
            if el.isInteger():
                self.queue.append(el.getInteger())
            else:
                self.dfs(el.getList())
        

    def next(self):
        """
        :rtype: int
        """
        return self.queue.pop(0)
        

    def hasNext(self):
        """
        :rtype: bool
        """
        return len(self.queue)
        

# Your NestedIterator object will be instantiated and called as such:
# i, v = NestedIterator(nestedList), []
# while i.hasNext(): v.append(i.next())