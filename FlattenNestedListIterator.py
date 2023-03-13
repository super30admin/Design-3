"""
Rasika Sasturkar
Time complexity: O(1) for next(), and O(depth of stack) for hasNext() function.
Space complexity: O(depth of stack)

Cannot show the implementation of this code in this program as Leetcode implements
another interface for the solution of this problem. However, I was able to
successfully run the code on Leetcode and pass all the test cases.
"""


class NestedIterator:
    """
    Brute force method is to solve your recursion which wouldn't be a dynamic iterator.
    Optimal solution is to add the lists to the stack everytime you encounter a list
    and iterate over that list using another iterator.
    """

    def __init__(self, nestedList):
        self.stk = [[nestedList, 0]]

    def next(self) -> int:
        self.hasNext()
        last_element, iterator = self.stk[-1]
        self.stk[-1][1] += 1
        return last_element[iterator].getInteger()

    def hasNext(self) -> bool:
        s = self.stk
        while s:
            nested_list, iterator = s[-1]
            if iterator == len(nested_list):
                s.pop()
            else:
                curr = nested_list[iterator]
                if curr.isInteger():
                    return True
                else:
                    s[-1][1] += 1
                    s.append([curr.getList(), 0])
        return False

    # Brute force
    # li = []
    # count = 0
    # def __init__(self, nestedList: [NestedInteger]):
    #     # self.ni = NestedInteger()
    #     self.li = []
    #     self.dfs(nestedList)
    #
    # def dfs(self, nested_list):
    #     # logic
    #     for nl in nested_list:
    #         if nl.isInteger():
    #             self.li.append(nl.getInteger())
    #         else:
    #             self.dfs(nl.getList())
    #
    # def next(self) -> int:
    #     result = self.li[self.count]
    #     self.count += 1
    #     return result
    #
    #
    # def hasNext(self) -> bool:
    #      return self.count != len(self.li)
