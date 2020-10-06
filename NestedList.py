"""
341. Flatten Nested List Iterator

Time : flatten() - O(n); next() - O(1); hasNext() - O(1) where n = num of elements
Space : flatten() - O(n); next() - O(1); hasNext() - O(1) where n = num of elements
Successfully excecuted on leetcode


Approach:
1. Using queue
2. Iterate through each element in nestedList.
3. At each step we've two options - If element is integer append it to the queue. Else if element is list recurse again for all the elements in a list
3. Implement next function using queue and popleft and hasNext to check if there's any elements left in queue

"""

class NestedIterator:
    def __init__(self, nestedList):
        """
        Initialize your data structure here.
        :type nestedList: List[NestedInteger]
        """
        self.queue=deque()
        self.flatten(nestedList)
                    
    def flatten(self,nestedList): #space is O(n) cause of queue
        for ele in nestedList:
            if ele.isInteger():
                self.queue.append(ele.getInteger())
            else:
                self.flatten(ele.getList())

    def next(self):
        """
        :rtype: int
        """
        return self.queue.popleft()

    def hasNext(self):
        """
        :rtype: bool
        """
        return len(self.queue)>0