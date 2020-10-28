#get and put O(1) TIME AND SPACE

class Node:
    def __init__(self,key,val):
        self.key = key
        self.val = val
        self.next = None
        self.prev = None

class DoubleLinkedList:
    def __init__(self):
        self.head = Node(0,0)
        self.tail = Node(0,0)
        self.head.next = self.tail
        self.tail.prev = self.head
    
    def addAtBeginning(self,node):
        node.next = self.head.next
        node.prev = self.head
        self.head.next.prev = node
        self.head.next = node
    
    def remove(self,node):
        node.prev.next = node.next
        node.next.prev = node.prev
        node.prev = None
        node.next = None
    
    def removeLastNode(self):
        node = self.tail.prev
        self.remove(node)
        
class LRUCache:

    def __init__(self, capacity: int):
        self.cache = {}
        self.linkedlist = DoubleLinkedList()
        self.capacity = capacity
        
    def get(self, key: int) -> int:
        if key not in self.cache:
            return -1
        current_node = self.cache[key]
        self.linkedlist.remove(current_node)
        self.linkedlist.addAtBeginning(current_node)
        return current_node.val
        
    def put(self, key: int, value: int) -> None:
        if key in self.cache:
            node = self.cache[key]
            node.val = value
            self.linkedlist.remove(node)
            self.linkedlist.addAtBeginning(node)
        else:
            if self.capacity == len(self.cache):
                tail = self.linkedlist.tail.prev.key
                self.linkedlist.removeLastNode()
                del self.cache[tail]
            node = Node(key,value)
            self.linkedlist.addAtBeginning(node)
            self.cache[key] = node
            
# Your LRUCache object will be instantiated and called as such:
# obj = LRUCache(capacity)
# param_1 = obj.get(key)
# obj.put(key,value)