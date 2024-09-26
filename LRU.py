"""
The approach here is to use a orderedDict which is a high performance container type of Hashmap in python.
This uses LinkedList internally, this maintains the order of the dictionary, here in the get operation if the
key is not present we return - 1, if key is present we take the value and append it to the starting of 
the key value, and then return the value.
For the put operation we if the key is present in the hashmap then we pop the key and add the new value
of the key, if the capacity is full we remove the least used key value pair, else we decrease the capacity.
LeetCode - Running 
Time complexity - O(1)
Space complexity - O(N)

Is this solution accepted for interview ?
"""

class LRUCache(object):

    def __init__(self, capacity):
        self.hashmap = collections.OrderedDict()
        self.capacity = capacity
        """
        :type capacity: int
        """
        

    def get(self, key):
        if key not in self.hashmap:
            return - 1
        top = self.hashmap.pop(key)
        self.hashmap[key] = top
        return top                   
    
        """
        :type key: int
        :rtype: int
        """
        

    def put(self, key, value):
        if key in self.hashmap:
            self.hashmap.pop(key)
        else:
            if self.capacity > 0:
                self.capacity -= 1
            else:
                self.hashmap.popitem(last=False)
        self.hashmap[key] = value