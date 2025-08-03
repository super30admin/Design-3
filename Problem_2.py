'''
146 LRU Cache
https://leetcode.com/problems/lru-cache/description/

Design a data structure that follows the constraints of a Least Recently Used (LRU) cache.

Implement the LRUCache class:
LRUCache(int capacity) Initialize the LRU cache with positive size capacity.

int get(int key) Return the value of the key if the key exists, otherwise return -1.

void put(int key, int value) Update the value of the key if the key exists. Otherwise, add the key-value pair to the cache. If the number of keys exceeds the capacity from this operation, evict the least recently used key.

The functions get and put must each run in O(1) average time complexity.

Solution:
1. Use a hash map <key, Node(key,val)> to fetch the value in O(1) time. In addition, we also use a doubly linked list to track the usage order, moving items to the head when used and deleting items (least recent usage) from the tail when size exceeds capacity of the LRU cache.

Solution uses print messages for debugging. Comment these out when testing on LeetCode. Solution was accepted in LeetCode.
https://www.youtube.com/watch?v=5eLhy5pxLEA

Time: O(1), Space: O(1)

'''
from collections import defaultdict
class Node:
    def __init__(self, key=-1, val=-1, next=None, prev=None):
        self.key = key
        self.val = val
        self.next = next
        self.prev = prev

    def print(self, dir='f'):
        node = self
        if dir == 'f':
            forward = ""
            while node:
                forward += f"{node.key,node.val} -> "
                node = node.next
            forward += "None"
            print(forward)
        elif dir == 'r':
            backward = "None"
            while node:
                backward = f"{node.key,node.val}" + f" <- {backward}"
                node = node.prev
            backward += ""
            print(backward)

    def __str__(self, dir='f'):
        node = self
        if dir == 'f':
            result = ""
            while node:
                result += f"{node.key,node.val} -> "
                node = node.next
            result += "None"
            return result

class LRUCache:
    def __init__(self, capacity: int):
        ''' Time: O(1), Space: O(1) '''
        self.map = defaultdict(int)
        self.capacity = capacity
        self.len = len(self.map)
        self.head = Node(-1,-1)
        self.tail = Node(-1000,-1000)
        self.head.next = self.tail
        self.tail.prev = self.head

    def get(self, key: int) -> int:
        ''' Time: O(1), Space: O(1) '''
        print(f"\nget({key})")
        if key in self.map:
            node = self.map[key]
            self.removeNode(node)
            self.addNodeToHead(node)
            val = node.val
        else:
            val = -1
        self.head.print('f')
        self.tail.print('r')
        return val

    def put(self, key: int, value: int) -> None:
        ''' Time: O(1), Space: O(1) '''
        print(f"\nput({key, value})")
        if key in self.map:
            # just update the value of the node
            node = self.map[key]
            self.setNodeKV(node, key, value) # update
            self.removeNode(node)
            self.addNodeToHead(node)
        else:
            if self.len == self.capacity:
               last_node = self.tail.prev
               self.removeNode(last_node)
               del self.map[last_node.key]
               new = Node(key, value)
               self.map[key] = new
               self.addNodeToHead(new)
            else: # len(self.map) < self.capacity
                new = Node(key, value)
                self.map[key] = new
                self.addNodeToHead(new)
                self.len += 1
        self.head.print('f')
        self.tail.print('r')

    def setNodeKV(self, node: Node, key: int, value:int) -> None:
        ''' Time: O(1), Space: O(1) '''
        node.key = key
        node.val = value

    def addNodeToHead(self, curr:Node) -> None:
        ''' Time: O(1), Space: O(1) '''
        front = self.head.next
        self.head.next = curr
        curr.next = front
        curr.prev = self.head
        front.prev = curr

    def removeNode(self, curr:Node) -> None:
        ''' Time: O(1), Space: O(1) '''
        back = curr.prev
        front = curr.next
        back.next = front
        front.prev = back
        curr.next = None
        curr.prev = None

def run_LRUCache():
    # Your LRUCache object will be instantiated and called as such:
    # obj = LRUCache(capacity)
    # param_1 = obj.get(key)
    # obj.put(key,value)

    lRUCache = LRUCache(2)
    lRUCache.put(1, 1) # cache is {1=1}
    lRUCache.put(2, 2) # cache is {1=1, 2=2}
    print(lRUCache.get(1))    # return 1
    lRUCache.put(3, 3); # LRU key was 2, evicts key 2, cache is {1=1, 3=3}
    print(lRUCache.get(2))    # returns -1 (not found)
    lRUCache.put(4, 4); # LRU key was 1, evicts key 1, cache is {4=4, 3=3}
    print(lRUCache.get(1))    # return -1 (not found)
    print(lRUCache.get(3))    # return 3
    print(lRUCache.get(4))    # return 4

run_LRUCache()
