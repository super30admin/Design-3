'''

## Problem 341: Flattened nested list iterator

## Author: Neha Doiphode
## Date  : 09-11-2022

## Description:
    You are given a nested list of integers nestedList.
    Each element is either an integer or a list whose elements may also be integers or other lists.
    Implement an iterator to flatten it.

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

## Examples:
    Example 1:
        Input: nestedList = [[1,1],2,[1,1]]
        Output: [1,1,2,1,1]
        Explanation: By calling next repeatedly until hasNext returns false,
        the order of elements returned by next should be: [1,1,2,1,1].

    Example 2:
        Input: nestedList = [1,[4,[6]]]
        Output: [1,4,6]
        Explanation: By calling next repeatedly until hasNext returns false,
        the order of elements returned by next should be: [1,4,6].

## Constraints:
    1 <= nestedList.length <= 500
    The values of the integers in the nested list is in the range [-106, 106].


## Time complexity: Please refer to respective doc-strings of different approaches used below.

## Space complexity: Please refer to respective doc-strings of different approaches used below.

'''

from typing import List, Optional
from collections import deque


# """
# This is the interface that allows for creating nested lists.
# You should not implement it, or speculate about its implementation
# """
class NestedInteger:
    def isInteger(self) -> bool:
        """
        @return True if this NestedInteger holds a single integer, rather than a nested list.
        """
        pass

    def getInteger(self) -> int:
        """
        @return the single integer that this NestedInteger holds, if it holds a single integer
        Return None if this NestedInteger holds a nested list
        """
        pass

    def getList(self):
        """
        @return the nested list that this NestedInteger holds, if it holds a nested list
        Return None if this NestedInteger holds a single integer
        """
        pass

class NestedIterator_Using_Queue:
    def __init__(self, nestedList: [NestedInteger]):
        self._q = deque()
        self._flatten_list(nestedList)

    def _flatten_list(self, nestedList: [NestedInteger]):
        '''
        Time complexity: O(n), where n is number of elements present in all lists in the nested list.
        Space complexity: O(n), auxiliary space to store all elements in the queue.
        '''
        for itr in range(len(nestedList)):
            if nestedList[itr].isInteger():
                self._q.append(nestedList[itr].getInteger())
            else:
                self._flatten_list(nestedList[itr].getList())

    def next(self) -> int:
        '''
        Time complexity: O(1), as we are just retrieving from the queue
        Space complexity: O(n), auxiliary space to store all elements in the queue.
        '''
        if self.hasNext():
            return self._q.popleft()

    def hasNext(self) -> bool:
        '''
        Time complexity: O(1), as we are just retrieving from the queue
        Space complexity: O(n), auxiliary space to store all elements in the queue.
        '''
        return len(self._q) != 0


class NestedIterator_Using_List:
    '''
    Time and space complexity is same as using queue.
    '''
    _idx = 0
    def __init__(self, nestedList: [NestedInteger]):
        self._l = []
        self._idx = 0
        self._flatten_list(nestedList)

    def _flatten_list(self, nestedList: [NestedInteger]):
        '''
        Time complexity: O(n), where n is number of elements present in all lists in the nested list.
        Space complexity: O(n), auxiliary space to store all elements in the list.
        '''
        for itr in range(len(nestedList)):
            if nestedList[itr].isInteger():
                self._l.append(nestedList[itr].getInteger())
            else:
                self._flatten_list(nestedList[itr].getList())

    def next(self) -> int:
        '''
        Time complexity: O(1), as we are just retrieving from the list accessing the index directly
        Space complexity: O(n), auxiliary space to store all elements in the list.
        '''
        if self.hasNext():
            val = self._l[self._idx]
            self._idx += 1
            return val

    def hasNext(self) -> bool:
        '''
        Time complexity: O(1), as we are just retrieving from the list accessing the index directly
        Space complexity: O(n), auxiliary space to store all elements in the list.
        '''
        return len(self._l) != self._idx


class NestedIterator_Using_Genarator:
    def __init__(self, nestedList: [NestedInteger]):
        '''
        Time complexity: O(1), here we only create generator object.
                               The object does not invoke any code here.
        Space complexity: O(D), Where D is depth of nested list.
                               We recursively call _init_generator function for nested lists.
                               Therefore, the runtime stack uses memory proportional to the
                               current depth of the list.
                               Seeing as the largest depth is D,
                               the space complexity is O(D).
        '''
        self._generator = self._init_generator(nestedList)
        self._peeked = None

    def _init_generator(self, nestedList: [NestedInteger]) -> "Generator[int]":
        for item in nestedList:
            if item.isInteger():
                yield item.getInteger()
            else:
                yield from self._init_generator(item.getList())

    def next(self) -> int:
        '''
        Time complexity: O(1), we simply get and return the value.
        Space complexity: O(1), No auxiliary space is used.
        '''
        if self.hasNext():
            next_integer, self._peeked = self._peeked, None
            return next_integer

    def hasNext(self) -> bool:
        # when a yield is encountered, control is handed back to the caller.
        # so here we need to check if self._peeked is not None as
        # when we call next later on, control will be returned back to beginning of of hasNext
        # each time after the yield is encountered.
        '''
        Time complexity: O(1), we simply invoke generator once and return the boolean result.
        Space complexity: O(1), No auxiliary space is used.
        '''
        if self._peeked != None:
            return True
        try:
            self._peeked = next(self._generator)
            return True
        except:
            return False

# Your NestedIterator object will be instantiated and called as such:
# i, v = NestedIterator(nestedList), []
# while i.hasNext(): v.append(i.next())
