'''

## Problem 146: LRU cache

## Author: Neha Doiphode
## Date  : 09-11-2022

## Description:
    Design a data structure that follows the constraints of a Least Recently Used (LRU) cache.

    Implement the LRUCache class:
        LRUCache(int capacity) Initialize the LRU cache with positive size capacity.

        int get(int key) Return the value of the key if the key exists, otherwise return -1.

        void put(int key, int value) Update the value of the key if the key exists. Otherwise,
        add the key-value pair to the cache.

        If the number of keys exceeds the capacity from this operation, evict the least recently used key.

        The functions get and put must each run in O(1) average time complexity.

## Examples:
    Example 1:
        Input: ["LRUCache", "put", "put", "get", "put", "get", "put", "get", "get", "get"]
               [[2], [1, 1], [2, 2], [1], [3, 3], [2], [4, 4], [1], [3], [4]]

        Output: [null, null, null, 1, null, -1, null, -1, 3, 4]

        Explanation
            LRUCache lRUCache = new LRUCache(2);
            lRUCache.put(1, 1); // cache is {1=1}
            lRUCache.put(2, 2); // cache is {1=1, 2=2}
            lRUCache.get(1);    // return 1
            lRUCache.put(3, 3); // LRU key was 2, evicts key 2, cache is {1=1, 3=3}
            lRUCache.get(2);    // returns -1 (not found)
            lRUCache.put(4, 4); // LRU key was 1, evicts key 1, cache is {4=4, 3=3}
            lRUCache.get(1);    // return -1 (not found)
            lRUCache.get(3);    // return 3
            lRUCache.get(4);    // return 4

## Constraints:
    1 <= capacity <= 3000
    0 <= key <= 104
    0 <= value <= 105
    At most 2 * 105 calls will be made to get and put.

## Time complexity: Please refer to respective doc-strings of different approaches used below.

## Space complexity: Please refer to respective doc-strings of different approaches used below.

'''

from collections import OrderedDict

class ListNode():
    def __init__(self, key: int = None, value: int = None, forward = None, backward = None):
        self._key      = key
        self._value    = value
        self._forward  = forward
        self._backward = backward

class LRUCache_Using_LinkedHashMap:
    def __init__(self, capacity: int):
        # Take dummy head and tail nodes.
        self._head = ListNode(-1, -1)
        self._tail  = ListNode(-1, -1)
        self._head._forward = self._tail
        self._tail._backward = self._head

        # Key and node pair to be stored in the hashmap.
        self._lru_map = dict()
        self._count = 0
        self._capacity = capacity
        print("null ", end = "")

    def _add_node(self, node: ListNode) -> None:
        '''
        Node that is least frequently used should always be at the end.
        Node that is most  frequently used should always be at the start.

         head
        [-1, -1] ->  [1, 1]
                 <-

        node to add: node = [2, 2]
        '''
        node._forward = self._head._forward
        node._backward = self._head
        self._head._forward = node
        node._forward._backward = node

    def _remove_node(self, node: ListNode) -> int:
        node._forward._backward = node._backward
        node._backward._forward = node._forward

    def get(self, key: int) -> int:
        '''
        Time complexity = O(1), as we perform lookup operation and if the key is present, just return its value.
                                as we create doubly linked list and references to the nodes are stored in the hashmap,
                                refering to a particular node, then removing it from the list and adding it to the beginning
                                of the list all are O(1), operations.
        Space complexity = O(C), where C is the capacity of the cache.
        '''
        # if key is not present
        if key not in self._lru_map:
            print("-1 ", end = "")
            return -1

        # if key is present
        node = self._lru_map[key]
        self._remove_node(node)
        self._add_node(node)
        print(f"{node._value} ", end = "")
        return node._value

    def put(self, key: int, value: int) -> None:
        '''
        Time complexity = O(1), as we perform lookup operation and if the key is present, just return its value.
                                Explaination is same as get.
        Space complexity = O(C), where C is the capacity of the cache.
        '''
        # if key is already present in the hashmap
        if key in self._lru_map:
            node = self._lru_map[key]
            node._value = value
            self._remove_node(node)
            self._add_node(node)

        # if key is not already present in the hashmap
        else:
            if len(self._lru_map) == self._capacity:
                node_to_remove = self._tail._backward
                self._remove_node(node_to_remove)
                del self._lru_map[node_to_remove._key]

            node = ListNode(key, value)
            self._add_node(node)
            self._lru_map[key] = node

        print("null ", end = "")

class LRUCache_Using_OrderedDict:

    def __init__(self, capacity: int):
        self._lru_cache = OrderedDict()
        self._capacity = capacity
        print("null ", end = "")

    def get(self, key: int) -> int:
        '''
        Most recently used items are moved at the end of the dictionary.
        At the time of removal, when the capacity is full, item of the beginning of the dictionary is removed
        as, most recently used items are always present at the end of the dictionary.
        So, least recently used item is always at the beginning.

        Time complexity = O(1), as we perform lookup operation and if the key is present, just return its value.
        Space complexity = O(C), where C is the capacity of the cache.
        '''
        if key in self._lru_cache:
            self._lru_cache.move_to_end(key)
            print(f"{self._lru_cache[key]} ", end = "")
            return self._lru_cache[key]
        else:
            print("-1 ", end = "")
            return -1

    def put(self, key: int, value: int) -> None:
        '''
        OrderedDict provides 2 methods.
        1. move_to_end = Moves item to the end of dictionary. If used with argument last = True, item is moved at the beginning.
        2. popitem     = Pops "last" item of the dictionary. If used with last = False, pops item at the beginning of the dictionary.

        Time complexity = O(1), as we perform lookup operation and if the key is present, just return its value. If not, just add it to the dictionary.
                                all sub-operations involved such as accessing the value, or looking up the key are O(1).
        Space complexity = O(C), where C is the capacity of the cache.
        '''
        if key in self._lru_cache:
            self._lru_cache[key] = value

        else:
            if self._capacity == len(self._lru_cache):
                self._lru_cache.popitem(last = False)
            self._lru_cache[key] = value

        self._lru_cache.move_to_end(key)
        print("null ", end = "")


# Driver code
"""
Input: ["LRUCache", "put", "put", "get", "put", "get", "put", "get", "get", "get"]
       [[2], [1, 1], [2, 2], [1], [3, 3], [2], [4, 4], [1], [3], [4]]

Output: [null, null, null, 1, null, -1, null, -1, 3, 4]

"""
print()
print("################# Using Linked hash map #################")
print("Input: LRUCache(2), put(1, 1), put(2, 2), get(1), put(3, 3), get(2), put(4, 4), get(1), get(3), get(4)")
print()
print("Output: ", end = "")
solution = LRUCache_Using_LinkedHashMap(2)
solution.put(1, 1)
solution.put(2, 2)
solution.get(1)
solution.put(3, 3)
solution.get(2)
solution.put(4, 4)
solution.get(1)
solution.get(3)
solution.get(4)
print()
print()

print()
print("############### Using Ordered dictionary ################")
print("Input: LRUCache(2), put(1, 1), put(2, 2), get(1), put(3, 3), get(2), put(4, 4), get(1), get(3), get(4)")
print()
print("Output: ", end = "")
solution = LRUCache_Using_OrderedDict(2)
solution.put(1, 1)
solution.put(2, 2)
solution.get(1)
solution.put(3, 3)
solution.get(2)
solution.put(4, 4)
solution.get(1)
solution.get(3)
solution.get(4)
print()
print()
