## Problem 2: LRU Cache(https://leetcode.com/problems/lru-cache/)

class Node:
    # A doubly linked list node stores the key, value, and pointers to the previous and next nodes.
    # Storing the key is necessary so we can delete the corresponding entry in the hash map
    # when the node is evicted from the tail of the list.
    def __init__(self, key: int, val: int):
        self.key = key
        self.val = val
        self.prev = None
        self.next = None

class LRUCache:
    def __init__(self, capacity: int):
        self.capacity = capacity
        
        # Dummy head and tail nodes avoid edge cases when adding/removing nodes 
        # (e.g., an empty list) by guaranteeing every real node is between two nodes.
        self.head = Node(-1, -1)
        self.tail = Node(-1, -1)
        self.head.next = self.tail
        self.tail.prev = self.head
        
        self.count = 0     # Keeps track of the current number of items in the cache
        self.nodeMap = {}  # Hash map for O(1) access to nodes by their key

    def removeNode(self, node: Node):
        # Extracts a node from the doubly linked list by linking its neighbors together
        node.next.prev = node.prev
        node.prev.next = node.next
        
        # Clear the node's pointers (optional but good practice)
        node.next = None
        node.prev = None
        
        self.count -= 1

    def addToHead(self, node: Node):
        # Inserts a node right after the dummy head. 
        # The head represents the "Most Recently Used" (MRU) side of the cache.
        node.next = self.head.next
        node.prev = self.head
        self.head.next = node
        node.next.prev = node
        
        self.count += 1

    def get(self, key: int) -> int:
        # Check if the key exists in our map
        node = self.nodeMap.get(key, None)
        if not node:
            return -1
        
        # If accessed, it becomes the most recently used item. 
        # We move it to the front by removing it from its current position and adding it to the head.
        self.removeNode(node)
        self.addToHead(node)
        
        return node.val

    def put(self, key: int, value: int) -> None:
        if self.nodeMap.get(key, None):
            # If the key already exists, update its value and move it to the MRU position (head)
            newNode = self.nodeMap.get(key, None)
            newNode.val = value
            self.removeNode(newNode)
            self.addToHead(newNode)
        else:
            # If it's a new key, create a new node and add it to the hash map
            newNode = Node(key, value)
            self.nodeMap[key] = newNode
            
            # Check if adding this new node will exceed the cache capacity
            if self.count == self.capacity:
                # The "Least Recently Used" (LRU) node is always right before the dummy tail.
                # Remove it from the hash map using the key stored in the node, then remove it from the list.
                del self.nodeMap[self.tail.prev.key]
                self.removeNode(self.tail.prev)
            
            # Finally, insert the new node at the MRU position (head)
            self.addToHead(newNode)