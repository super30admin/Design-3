# Space Complexity: O(capcacity) 

# Time Complexity: O(1) for both get and put

class Node: 
    def __init__(self, key, val): 
        self.key = key
        self.val = val
        self.next = None
        self.prev = None

class LRUCache:
    
    def __init__(self, capacity: int):
        self.map = {}
        self.capacity = capacity
        self.head = Node(-1, -1)
        self.tail = Node(-1, -1)
        self.head.next = self.tail
        self.tail.prev = self.head

    def removeNode(self, node): 
        node.prev.next = node.next
        node.next.prev = node.prev
        node.prev = None
        node.next = None
        

    def addToHead(self, node): 
        node.prev = self.head
        node.next = self.head.next
        node.next.prev = node
        self.head.next = node


    def get(self, key: int) -> int:
        if key not in self.map: 
            return -1
        node = self.map[key]
        self.removeNode(node)
        self.addToHead(node)
        return node.val
        

    def put(self, key: int, value: int) -> None:
        if key in self.map: 
            node = self.map[key]
            node.val = value
            self.removeNode(node)
            self.addToHead(node)

        else: 
            if len(self.map) == self.capacity: 
                lastNode = self.tail.prev
                self.removeNode(lastNode)
                del self.map[lastNode.key]

            newNode = Node(key, value)
            self.addToHead(newNode)
            self.map[key] = newNode


        


# Your LRUCache object will be instantiated and called as such:
# obj = LRUCache(capacity)
# param_1 = obj.get(key)
# obj.put(key,value)
