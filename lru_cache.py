# GET AND PUT METHOD TAKES O(1) TIME AND O(1) SPACE
class Node:
    def __init__(self,key,val):
        self.key = key
        self.val = val
        self.prev = None
        self.next = None

class List:
    def __init__(self):
        self.head = Node(-1,-1)
        self.tail = Node(-1,-1)
        self.head.next = self.tail
        self.tail.prev = self.head
    
    # always add node at the beginning
    def addNode(self,node):
        node.prev = self.head
        node.next = self.head.next
        node.next.prev = node
        node.prev.next = node
    
    # removing node normally
    def removeNode(self,node):
        node.prev.next = node.next
        node.next.prev = node.prev
        node.prev = None
        node.next = None
    
    # remove Tail
    def removeTail(self):
        node = self.tail.prev
        self.removeNode(node)

class LRUCache:

    def __init__(self, capacity: int):
        self.cache = {}
        self.list = List()
        self.capacity = capacity
        

    def get(self, key: int) -> int:
        if key not in self.cache:
            return -1
        node = self.cache[key]
        self.list.removeNode(node)
        self.list.addNode(node)
        return node.val

    def put(self, key: int, value: int) -> None:
        if key in self.cache:
            node = self.cache[key]
            self.list.removeNode(node)
            node.val = value
            self.list.addNode(node)
        else:
            if len(self.cache) == self.capacity:
                tail_key = self.list.tail.prev.key
                self.list.removeTail()
                del self.cache[tail_key]
            node = Node(key,value)
            self.list.addNode(node)
            self.cache[key] = node
            
                
        


# Your LRUCache object will be instantiated and called as such:
# obj = LRUCache(capacity)
# param_1 = obj.get(key)
# obj.put(key,value)