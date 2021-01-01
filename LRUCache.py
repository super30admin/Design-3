"""
Approach: Using hashmap and doubly linked list

Data structures
Why Hashmap?
To store and retrieve values in O(1) time complexity

Why Doubly Linked List?
To maintain a queue of used elements. So whenever a element is used it should be kept first in the queue. That means it must be deleted from its place and put in the very front of the queue. Using a linked list makes deletion and insertion less expensive

Put: 
If the element is present in the hashmap. If yes update the value and put it first in the linkedlist. 
If capacity is reached then remove it from the hashmap and the linkedlist.
If either of the above is not true then just add it to the hashmap and linkedlist.

Get: If the element is present in the hashmap then return that element and put it at the first place in queue.

Time complexity:
1) put: O(1)
2) get: O(1)

Space complexity: O(c); c = capacity of hashmap
"""

class Node:
    def __init__(self, key, value):
        self.next = None
        self.key = key
        self.value = value
        self.prev = None

class LRUCache:

    def __init__(self, capacity: int):
        self.head = Node(-1,-1)
        self.tail = Node(-1,-1)
        self.cap = capacity
        self.hmap = {}
        
        self.head.next = self.tail
        self.tail.next = self.head

    def remove(self, node):
        node.next.prev = node.prev
        node.prev.next = node.next
        
    def addToBeg(self, node):
        node.next = self.head.next # node -> 1st node
        self.head.next.prev = node # node <-> 1st node
        
        node.prev = self.head # head <-> node <-> 1st node
        self.head.next = node # head <-> node <-> 1st node
        
    def get(self, key: int) -> int:
        if key in self.hmap:
            node = self.hmap[key]
            self.remove(node)
            self.addToBeg(node)
            return node.value
        
        return -1
    
    def put(self, key: int, value: int) -> None:
        # If already in hashmap then delete it and put it in the beginning
        if key in self.hmap:
            node = self.hmap[key]
            node.value = value # Update value
            self.remove(node)
            self.addToBeg(node)
            return 
        
        # Remove last node and its key from hashmap
        elif self.cap == len(self.hmap):
            last = self.tail.prev
            self.remove(self.tail.prev)
            del self.hmap[last.key]
            
        # New key-value pair, then add it to hashmap and linked list
        node = Node(key, value)
        self.addToBeg(node)
        self.hmap[key] = node
            

# Your LRUCache object will be instantiated and called as such:
# obj = LRUCache(capacity)
# param_1 = obj.get(key)
# obj.put(key,value)