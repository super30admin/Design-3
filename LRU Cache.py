class Node:
    def __init__(self, key = None, val=None):
        self.key = key
        self.val = val
        self.next = None
        self.prev = None

class DLL:
    def __init__(self):
        self.head = Node(-1, -1)
        self.tail = Node(-1, -1)
        self.head.next = self.tail
        self.tail.prev = self.head
        
    def addToHead(self, node):
        node.next = self.head.next
        node.prev = self.head
        self.head.next.prev = node
        self.head.next = node
        
    def removeNode(self, node):
        node.prev.next = node.next
        node.next.prev = node.prev
        
    def removeFromTail(self):
        node = self.tail.prev
        self.tail.prev = node.prev
        node.prev.next = node.next
        return node

class LRUCache:

    def __init__(self, capacity: int):
        self.capacity = capacity
        self.linked_list = DLL()
        self.link_map = {}
        
    def get(self, key: int) -> int:
        if key not in self.link_map:
            return -1
        # Fetch Node, update LinkedList to bring recently used to front
        node = self.link_map[key]
        self.linked_list.removeNode(node)
        self.linked_list.addToHead(node)
        return node.val
    
    def put(self, key: int, value: int) -> None:
        
        # If Key in Map --> Update Map, LinkedList
        if key in self.link_map:            
            node = self.link_map[key]
            node.val = value
            self.linked_list.removeNode(node)
            self.linked_list.addToHead(node)
            return
        
        # Else, create Node and add to map & LinkedList
        node = Node(key, value)
        self.link_map[key] = node
        self.linked_list.addToHead(node)
        
        # If number of nodes exceeds capacity, remove least recently used which will be found at tail. 
        if self.capacity < len(self.link_map):
            node = self.linked_list.removeFromTail()
            del self.link_map[node.key]
        
# Your LRUCache object will be instantiated and called as such:
# obj = LRUCache(capacity)
# param_1 = obj.get(key)
# obj.put(key,value)

### Complexity Analysis:
# Time Complexity O(1): All linked list operations take constant time, hence get and put both will be constant time operations
# Space Complexity O(capacity): At most, we store the number of nodes that our capacity permit in hashmap and LinkedList