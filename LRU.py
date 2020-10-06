"""
146. LRU Cache

Successfully excecuted on leetcode

Approach - 
1. Hashmap + DoublyLinkedList
2. Create DLL - to get, put and remove in constant time and space
3. Create HashMap - to store key,value pair
4. Put : Whenever you are putting a key-value pair in, check whether the key already exists.
    If key exists, you get that key-value pair, update the value and put that pair at the the end of the cache.
    If the key does not exist, you check whether the cache size is already at limit.
    If the cache size is at limit, pop the key-value pair at the and push the new key-value pair at the end of the cache.
    If the cache is still under size limit, push the new key-value pair at the end of the cache.
5. Get : Check whether the key exists in the cache.
    If it exists, put that key-value pair at the end of the cache and return the value.
    If it does not exist, return -1.

"""

class DoublyLinkedList:
    def __init__(self,k,v):
        self.key = k
        self.val = v
        self.prev = None
        self.next = None

class LRUCache:
    def __init__(self,capacity): #time = O(1), space = O(capacity)
        self.capacity = capacity
        self.dic = dict()
        self.head = DoublyLinkedList(0,0)
        self.tail = DoublyLinkedList(0,0)
        self.head.next = self.tail
        self.tail.prev = self.head

    def get(self,key): # time = space = O(1)
        if key in self.dic:
            n = self.dic[key]
            self.remove(n)
            self.add(n) #add as recently used
            return n.val
        return -1

    def put(self,key,val):# time = space = O(1)
        if key in self.dic:
            self.remove(self.dic[key])
        n = DoublyLinkedList(key,val)
        self.add(n)
        self.dic[key] = n
        if len(self.dic) > self.capacity:
            n = self.head.next
            self.remove(n)
            del self.dic[n.key]

    def remove(self,node): # time = space = O(1)
        p = node.prev
        n = node.next
        p.next = n
        n.prev = p

    def add(self,node): # time = space = O(1) 
        #adds at the end
        p = self.tail.prev 
        p.next = node
        self.tail.prev = node
        node.prev = p
        node.next = self.tail