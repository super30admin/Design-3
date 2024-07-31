# I created a solution to flatten a nested list of integers into a simple list using a DFS approach. The constructor (__init__) takes a nestedList and uses a helper function to recursively traverse the list. If an element is an integer, it appends it to a final list; if it's a nested list, the helper function calls itself on that nested list. This effectively flattens the entire structure into a single list of integers stored in self.final. The next method returns the next integer by popping the first element of self.final, while the hasNext method checks if there are any remaining integers in self.final. The time complexity of the constructor is O(N), where N is the total number of integers in all nested lists, because each integer is visited once. The space complexity is also O(N) due to the storage of all integers in the final list. The next and hasNext methods have a time complexity of O(1).

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
        final = []
        def helper(lst):

            for i in range(len(lst)):
                if lst[i].isInteger():
                    final.append(lst[i].getInteger())
                else:
                    helper(lst[i].getList())
        helper(nestedList)
        self.final = final

    def next(self) -> int:
       return self.final.pop(0) 
    
    def hasNext(self) -> bool:
        return len(self.final) > 0
         

# Your NestedIterator object will be instantiated and called as such:
# i, v = NestedIterator(nestedList), []
# while i.hasNext(): v.append(i.next())