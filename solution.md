# Problem 1: Flatten Nested List Iterator
## Time Complexity :
O(n)

## Space Complexity :
O(n)

## Did this code successfully run on Leetcode :
Yes.

## Any problem you faced while coding this :
No.

## Your code here along with comments explaining your approach
### Solution:
      class NestedIterator:
          def __init__(self, nestedList: [NestedInteger]):
              self.queue = collections.deque(nestedList)



          def next(self) -> int:
              cur = self.queue.popleft()
              return cur.getInteger()


          def hasNext(self) -> bool:
              while self.queue:
                  top = self.queue[0]
                  if top.isInteger():
                      return True
                  lis = self.queue.popleft().getList()
                  self.queue.extendleft(lis[::-1])
              return False

# Problem 2: LRU Cache
## Time Complexity :
O(n)

## Space Complexity :
O(n)

## Did this code successfully run on Leetcode :
Yes.

## Any problem you faced while coding this :
No.

## Your code here along with comments explaining your approach
### Solution:

      from collections import OrderedDict
      class LRUCache():
          def __init__(self, size):
              self.size, self.cache = size, OrderedDict()

          def get(self, key):
              value = self.cache.pop(key, None)
              if value is None: return -1
              self.cache[key] = value
              return value

          def put(self, key, value):     
              if self.cache.pop(key, None) is None and self.size == len(self.cache):
                  self.cache.popitem(last=False)
              self.cache[key] = value
