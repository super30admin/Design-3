"""
Leetcode-https://leetcode.com/problems/flatten-nested-list-iterator/
TC- O(N) where N is total number of integers in nested list, SC- O(d), where d is max depth of nestedInteger.
Challenges-Where should the logic be? Initially I had it in next() and it was returning an extra null because when the
last integer was processed and returned, my stack wasn't popped and hasNext was returning True after check if the stack
is empty. Since it came true, next was called again and Nothing was returned but null was added to the result.
Lecture-Lecture-https://youtu.be/WsIi8VGX5s0
FAQ-


You are given a nested list of integers nestedList. Each element is either an integer or a list whose elements may also
be integers or other lists. Implement an iterator to flatten it.

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
Explanation: By calling next repeatedly until hasNext returns false, the order of elements returned by next should be:
[1,1,2,1,1].

Example 2:
Input: nestedList = [1,[4,[6]]]
Output: [1,4,6]
Explanation: By calling next repeatedly until hasNext returns false, the order of elements returned by next should be: [1,4,6].


Constraints:
1 <= nestedList.length <= 500
The values of the integers in the nested list is in the range [-106, 106].
"""

"""
Ideation - Using native iterator
Our objective is to create a iterator for this problem. The property of iterator is to maintain it's dynamic nature.
Which means, if the values in a list iterator are changed during run time, it can be handled.
NOTE- The dynamic nature has it's constraints too, like if user tries to change value or delete the element currently
being accessed by iterator is not possible. So, by dynamic it means dynamic as much as possible.

To solve this problem, we shall use native iterator which has next and hasNext functionality so we don't have to build 
it, and we will build our solution on top of it. NOTE- Native iterator should not solve the whole problem but facilitate 
our solution, which is the case in our problem.

If the iterator has next and it's next is an integer, return it. If it's nested list, keep processing it (using while 
loop), until you hit and integer and repeat.
Note- Iterator always points to its next. If you call .next(), iterators next pointer has already move to it's next.
So, we will lose our current next and to solve this situation, we need to save our current next, if we need it later. 

This problem is same as solving parenthesis problem. But, here we cannot recursively call DFS since then it will have 
no dynamic ability. So ,we need to make a controlled recursion using stack and push the current iterators into it if
next is list.  
"""


# """
# This is the interface that allows for creating nested lists.
# You should not implement it, or speculate about its implementation
# """
#
# class NestedInteger:
#     def isInteger(self) -> bool:
#         """
#        @return True if this NestedInteger holds a single integer, rather than a nested list.
#        """
#
#     def getInteger(self) -> int:
#         """
#        @return the single integer that this NestedInteger holds, if it holds a single integer
#        Return None if this NestedInteger holds a nested list
#        """
#
#     def getList(self):
#         """
#        @return the nested list that this NestedInteger holds, if it holds a nested list
#        Return None if this NestedInteger holds a single integer
#        """


class NestedIterator:
    def __init__(self, nestedList):
        self.nextInteger = None
        self.stack = []
        self.stack.append(iter(nestedList))

    def next(self) -> int:
        # hasNext() will be called before calling next() which will pull our next integer to be used here.
        nextInt = self.nextInteger
        self.nextInteger = None
        return nextInt

    def hasNext(self) -> bool:
        while self.stack:
            # storing next ele since calling next() will move to iter next pointer to it's next position, and we will
            # lose our current next.
            nextEle = next(self.stack[-1], False)
            # if iterator does not have next element, pop it from the stack
            if nextEle is False:
                self.stack.pop()
            # if iterator has next
            else:
                # if next is an integer return integer
                if nextEle.isInteger():
                    self.nextInteger = nextEle.getInteger()
                    return True
                # if nestedList, add it to stack
                else:
                    self.stack.append(iter(nextEle.getList()))

        # has next if something is in stack
        return False


# Your NestedIterator object will be instantiated and called as such:
# nestedList = [[1, 1], 2, [1, 1]]
nestedList = [1, [4, [6]]]
i, v = NestedIterator(nestedList), []
while i.hasNext(): v.append(i.next())
print(v)
