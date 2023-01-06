#Time Complexity :  O(n)
#Space Complexity :  O(n)
#Did this code successfully run on Leetcode : Yes

#code along with comments
class NestedIterator:
    def __init__(self, nestedList: [NestedInteger]):
        def flatten(array):                                         #recursive function
            result = []
            for i in array:
                if i.isInteger():                                   #if value in the array is integer
                    result.append(i.getInteger())                   #we add it on to result array
                else:
                    result.extend(flatten(i.getList()))             #otherwise we call getList and extend it
                                                                    #to the result array
            return result
        
        self.output = deque(flatten(nestedList))                    #finally we store it as a queue
    
    def next(self) -> int:
        return self.output.popleft()                                #if value exists we pop from queue and return                          
    
    def hasNext(self) -> bool:
        if self.output:                                             #if there is value in output list
            return True                                             #we return true
        else:
            return False                                            #else we return false
