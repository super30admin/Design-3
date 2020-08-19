#Time Complexity - O(n) where n is the number of total integers in the nested list
#Space Complexity - O(n) for the stack
#Works on leetcode - yes
#Approach - Here we need to  iterate over the original data structure. I keep the current progress in a stack. The hasNext function
#tries to find an integer. The next function returns the found integer and moves on. 
class NestedIterator:
    def __init__(self, nestedList: [NestedInteger]):
        self.stack = [[nestedList, 0]]
        
    
    def next(self) -> int:
        #self.hasNext()
        nestedlist, i = self.stack[-1]
        self.stack[-1][1] +=1
        return nestedlist[i].getInteger()
        
    
    def hasNext(self) -> bool:
        s = self.stack
        while s:
            nestedlist, i = s[-1]
            if i==len(nestedlist):
                s.pop()
            else:
                x = nestedlist[i]
                if x.isInteger():
                    return True
                s[-1][1]+=1
                s.append([x.getList(),0])
        return False