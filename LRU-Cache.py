class DLLNode:

    def __init__(self, value, key):
        self.prev = None
        self.next = None
        self.value = value
        self.key = key


class LRUCache:
    def __init__(self, capacity: int):
        self.head = DLLNode(None, None)
        self.tail = DLLNode(None, None)
        self.head.next = self.tail
        self.tail.prev = self.head
        self.capacity = capacity
        self.dict = {}

    def insert_after_head(self, node):
        prev_node = node.prev
        next_node = node.next
        if prev_node is not None:
            prev_node.next = next_node
        if next_node is not None:
            next_node.prev = prev_node
        temp = self.head.next
        self.head.next = node
        node.prev = self.head
        temp.prev = node
        node.next = temp
        return

    def get(self, key: int) -> int:
        if key in self.dict:
            node = self.dict[key]
            self.insert_after_head(node)
            return self.dict[key].value
        return -1

    def put(self, key: int, value: int) -> None:
        if key in self.dict:
            node = self.dict[key]
            node.value = value
            self.insert_after_head(node)
            return
        if len(self.dict) == self.capacity:
            evicted_node = self.tail.prev
            prev_node = evicted_node.prev
            prev_node.next = self.tail
            self.tail.prev = prev_node
            del self.dict[evicted_node.key]

        new_node = DLLNode(value, key)
        self.dict[key] = new_node
        self.insert_after_head(new_node)


# Your LRUCache object will be instantiated and called as such:
# obj = LRUCache(capacity)
# param_1 = obj.get(key)
# obj.put(key,value)

'''
Time Complexity:
get: O(1)
put: O(1)
'''