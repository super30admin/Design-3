# TC: O(1) all operations
# SC: O(n) Hashmap and doubly linkedlist

# Stepwise intution
# 1: We can store elements in a Queue/linkedlist- get element- O(n)
# 2: Maintain hashmap of key and value - get - O(1) but direct access to a element is still O(n)
# 3: Store entire reference of node in hashmap- access(for other operations) - O(1)
# 4: To remove, we need prev element in the list. Hence we can maintain nodes of (key, val) in doubly linked list. remove, put - O(1) now

class Node: 
    def __init__(self, key, val):
        self.key = key
        self.val = val
        self.prev = None
        self.next = None
        
class LRUCache:
    # any new node or recently accessed node is sent to next of head
    def addToHead(self, node):
        node.prev = self.head
        node.next = self.head.next
        self.head.next = node
        node.next.prev = node
    
    def removeNode(self, node):
        node.next.prev = node.prev
        node.prev.next = node.next

    # Hashmap + Doublely linked list(head <=> tail)    
    def __init__(self, capacity: int):
        self.capacity = capacity
        self.hashmap = {}
        self.head, self.tail = Node(-1,-1), Node(-1,-1)
        self.head.next = self.tail
        self.tail.prev = self.head
      
    def get(self, key: int) -> int:
        if key not in self.hashmap: return -1
        node = self.hashmap[key]
        self.removeNode(node)
        self.addToHead(node)
        return node.val

    def put(self, key: int, value: int) -> None:
        if key in self.hashmap:
            node = self.hashmap[key]
            node.val = value
            self.removeNode(node)
            self.addToHead(node) 
        else: 
            if len(self.hashmap) == self.capacity: 
                tailPrev = self.tail.prev
                self.removeNode(tailPrev)
                del self.hashmap[tailPrev.key]
            newNode = Node(key, value)
            self.addToHead(newNode)
            self.hashmap[key] = newNode