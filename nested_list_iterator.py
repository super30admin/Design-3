from collections import deque
class NestedIterator:

    def fillQueue(self, nestedList):
        for i in nestedList:
            if(i.isInteger()):
                self.q.append(i.getInteger())
            else:
                self.fillQueue(i.getList())

    def __init__(self, nestedList: [NestedInteger]):
        # print(nestedList[0][])
        self.q=deque([])
        self.fillQueue(nestedList)
    
    def next(self) -> int:
        return self.q.popleft()
        
    def hasNext(self) -> bool:
        if(len(self.q)):
            return True
        return False