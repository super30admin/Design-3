---------------------------------Nested List Iterator-------------------------------------
# Time Complexity : O(N)
# Space Complexity : O(N)
# Did this code successfully run on Leetcode: Yes
# Any problem you faced while coding this : No
# 
# Here i have created a flatten function , where I can flatten my iterator using a queue and when ever i call has next function, i will check 
# weather my queue is not empty then i will return True, else I will return False. When next function is called I will pop element from queue.

class NestedIterator:
    def __init__(self, nestedList: [NestedInteger]):
        self.nestedList = nestedList
        self.queue = deque()
        self.flatten(self.nestedList)
            
    
    def next(self) -> int:
        if self.hasNext():
            return self.queue.popleft()
        
    def flatten(self, newlist):
        for i in newlist:
            if i.isInteger():
                self.queue.append(i.getInteger())
            else:
                temp = i.getList()
                self.flatten(temp)
                
                
                
        
    def hasNext(self) -> bool:
        return len(self.queue)>0
        
        
---------------------------------Nested List Iterator------------------------------------
# Time Complexity : O(N) number of elements in a list
# Space Complexity : O(M) number of nested lists
# Did this code successfully run on Leetcode: Yes
# Any problem you faced while coding this : No
# 
# Here I have used a stack , instead of flattening the whole list at a time, when ever the has next is called I will giving access to that element.
# when has next is called, I will check the top element of my stack and length of top element is equal to my curr pointer.If yes, then I dont have next element i will pop 
# elements from stack. Else, I will check curr element is isInteger, if yes, return True, else append my current element into stack.

        
class NestedIterator:
    def __init__(self, nestedList: [NestedInteger]):
        self.stack = [[nestedList,0]]
            
    
    def next(self):
        nl, curr = self.stack[-1]
        res = nl[curr].getInteger()
        self.stack[-1][1] +=1
        return res
        
                
    def hasNext(self) -> bool:
        while self.stack:
            nl, curr = self.stack[-1]
            if curr == len(nl):
                self.stack.pop()
            else:
                if nl[curr].isInteger():
                    return True
                self.stack[-1][1] +=1
                self.stack.append([nl[curr].getList(), 0])
        return False