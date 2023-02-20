#Time Complexity :- O(1) for both the operations
#Space Complexity :- O(n) for storing the linkedlist as well as the hashMap

class Node:
    def __init__(self, data, key):
        self.data = data 
        self.next = None
        self.prev = None
        self.key = key

class LRUCache:

    def addNodeToHead(self, node):
        node.next = self.head.next
        node.prev = self.head
        node.next.prev = node
        self.head.next = node
    
    def removeNode(self, node):
        node.next.prev = node.prev
        node.prev.next = node.next

    def __init__(self, capacity: int):
        self.map = {}
        self.capacity = capacity
        self.head = Node(-1,-1)
        self.tail = Node(-1,-1)
        self.head.next = self.tail
        self.tail.prev = self.head
        

    def get(self, key: int) -> int:
        if key not in self.map:
            return -1
        node = self.map.get(key)
        self.removeNode(node)
        self.addNodeToHead(node)
        return node.data
        

    def put(self, key: int, value: int) -> None:
        print(key, value)
        if key in self.map:
            node = self.map.get(key)
            self.removeNode(node)
            self.addNodeToHead(node)
            node.data = value
            return
        if self.capacity == len(self.map):
            tailNodePrev = self.tail.prev
            self.removeNode(tailNodePrev)
            del self.map[tailNodePrev.key]
        newNode = Node(value,key)
        self.map[key] = newNode
        self.addNodeToHead(newNode)
            





        


# Your LRUCache object will be instantiated and called as such:
# obj = LRUCache(capacity)
# param_1 = obj.get(key)
# obj.put(key,value)