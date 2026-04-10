# Space Complexity: O(capacity) for the hashmap and the doubly linked list
# Time Complexity: O(1) for both get and put operations

# Question=> Asks us to implement an LRU (Least Recently Used) Cache

# At first we tried doing it with a singly linked list but it was not efficient for removing the node from the middle of the list. 
# So we switched to a doubly linked list which allows us to remove the node from the middle in O(1) time. 
# We also maintain a hashmap to store the key and the corresponding node in the doubly linked list for O(1) access. 
# The head of the doubly linked list will always point to the most recently used node and the tail will always point to the least recently used node. 
# Whenever we access a node, we move it to the head of the list and whenever we need to remove a node, we remove it from the tail of the list.

# Now to implement above logic we create a class for doubly linked list and 2 helper functions to add a node to the head of the list and to remove a node from the list.
# In the get function, we check if the key is present in the hashmap, if it is present then we move the corresponding node to the head of the list and return its value. If it is not present then we return -1.
# In the put function, we check if the key is present in the hashmap, if it is present then we update the value of the corresponding node and move it to the head of the list. 
#   If it is not present then we 
#       Check if the cache is full, if it is full then we remove the least recently used node from the tail of the list and the hashmap.
#       Then we create a new node and add it to the head of the list.

class LRUCache:
    # Class for doubly Linked List
    class Node:
        def __init__(self, key, value):
            self.key, self.val = key, value
            self.next = None
            self.prev = None

    # This will be for the Most recently used
    def addToHead(self,node):
        node.prev = self.head
        node.next = self.head.next
        self.head.next = node
        node.next.prev = node
    
    # To remove the node from the doubly linked list
    def removeNode(self,node):
        node.prev.next = node.next
        node.next.prev = node.prev

    def __init__(self, capacity: int):
        self.hMap = {}
        self.head = self.Node(0,0)
        self.tail = self.Node(0,0)
        self.head.next = self.tail
        self.tail.prev = self.head
        self.capacity = capacity

    def get(self, key: int) -> int:
        # Time: O(1)
        # Space: O(1)
        if key in self.hMap:
            node = self.hMap[key]
            self.removeNode(node)
            self.addToHead(node)
            return node.val
        else: return -1

    def put(self, key: int, value: int) -> None:
        # Time: O(1)
        # Space: O(1)
        if key in self.hMap:
            # This is not fresh node i.e the key already exists
            node = self.hMap[key]
            node.val = value
            self.removeNode(node)
            self.addToHead(node)
        else:
            # This is a fresh node
            if len(self.hMap) == self.capacity:
                lru_node = self.tail.prev
                self.removeNode(lru_node)
                del self.hMap[lru_node.key]
            newNode = self.Node(key,value)
            self.hMap[key] = newNode
            self.addToHead(newNode)

# Your LRUCache object will be instantiated and called as such:
# obj = LRUCache(capacity)
# param_1 = obj.get(key)
# obj.put(key,value)


# mailto:ghendrickson@communictyfoodbank.com