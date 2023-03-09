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
        self.stack = []
        self.cursor = None
        #if nestedlist exists push the iterator of nestedlist into stack
        if nestedList:
            self.stack.append(iter(nestedList))
        

    #Time complexity - O(1) 
    #Space complexity - O(1)
    def next(self) -> int:
        #cursor points to next element, hence return the value in cursor 
        val = self.cursor
        self.cursor = None
        return val
        

        
    #Time complexity - O(1) averagely, but worst case O(d) where d is the depth of list
    #Space complexity - O(1) averagely, but worst case O(d) where d is the depth of list
    def hasNext(self) -> bool:

        while self.stack and self.cursor==None:
            
            #check if the top of stack has an iterator which 
            #holds nested list of nested integer
            iterator = self.stack[-1]
            curNested = next(iterator, None)

            #if the iterator has no next elements then pop it from the stack
            if curNested == None:
                self.stack.pop()
                continue
            
            #if the iterator next element is integer then set it to cursor and return True
            #else push the ietrator of the nested list into stack.
            if curNested.isInteger():
                self.cursor = curNested.getInteger()
                return True
            else:
                self.stack.append(iter(curNested.getList()))
        

        return False
        


#-------------------------------------OR-------------------------------------------------

class NestedIterator:
    def __init__(self, nestedList: [NestedInteger]):
        self.stack = []
        for i in range(len(nestedList)-1, -1, -1):
            self.stack.append(nestedList[i])
        

    #Time complexity - O(1) 
    #Space complexity - O(1)
    def next(self) -> int:
        return self.stack.pop().getInteger()
        

        
    #Time complexity - O(1) averagely, but worst case O(d) where d is the depth of list
    #Space complexity - O(1) averagely, but worst case O(d) where d is the depth of list
    def hasNext(self) -> bool:
        if len(self.stack)==0:  #if stack is empty then no next element present
            return False
        
        #if top of stack is list then add to stack and call hasNext until integer is found
        while not self.stack[-1].isInteger():      
            nList = self.stack.pop().getList()
            for i in range(len(nList)-1,-1,-1):
                self.stack.append(nList[i])
            return self.hasNext()

        return True #if top of stack is integer return true
        

# #-------------------------------------OR-------------------------------------------------

# #below solution does not behave like iterator 

class NestedIterator:
    def __init__(self, nestedList: [NestedInteger]):
        self.list = []
        self.i = 0
        self.dfs(nestedList)
    

    def dfs(self, nestedIntegerList):
        for i in range(len(nestedIntegerList)):
            if nestedIntegerList[i].isInteger():
                self.list.append(nestedIntegerList[i].getInteger())
            else:
                self.dfs(nestedIntegerList[i].getList())

    #Time complexity - O(1) but extra time O(n) and space O(n) used to perform dfs and store nums in a new list. Here n is length of nestedList
    #Space complexity - O(1)
    def next(self) -> int:
        if self.i<len(self.list):
            num = self.list[self.i]
            self.i += 1
            return num
        
       #Time complexity - O(1) but extra time O(n) and space O(n) used to perform dfs and store nums in a new list. Here n is length of nestedList
    #Space complexity - O(1)
    def hasNext(self) -> bool:
        return self.i<len(self.list)
         

# Your NestedIterator object will be instantiated and called as such:
# i, v = NestedIterator(nestedList), []
# while i.hasNext(): v.append(i.next())