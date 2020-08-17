class NestedIterator:
    def __init__(self, nestedList: [NestedInteger]):
        self.stack=[[nestedList,0]]
    
    def helper(self):
        while self.stack:
            curr_list=self.stack[-1][0]
            curr_index=self.stack[-1][1]
            
            if len(curr_list)==curr_index:
                self.stack.pop()
                continue
            if curr_list[curr_index].isInteger():
                break
            new_list=curr_list[curr_index].getList()
            self.stack[-1][1]+=1
            self.stack.append([new_list, 0])
            
        
    
    def next(self) -> int:
        self.helper()
        curr_list = self.stack[-1][0]
        curr_index = self.stack[-1][1]
        self.stack[-1][1] += 1
        return curr_list[curr_index].getInteger()
        
    
    def hasNext(self) -> bool:
        self.helper()
        return len(self.stack)>0
         

#Time-Complexity:O(1)
#Space-Complexity:O(L) where L is deepest level List