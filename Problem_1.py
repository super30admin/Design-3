'''
341 Flatten Nested List Iterator
https://leetcode.com/problems/flatten-nested-list-iterator/description/

You are given a nested list of integers nestedList. Each element is either an integer or a list whose elements may also be integers or other lists. Implement an iterator to flatten it.

Implement the NestedIterator class:

    NestedIterator(List<NestedInteger> nestedList) Initializes the iterator with the nested list nestedList.
    int next() Returns the next integer in the nested list.
    boolean hasNext() Returns true if there are still some integers in the nested list and false otherwise.

Your code will be tested with the following pseudocode:
initialize iterator with nestedList
res = []
while iterator.hasNext()
    append iterator.next() to the end of res
return res

If res matches the expected flattened list, then your code will be judged as correct.

Example 1:
Input: nestedList = [[1,1],2,[1,1]]
Output: [1,1,2,1,1]
Explanation: By calling next repeatedly until hasNext returns false, the order of elements returned by next should be: [1,1,2,1,1].

Example 2:
Input: nestedList = [1,[4,[6]]]
Output: [1,4,6]
Explanation: By calling next repeatedly until hasNext returns false, the order of elements returned by next should be: [1,4,6].

Constraints:
1 <= nestedList.length <= 500
The values of the integers in the nested list is in the range [-106, 106].

Solution:
1. Eager Approach: Do eager evaluation. In init, we flatten the list by using dfs and store the flattened list in a new list new[]. Then for each next() call, we retrieve the new[i], where i is the index of the element, and increment index by 1. For each hasNext() call, we check if index < N, N = no. of elements in new[]

Caveat: We should not be building a flattened list prior to calling next(). This is called eager evaluation. Ideally, the list should be flattened using lazy evaluation, i.e. flatten one element at a time only when calling next(). That is, one next() call flattens one element.

Let N = no of elements, L = level of depth (number of nested lists inside original list).

Space for __init__ is O(N) for new[] and L for dfs recursion stack
                Time        Space
__init__:       O(N)        O(N+L)
next():         O(1)        O(1)
hasNext():      O(1)        O(1)

2. Lazy approach: Do lazy evaluation by keeping a stack of iterators to track where we are in each nested list. Every time hasNext() is called, we go as deep as needed to find the next integer. If we hit an integer, we save it and return true; else we just dive deeper.


Let N = no of elements, L = level of depth (number of nested lists inside original list)

                Time        Space
__init__:       O(1)        O(1)
next():         O(1)        O(1)
hasNext():      O(N+L)      O(L)

https://www.youtube.com/watch?v=5eLhy5pxLEA&t=3478s

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

class NestedIteratorEager:
    def __init__(self, nestedList):
        ''' Time: O(N), Space: O(N + L) '''
        self.list = []
        self.idx = 0
        self.dfs(nestedList)

    def dfs(self, nestedList):
        for ne in nestedList:
            if ne.isInteger():
                self.list.append(ne.getInteger())
            else:
                self.dfs(ne.getList())

    def next(self):
        ''' Time: O(1), Space: O(1) '''
        val = self.list[self.idx]
        self.idx += 1
        return val

    def hasNext(self):
        ''' Time: O(1), Space: O(1) '''
        return self.idx < len(self.list)


class NestedIteratorLazy:
    def __init__(self, nestedList):
        ''' Time: O(1), Space: O(1) '''
        self.stack = [iter(nestedList)] # default Python iterator
        self.nextEl = None

    def next(self) -> int:
        ''' Time: O(1), Space: O(1) '''
        self.nextEl.getInteger()

    def peek_stack(self):
        ''' Time: O(1), Space: O(1) '''
        return self.stack[-1]

    def hasNext(self) -> bool:
        ''' Time: O(N+L), Space: O(L) '''
        while self.stack:
            self.nextEl = next(self.peek_stack(), None)
            if self.nextEl is None:
                self.stack.pop()
            else:
                if self.nextEl.isInteger():
                    return True
                else:
                    self.stack.append(iter(self.nextEl.getList()))
        return False


# nestedList = [[1,1],2,[1,1]]
# i, v = NestedIteratorEager(nestedList), []
# while i.hasNext():
#     val = i.next()
#     v.append(val)