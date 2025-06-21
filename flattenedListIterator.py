"""
Use a stack to store nested elements in reverse order
Flatten only as needed to find next integer, when hasNext is called
next() pops and returns top integer from stack
"""
"""
Time Complexity: Amortized O(1) per next() and hasNext() call over the entire list
Space Complexity: O(N) — all elements stored on stack

"""


from typing import List, Optional

class NestedInteger:
    def __init__(self, value):
        if isinstance(value, int):
            self._integer = value
            self._list = None
        else:
            self._integer = None
            self._list = [NestedInteger(v) for v in value]

    def isInteger(self) -> bool:
        return self._integer is not None

    def getInteger(self) -> Optional[int]:
        return self._integer

    def getList(self) -> Optional[List['NestedInteger']]:
        return self._list


class NestedIterator:
    def __init__(self, nestedList: List[NestedInteger]):
        self.stack = nestedList[::-1]  # Start with reversed list for LIFO behavior

    def next(self) -> int:
        return self.stack.pop().getInteger()

    def hasNext(self) -> bool:
        while self.stack:
            top = self.stack[-1]
            if top.isInteger():
                return True
            self.stack.pop()
            self.stack.extend(reversed(top.getList()))
        return False


if __name__ == "__main__":
    nestedList = [NestedInteger(1), NestedInteger([4, [6]])]
    i = NestedIterator(nestedList)
    result = []
    while i.hasNext():
        result.append(i.next())
    print(result)
