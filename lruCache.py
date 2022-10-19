#lru cache: used ordered dict from python collections library.
#ordered dict rearranges dict order. Can move an element to end or beginning using move_to_end
#popitem can be used to pop based on either LIFO or FIFO order.

#time complexity:O(1) for put and get using ordered dict
#space: O(capacity) since we only store capacity number of elements in ordered dict

from collections import OrderedDict
class LRUCache(OrderedDict):

    def __init__(self, capacity: int):
        self.capacity=capacity

    def get(self, key: int) -> int:
        if key not in self:
            return -1
        self.move_to_end(key)
        return self[key]

    def put(self, key: int, value: int) -> None:
        if key in self:
            self.move_to_end(key)
        self[key]=value
        if len(self)>self.capacity:
            self.popitem(last=False)

# Your LRUCache object will be instantiated and called as such:
# obj = LRUCache(capacity)
# param_1 = obj.get(key)
# obj.put(key,value)