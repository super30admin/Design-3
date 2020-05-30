# Time complexity: O(n)
# Space complexity: O(n)
# Did this code successfully run on Leetcode : Yes
# Any problem you faced while coding this : No
class NestedIterator:
    def __init__(self, nestedList):
        self.queue = []
        self.fillQueue(nestedList)

    def fillQueue(self, nestedList):
        for i in nestedList:
            if i.isInteger():
                self.queue.append(i.getInteger())
            else:
                self.fillQueue(i.getList())

    def next(self) -> int:
        return self.queue.pop(0)

    def hasNext(self) -> bool:
        return len(self.queue) > 0
