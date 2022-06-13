# Time Complexity: get, put, add, remove - O(1)
# Space: O(1)


class LRUCache:
    
    class Node:
        def __init__(self, key, val):
            self.key = key
            self.val = val
            self.prev = None
            self.next = None
    
    def __init__(self, capacity: int):
        self.capacity = capacity
        self.head = self.Node(-1,-1)
        self.tail = self.Node(-1,-1)
        self.head.next = self.tail
        self.tail.prev = self.head
        self.map = dict()
    
    def removeNode(self,node):
        node.next.prev = node.prev
        node.prev.next = node.next
    
    def addToHead(self,node):
        node.next = self.head.next
        node.prev = self.head
        self.head.next = node
        node.next.prev = node
        
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
            if self.capacity == len(self.map):
                # Delete the LRU
                tail_prev = self.tail.prev
                self.removeNode(tail_prev)
                self.map.pop(tail_prev.key)
            new_node = self.Node(key, value)
            self.addToHead(new_node)
            self.map[key] = new_node
            


# Your LRUCache object will be instantiated and called as such:
# obj = LRUCache(capacity)
# param_1 = obj.get(key)
# obj.put(key,value)