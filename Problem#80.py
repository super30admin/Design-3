'''
146. LRU Cache
Problem Link: https://leetcode.com/problems/lru-cache/description/
'''

# Time Complexity : O(1) for get and put operations
# Space Complexity : O(capacity) for storing the cache
# Did this code successfully run on Leetcode : Yes, this implementation passed all test cases.
# Any problem you faced while coding this : Yes, managing the order of elements in the cache was a bit tricky, but using a list to track the order helped.

'''
Problem Description:
Design a data structure that follows the constraints of a Least Recently Used (LRU) cache.
It should support the following operations: get and put.
- get(key): Returns the value of the key if the key exists in the cache, otherwise returns -1.
- put(key, value): If the key already exists, update the value. If the key does not exist, add the key-value pair to the cache.
If the cache reaches its capacity, it should invalidate the least recently used item before inserting a new item.
'''

class LRUCache: # humne ek LRUCache class banaya hai jo ki LRU Cache ke operations ko handle karega (we have created an LRUCache class that will handle the operations of the LRU Cache)
    '''
    Intuition:
    To implement an LRU cache, we can use a combination of a dictionary and a doubly linked list
    The dictionary will be used to store the key-value pairs and the doubly linked list will be used to
    keep track of the order in which the keys were accessed.
    '''

    def __init__(self, capacity: int): # idhar hum constructor banate hain jo ki cache ki capacity ko set karega (here we create a constructor that will set the capacity of the cache)
        self.capacity = capacity # cache ki capacity ko store karte hain (we store the capacity of the cache)
        self.cache = {} # ye dictionary hai jo ki key-value pairs ko store karega (this is a dictionary that will store the key-value pairs)
        self.order = [] # ye list hai jo ki keys ki order ko store karegi (this is a list that will store the order of the keys)
        self.size = 0 # ye variable hai jo ki cache ki current size ko store karega (this is a variable that will store the current size of the cache)

    def get(self, key: int) -> int: # ye method key ko get karega agar wo cache me hai to (this method will get the key if it is in the cache)
        if key in self.cache: # agar key cache me hai to (if the key is in the cache)
            # Access the key and move it to the end of the order list
            # Move the accessed key to the end of the order list
            self.order.remove(key) # hum key ko order list se remove karte hain (we remove the key from the order list)
            self.order.append(key) # aur uske baad key ko order list ke end me add karte hain (and then we add the key to the end of the order list)
            return self.cache[key] # aur key ka value return karte hain (and we return the value of the key)
        else: # agar key cache me nahi hai to (if the key is not in the cache)
            return -1 # to hum -1 return karte hain (then we return -1)

    def put(self, key: int, value: int) -> None: # ye method key-value pair ko cache me put karega (this method will put the key-value pair in the cache)
        if key in self.cache: # agar key cache me hai to (if the key is in the cache)
            # Update the value and move the key to the end of the order list
            self.cache[key] = value # hum key ka value update karte hain (we update the value of the key)
            self.order.remove(key) # aur uske baad key ko order list se remove karte hain (and then we remove the key from the order list)
            self.order.append(key) # aur uske baad key ko order list ke end me add karte hain (and then we add the key to the end of the order list)
        else: # agar key cache me nahi hai to (if the key is not in the cache)
            if self.size >= self.capacity: # agar cache ki size capacity se zyada hai to (if the size of the cache is greater than or equal to the capacity)
                # Remove the least recently used item
                lru_key = self.order.pop(0) # hum order list se least recently used key ko remove karte hain (we remove the least recently used key from the order list)
                del self.cache[lru_key] # aur uske baad us key ko cache se delete karte hain (and then we delete that key from the cache)
                self.size -= 1 # aur cache ki size ko 1 se kam karte hain (and we decrease the size of the cache by 1)
            # Add the new key-value pair
            self.cache[key] = value # hum key-value pair ko cache me add karte hain (we add the key-value pair to the cache)
            self.order.append(key) # aur uske baad key ko order list ke end me add karte hain (and then we add the key to the end of the order list)
            self.size += 1 # aur cache ki size ko 1 se badhate hain (and we increase the size of the cache by 1)


# Your LRUCache object will be instantiated and called as such:
# obj = LRUCache(capacity)
# param_1 = obj.get(key)
# obj.put(key,value)