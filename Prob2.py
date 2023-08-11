# Time complexity : O(1)
# Space complexity : O(n) to maintain the doubly linked list and the hash map

# Use a doubly linked list with two dummy nodes : Head and Tail. 
#Most recently used nods are towards the head pointer and the least recently used ones are towards the Tail pointer. 
#Keep adding elements at the head pointer until capacity is full. When capacity is full, remove node that is closer to the tail pointer. 
#Also maintain a hash map that maps keys to the pointer sin linked list

class Node:
    def __init__(self, key, val):
        self.key = key
        self.val = val
        self.next = None
        self.prev = None

class LRUCache:
    def __init__(self, capacity: int):
        self.map = {}  # Use this dictionary to store key-node mappings
        self.capacity = capacity
        self.head = Node(-1, -1) #make head and tail to handle the moving of the ndoes
        self.tail = Node(-1, -1)
        self.head.next = self.tail
        self.tail.prev = self.head
    
    def addhead(self, node): #add to the MR(front of LL)
        node.next = self.head.next
        node.prev = self.head
        self.head.next = node
        node.next.prev = node
    
    def removeNode(self, node): #remove the nodes
        node.prev.next = node.next
        node.next.prev = node.prev

    def get(self, key: int) -> int:
        if key not in self.map:
            return -1
        node = self.map[key] #get value, then remove current node and move it to the front
        self.removeNode(node)
        self.addhead(node)
        return node.val

    def put(self, key: int, value: int) -> None:
        if key in self.map:             #if found, get node reference and update it
            node = self.map[key]
            node.val = value
            self.removeNode(node) #then remove current node and move it to the front
            self.addhead(node)
        else:
            # Fresh node
            if len(self.map) == self.capacity: #if new node, check capacity, if max, delete LR from LL and hashmap
                tailprev = self.tail.prev
                self.removeNode(tailprev)
                del self.map[tailprev.key]
            new_node = Node(key, value) #else add to the front (MR) and the hashmap
            self.addhead(new_node)
            self.map[key] = new_node

# Your LRUCache object will be instantiated and called as such:
# obj = LRUCache(capacity)
# param_1 = obj.get(key)
# obj.put(key, value)
