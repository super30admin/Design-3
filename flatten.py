 # Runtime complexity - O(n) where n is umber of elemenst in the nested integer iterator including ones in list
        # Memory complexity - O(1) excluding the result list
class NestedIterator:
    def __init__(self, nestedList: [NestedInteger]):
        self.queue=[]
        self.fillQueue(nestedList)
        
    
    def next(self) -> int:
        return self.queue.pop(0)
        
    
    def hasNext(self) -> bool:
        # self.queue = []
        if not self.queue:
            return False
        return True
    def fillQueue(self, nestedList):
        for i in nestedList:
            if i.isInteger():
                self.queue.append(i.getInteger())
                print("queue is",self.queue)
            else:
                self.fillQueue(i.getList())