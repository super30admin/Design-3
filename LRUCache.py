from collections import OrderedDict
class LRUCache(object):

    def __init__(self, capacity):
        """
        :type capacity: int
        """
        self.sz = capacity
        self.dict = OrderedDict()

    def get(self, key):
        """
        :type key: int
        :rtype: int
        """
        if key in self.dict:
            value = self.dict[key]
            del self.dict[key]
            self.dict[key] = value
            return value
        return -1

    def put(self, key, value):
        """
        :type key: int
        :type value: int
        :rtype: None
        """
        if key in self.dict:
            del self.dict[key]
            self.dict[key] = value
        elif len(self.dict) < self.sz:
            self.dict[key] = value
        else:
            self.dict.popitem(last= False)
            self.dict[key] = value


# Your LRUCache object will be instantiated and called as such:
# obj = LRUCache(capacity)
# param_1 = obj.get(key)
# obj.put(key,value)