# O(N) TIME AND O(N) SPACE FOR CONSTRUCTOR WHERE N IS NO.OF INTEGERS
# O(1) TIME AND O(1) SPACE FOR NEXT AND HASNEXT METHODS 
class NestedIterator:
    def __init__(self, nestedList: [NestedInteger]):
        self.nestedList = nestedList
        self.que = []
        self.flatten(self.nestedList)
    
    def flatten(self,list):
        for i in list:
            if i.isInteger():
                self.que.append(i.getInteger())
            else:
                self.flatten(i.getList())
    
    def next(self) -> int:
        return self.que.pop(0)
        
    
    def hasNext(self) -> bool:
         return len(self.que)