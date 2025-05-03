# 146. LRU Cache

# TC : 
    # get: O(1) - Lookup in the dictionary is O(1), and moving a node in the linked list is also O(1)
    # put: O(1) - Dictionary operations, node creation, and linked list operations are all O(1)
# SC :
    # O(capacity) - We store at most capacity key-value pairs in both the dictionary and the linked list

# Did this code successfully run on Leetcode : Yes

# Approach :
# Data Structure Choice:
    # I used a combination of a hash map (dictionary) and a doubly linked list
    # The hash map provides O(1) lookups for values
    # The doubly linked list tracks usage order and allows O(1) updates
# LRU Implementation Strategy:
    # Most recently used items are kept at the front (near the head)
    # Least recently used items are at the back (near the tail)
    # When we access or update an item, we move it to the front
    # When the cache is full, we remove the item at the back
# Key Operations:
    # get: Look up the item, if found, move it to front (most recently used position)
    # put: Add/update an item, place it at front, and remove the LRU item if over capacity
# Optimizations:
    # Using dummy head and tail nodes simplifies edge cases in the linked list operations
    # Direct access to nodes via the dictionary avoids linked list traversal

class LRUCache:
    def __init__(self, capacity: int):
        # Initialize with the given capacity
        self.capacity = capacity
        
        # Dictionary to store key-value pairs for O(1) access
        self.cache = {}
        
        # Use a doubly linked list to track usage order
        # This allows us to efficiently move nodes and remove the LRU node
        self.head = Node(0, 0)  # Dummy head
        self.tail = Node(0, 0)  # Dummy tail
        
        # Connect head and tail
        self.head.next = self.tail
        self.tail.prev = self.head
        
    def get(self, key: int) -> int:
        if key in self.cache:
            # Key exists - move the node to the most recently used position
            node = self.cache[key]
            self._remove_node(node)
            self._add_to_head(node)
            return node.value
        return -1  # Key doesn't exist
    
    def put(self, key: int, value: int) -> None:
        # Check if key already exists
        if key in self.cache:
            # Remove the existing node from its current position
            self._remove_node(self.cache[key])
        
        # Create a new node
        new_node = Node(key, value)
        
        # Add to the dictionary and to the front of the list
        self.cache[key] = new_node
        self._add_to_head(new_node)
        
        # If over capacity, remove the least recently used item (tail)
        if len(self.cache) > self.capacity:
            lru_node = self.tail.prev
            self._remove_node(lru_node)
            del self.cache[lru_node.key]
        
    # Helper methods for doubly linked list operations
    def _remove_node(self, node):
        # Remove a node from the linked list
        prev_node = node.prev
        next_node = node.next
        prev_node.next = next_node
        next_node.prev = prev_node
    
    def _add_to_head(self, node):
        # Add a node right after the dummy head
        node.prev = self.head
        node.next = self.head.next
        self.head.next.prev = node
        self.head.next = node

# Node class for doubly linked list
class Node:
    def __init__(self, key, value):
        self.key = key
        self.value = value
        self.prev = None
        self.next = None