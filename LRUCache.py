# Time Complexity : O(1)
# Space Complexity : O(n)

class Node:
    def __init__(self, key, val):
        self.key = key
        self.val = val
        self.prev = None
        self.next = None


class LRUCache:
    def __init__(self, capacity: int):
        self.capacity = capacity
        self.hashmap = {}
        self.head = Node(-1, -1)
        self.tail = Node(-1, -1)
        self.head.next = self.tail
        self.tail.prev = self.head

    def get(self, key: int) -> int:
        if key not in self.hashmap:
            return -1
        node = self.hashmap[key]
        self.removeNode(node)
        self.addtohead(node)
        return node.val

    def put(self, key: int, value: int) -> None:
        # if node is already in the caache
        if key in self.hashmap:
            node = self.hashmap.get(key)
            node.val = value
            self.removeNode(node)
            self.addtohead(node)
        else:
            if self.capacity == len(self.hashmap):
                # remove LRU node
                tail_prev = self.tail.prev
                self.removeNode(tail_prev)
                del self.hashmap[tail_prev.key]
            new_node = Node(key, value)
            self.addtohead(new_node)
            self.hashmap[key] = new_node

    def addtohead(self, node):
        node.next = self.head.next
        node.prev = self.head
        self.head.next = node
        node.next.prev = node

    def removeNode(self, node):
        node.next.prev = node.prev
        node.prev.next = node.next

# Your LRUCache object will be instantiated and called as such:
# obj = LRUCache(capacity)
# param_1 = obj.get(key)
# obj.put(key,value)