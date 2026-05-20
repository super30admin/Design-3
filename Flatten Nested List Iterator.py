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

#recursive.
#1) isInterger()=True then we get int. else thats a list so use getList()
#for iterator, we have hasnext() and next()
#flow: 1)boolean check: isInterger? 
#yes: getInterger, put in res list
#no: getList, recursive iteration on this element, again use isInterger? :yes, getInterger else: getList...

# class NestedIterator:
#     def __init__(self, nestedList):
#         self.result = []
#         self.idx = 0
#         self.dfs(nestedList)

#     def dfs(self, nestedList):
#         for el in nestedList:
#             if el.isInteger():
#                 self.result.append(el.getInteger())
#             else:
#                 self.dfs(el.getList())

#     def next(self) -> int:
#         toReturn = self.result[self.idx]
#         self.idx += 1
#         return toReturn

#     def hasNext(self) -> bool: #only for the end check
#         return self.idx != len(self.result)
         
#with iterator
#data keeps changing, so iterators should be lazy loading: inculcate dynamicity ie use controlled recursion ie controlled stack

# l = [1, [2,3], 4]
# this is NOT a flat list. so a single iterator walking left to right will hit [2,3] as one blob — we need to go INSIDE it too, so we have 2 iterators,one native ie original and other ie nested iterator which we push into stack when we get a nestedint of type list. 

#A)peek at next el , if nested int of type int, hasNext=true ,if nested int of type list , push new iter ie new nested pointer to stack , 3)if iter exhausted, pop it out of stack 4) if stack is empty ie entire list is done, hasNext= false. B)if nested int of type int, next() consumes the int and returns it and advances the native pointer - thats it?


class NestedIterator:
    def __init__(self, nestedList):
        self.st = [iter(nestedList)] 
        self.nextel=0
        
    def next(self) -> int:
        return self.nextel.getInteger()

    def hasNext(self) -> bool: 
    #     while self.stack:
    # peek current element of top iter
    # ...keep looping until you find an integer or stack is empty
        while self.st:
            self.nextel=next(self.st[-1],None)
            if self.nextel==None:
                self.st.pop()
            else:
                if self.nextel.isInteger():
                    return True
                else:
                    self.st.append(iter(self.nextel.getList()))
        return False

# Your NestedIterator object will be instantiated and called as such:
# i, v = NestedIterator(nestedList), []
# while i.hasNext(): v.append(i.next())
