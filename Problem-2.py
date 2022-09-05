
# 146. LRU Cache

'''
Leetcode all test cases passed: Yes
class NestedIterator:
    def get(self, nestedList: [NestedInteger]):
      Time Complexity: O(1) 
    def put(self, nestedList: [NestedInteger]):
      Time Complexity: O(1) 
'''
from collections import OrderedDict

class LRUCache(object):

    def __init__(self, capacity):
        """
        :type capacity: int
        """
        self.capacity = capacity
        self.d =  OrderedDict()

    def get(self, key):
        """
        :type key: int
        :rtype: int
        """
        try:
            self.d.move_to_end(key)
            return self.d[key]
        except:
            return -1

    def put(self, key, value):
        """
        :type key: int
        :type value: int
        :rtype: None
        """
        try:
            self.d.move_to_end(key)
            self.d[key] = value
        except:
            self.d[key] = value
        if len(self.d) > self.capacity:
            self.d.popitem(last = False)
