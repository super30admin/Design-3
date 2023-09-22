'''
Approach 1 : Using DFS

Time Complexity:
1. overall : O(n+l) #where n is number of all of the intergers in the input
2. next : O(1)
3. hasNext : O(1)

Space Complexity:
1. Overall : O(n + d) #where d is the stack size, and we take n because we are storing all the numbers in the result array
2. next : No space
3. hashNext : No space

'''



# """
# This is the interface that allows for creating nested lists.
# You should not implement it, or speculate about its implementation
# """
#class NestedInteger:
#    def isInteger(self) -> bool:
#        """
#        @return True if this NestedInteger holds a single integer, rather than a nested list.
#        """
#
#    def getInteger(self) -> int:
#        """
#        @return the single integer that this NestedInteger holds, if it holds a single integer
#        Return None if this NestedInteger holds a nested list
#        """
#
#    def getList(self) -> [NestedInteger]:
#        """
#        @return the nested list that this NestedInteger holds, if it holds a nested list
#        Return None if this NestedInteger holds a single integer
#        """

class NestedIterator:
    def __init__(self, nestedList: [NestedInteger]):
        self.result = []
        self.nestedList = nestedList
        self.dfs(nestedList)
        self.obj = NestedInteger()
        self.index = 0
        
    def dfs(self, nestedList: [NestedInteger]):
        for n in nestedList:
            if n.isInteger():
                self.result.append(n)
            else:
                self.dfs(n.getList())
    
    def next(self) -> int:
        if self.hasNext():
            val = self.result[self.index]
            self.index += 1 
            return val
        else:
            return None
    
    def hasNext(self) -> bool:
        return self.index != len(self.result)
         

# Your NestedIterator object will be instantiated and called as such:
# i, v = NestedIterator(nestedList), []
# while i.hasNext(): v.append(i.next())


'''
Approach 2 : Using stack + iterator, since this has reference to the list we don't really store the actual value and always get the updated value

Time Complexity: O(n+l) l is the number of list and n is the total number of elements
Space Complexity: O(l)
'''

class NestedIterator:
    def __init__(self, nestedList: [NestedInteger]):
        self.stack = [[nestedList, 0]]
        
    def next(self):
        self.hasNext()
        nestedList, index = self.stack[-1]
        self.stack[-1][1] += 1
        return nestedList[index].getInteger()
            
    def hasNext(self):
        while self.stack:
            nestedList, index = self.stack[-1]
            if index == len(nestedList):
                self.stack.pop()
            else:
                x = nestedList[index]
                if x.isInteger():
                    return True
                self.stack[-1][1] += 1
                self.stack.append([x.getList(), 0])
        return False