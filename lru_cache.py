class Node:
    def __init__(self, key, value):
        self.key = key
        self.val = value
        self.next = None
        self.prev = None
        
class LRUCache:

    def __init__(self, capacity: int):
        self.capacity = capacity
        self.map = {}
        self.head = Node(-1,-1)
        self.tail = Node(-1,-1)
        self.head.next = self.tail
        self.tail.prev = self.head

    def remove(self, Node):
        Node.next.prev = Node.prev
        Node.prev.next = Node.next
        
    def addtohead(self, Node):
        Node.next = self.head.next
        Node.prev = self.head
        self.head.next = Node
        Node.next.prev = Node
        
    def get(self, key: int) -> int:
        if key not in self.map:
            return -1
        node = self.map[key]
        self.remove(node)
        self.addtohead(node)
        return node.val

    def put(self, key: int, value: int) -> None:
        if key in self.map:
            node = self.map[key]
            node.val = value
            self.remove(node)
            self.addtohead(node)
        else:
            if self.capacity == len(self.map):
                node = self.tail.prev
                self.remove(node)
                del self.map[node.key]
            new_node = Node(key, value)
            self.map[key] = new_node
            self.addtohead(new_node)


#time complexity - O(1) for get and put operations

#space complexity - O(n), n = capacity

#all test cases passed


# Your LRUCache object will be instantiated and called as such:
# obj = LRUCache(capacity)
# param_1 = obj.get(key)
# obj.put(key,value)