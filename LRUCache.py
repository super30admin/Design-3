
class Node:
    def __init__(self, key, value):
        self.key = key
        self.value = value
        self.prev = None
        self.next = None
class LRUCache:

    def __init__(self, capacity: int):
        self.map = dict()
        self.capacity = capacity
        self.head = Node(0,0)
        self.tail = Node(0,0)
        self.head.next = self.tail
        self.tail.prev = self.head
        self.count = 0
    # Time Complexity: O(1)
    # Space Complexity: O(n)
    def get(self, key: int) -> int:
        if key not in self.map:
            return -1
        self.removeNode(self.map[key])
        self.AddToHead(self.map[key])
        return self.map[key].value

    def removeNode(self, node):
        node.prev.next = node.next
        node.next.prev = node.prev

    def AddToHead(self, node):
        node.next = self.head.next
        node.prev = self.head
        self.head.next = node
        node.next.prev = node
        
    # Time Complexity: O(1)
    # Space Complexity: O(n)    
    def put(self, key: int, value: int) -> None:
        if key in self.map:
            tempNode = self.map[key]
            tempNode.value = value
            self.removeNode(tempNode)
            self.AddToHead(tempNode)
        else:
            if self.count == self.capacity:
                tailprev = self.tail.prev
                self.removeNode(tailprev)
                self.map.pop(tailprev.key)
                self.count -= 1
            temp = Node(key, value)
            self.map[key] = temp
            self.AddToHead(temp)
            self.count += 1
        


# Your LRUCache object will be instantiated and called as such:
# obj = LRUCache(capacity)
# param_1 = obj.get(key)
# obj.put(key,value)