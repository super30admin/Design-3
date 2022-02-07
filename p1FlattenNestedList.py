#Time: O(N)
#Space: O(N) due to recursion height

class NestedIterator:
    def __init__(self, nestedList: [NestedInteger]):
        
        def flatten(nl):
            temp = []
            for i in nl:
                if i.isInteger():
                    temp.append(i)
                else: temp.extend(flatten(i.getList()))
            return temp
        
        self.n = deque(flatten(nestedList))
    
    def next(self) -> int:
        return self.n.popleft()
    
    def hasNext(self) -> bool:
        return self.n

# Your NestedIterator object will be instantiated and called as such:
# i, v = NestedIterator(nestedList), []
# while i.hasNext(): v.append(i.next())