# T.C --> O(N+L)
# S.C --> o(N+D)
class NestedIterator:
    def __init__(self, nestedList: [NestedInteger]):
        self.queue = deque()
        self.flattenList(nestedList)

    def flattenList(self, nestedList):
        for item in nestedList:
            if not item.isInteger():
                self.flattenList(item.getList())
            else:
                self.queue.append(item.getInteger())

    def next(self) -> int:
        return self.queue.popleft()

    def hasNext(self) -> bool:
        return len(self.queue) > 0
