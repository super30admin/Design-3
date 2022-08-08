class BruteForceNestedIterator:
    '''
    T = Constructor: O(N + L)
    Space complexity : O(N + D)
    '''
    def __init__(self, nestedList: [NestedInteger]):
        self.nestedList  = nestedList
        self.q = []
        
        def flatten(nestted_list):
            for i in nestted_list:
                if i.isInteger():
                    self.q.append(i.getInteger())
                else:
                    flatten( i.getList()  )
                    
        flatten(self.nestedList)

    def next(self) -> int:
        return self.q.pop(0)
    
    def hasNext(self) -> bool:
        return len(self.q) > 0 
