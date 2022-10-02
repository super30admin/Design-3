class Node:
    def __init__(self, key=None, value=None, next=None, prev=None):
        self.key = key
        self.value = value
        self.next = next
        self.prev = prev

class DoublyLinkedList:
    def __init__(self):
        self.head = Node()
        self.tail = Node()
        self.head.next = self.tail
        self.tail.next = self.head

    def remove_node(self, node):
        prev = node.prev
        next = node.next
        prev.next = next
        next.prev = prev
        return node
    
    def remove_from_tail(self):
        prev = self.tail.prev
        self.tail.prev = prev.prev
        prev.prev.next = self.tail
        return prev
    
    def add_to_head(self, node):
        next = self.head.next
        self.head.next = node
        node.prev = self.head
        node.next = next
        next.prev = node
        
    def move_to_head(self, node):
        node = self.remove_node(node)
        self.add_to_head(node)
        
class LRUCache:
    def __init__(self, capacity: int):
        self.capacity = capacity
        self.cache = {}
        self.dll = DoublyLinkedList()
        
    def get(self, key: int) -> int:
        if key in self.cache:
            node = self.cache[key]
            self.dll.move_to_head(node)
            return node.value
        else:
            return -1

    def put(self, key: int, value: int) -> None:
        if key in self.cache:
            node = self.cache[key]
            self.dll.move_to_head(node)
            node.value = value
        else:
            node = Node(key, value)
            if len(self.cache) == self.capacity:
                removed_node = self.dll.remove_from_tail()
                del self.cache[removed_node.key]
            self.dll.add_to_head(node)
            self.cache[key] = node
        


# Your LRUCache object will be instantiated and called as such:
# obj = LRUCache(capacity)
# param_1 = obj.get(key)
# obj.put(key,value)