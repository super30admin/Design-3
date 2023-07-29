class LRUCache:
    # Time Complexity: O(1)
    # Space Complexity: O(capacity)
    class Node:                      # we'll be storing {int:node} into the dictionary
        def __init__(self, key, val): # Doubly linnked list
            self.key = key
            self.val = val
            self.prev = None
            self.next = None

    def removeNode(self, curr):
        # Remove a node from the linked list
        curr.next.prev = curr.prev
        curr.prev.next = curr.next

    def addToHead(self, curr):   # Head side represents most frequently used and the tail side represents lease frequently used.
        # Add a node to the head of the linked list
        curr.next = self.head.next
        curr.prev = self.head
        self.head.next = curr
        curr.next.prev = curr

    def __init__(self, capacity: int):
        # Initialize the LRUCache
        self.capacity = capacity
        self.map = {}                   # For storing the <key:node> pairs.
        self.head = self.Node(-1, -1)   # Creating dummty nodes head and tail with key -1 and value -1
        self.tail = self.Node(-1, -1)
        self.head.next = self.tail      # Connecting dummy nodes (head and tail)
        self.tail.prev = self.head

    # Time Complexity: O(1)
    # Space Complexity: O(1)
    def get(self, key: int) -> int: # We'll find the node in the map and return its value (node.val), But we also have to put it in recently used
        if key not in self.map:
            return -1
        node = self.map[key]
        self.removeNode(node) # Removing the node and adding it in front as it is recently used
        self.addToHead(node)
        return node.val

    # Time Complexity: O(1)
    # Space Complexity: O(1)
    def put(self, key: int, value: int) -> None:
        if key in self.map:             # If there is already a node with the key, just update the value.
            node = self.map[key]
            node.val = value
            self.removeNode(node)      # As it is used recently, put it in front.
            self.addToHead(node)       
        else:
            if len(self.map) == self.capacity:  # We'll check the capacity first
                to_remove = self.tail.prev      # We'll remove the node from the map and from the last of linked list as well.
                self.removeNode(to_remove)
                del self.map[to_remove.key]
            new_node = self.Node(key, value)    # Else create a new node and put it inside the map and LL as well.
            self.addToHead(new_node)
            self.map[key] = new_node
