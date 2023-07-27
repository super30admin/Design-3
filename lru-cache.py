class DoublyList:
    def __init__(self, key = 0, value = 0):
        self.key = key
        self.next = None
        self.prev = None
        self.val = value

class LRUCache:

    def __init__(self, capacity: int):
        self.head = DoublyList(-1, -1)
        self.tail = DoublyList(-1, -1)
        self.head.next = self.tail
        self.tail.prev = self.head
        self.capacity = capacity
        self.hp = {}

        
    def get(self, key: int) -> int:
        if key not in self.hp:
            return -1
        
        node = self.hp[key]
        self.remove(node)
        self.add(node)
        return node.val
        

    def put(self, k: int, v: int) -> None:
        if k in self.hp:
            old_node = self.hp[k]
            self.remove(old_node)
        
        node = DoublyList(k, v)
        self.add(node)
        self.hp[k] = node

        if len(self.hp) > self.capacity:
            node_to_delete = self.tail.prev
            self.remove(node_to_delete)
            del self.hp[node_to_delete.key]      


    def add(self, node):
        node.prev = self.head
        node.next = self.head.next
        self.head.next = node
        node.next.prev = node
    

    def remove(self, node):
        node.prev.next = node.next
        node.next.prev = node.prev

                

# Your LRUCache object will be instantiated and called as such:
# obj = LRUCache(capacity)
# param_1 = obj.get(key)
# obj.put(key,value)