# Time Complexity : As follows
# Constructor: O(N) where is the nujmber of all integers
# next : O(1)
# hasNext : O(1)
# Space Complexity : O(N + D) where N is the number of all integers and D is the deepest nested list (recursion).
# Did this code successfully run on Leetcode : Yes
# Any problem you faced while coding this : No



'''Main Idea'''
# use constructor to flatten the list recursively in a cache and then use that cache to give out 
# values in O(1) time.

'''Need to implement a solution that is not so dependent on constructor'''

class NestedIterator:
    def __init__(self, nestedList: [NestedInteger]):
        self.nestedList = nestedList
        self.flatten = []
        self.recur(nestedList)
        self.idx = 0
        
    def recur(self, currList):
        idx = 0
        while idx < len(currList):
            if currList[idx].isInteger():
                self.flatten.append(currList[idx].getInteger())
                idx += 1
            else:
                self.recur(currList[idx].getList())
                idx += 1
        return
        
    def next(self) -> int:
        ret = self.flatten[self.idx]
        self.idx += 1
        return ret
        
    def hasNext(self) -> bool:
        if self.idx < len(self.flatten):
            return True
        else:
            return False
         

# Your NestedIterator object will be instantiated and called as such:
# i, v = NestedIterator(nestedList), []
# while i.hasNext(): v.append(i.next())