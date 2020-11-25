# as taught in class, using dictionary snd doubly linked list to solve this problem
#Time Complexity: O(1)
#Space Complexity: O(2n)
class Node:
    def __init__(self,key,val):
        self.key = key
        self.val = val
        self.next = None
        self.prev = None
class LRUCache:
    def __init__(self, capacity: int):
        self.head = Node(-1,-1)
        self.tail = Node(-1,-1)
        self.head.next = self.tail
        self.tail.prev = self.head
        self.map = dict()
        self.capacity = capacity

    def addTohead(self,node):
        node.next = self.head.next
        node.prev = self.head
        self.head.next = node
        node.next.prev = node
        
    def removeNode(self,node):
        node.prev.next = node.next
        node.next.prev = node.prev
    def get(self, key: int) -> int:
        if (key not in self.map):
            return -1
        node = self.map[key]
        self.removeNode(node)
        self.addTohead(node)
        return node.val
        
    def put(self, key: int, value: int) -> None:
        if key in self.map:
            node = self.map[key]
            node.val = value
            self.removeNode(node)
            self.addTohead(node)
        else:
            newNode = Node(key,value)
            if self.capacity == len(self.map):
                tailprev = self.tail.prev
                self.removeNode(tailprev)
                del self.map[tailprev.key]
            self.addTohead(newNode)
            self.map[key] = newNode
                
