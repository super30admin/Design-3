class ListNode:
    def __init__(self, key, value):
        self.key = key
        self.value = value
        self.prev = None
        self.next = None


class LRUCache:
    def __init__(self, capacity: int):
        self.LRU_dict = dict()
        self.capacity = capacity
        self.head = ListNode(0, 0)
        self.tail = ListNode(-1, -1)
        self.head.next = self.tail
        self.tail.prev = self.head

    def get(self, key: int) -> int:
        if key in self.LRU_dict:
            node = self.LRU_dict[key]
            self.remove_node(node)
            self.add_head(node)
            return node.value
        else:
            return -1

    def remove_node(self, node):
        node.prev.next = node.next
        node.next.prev = node.prev

    def put(self, key: int, value: int) -> None:
        if key in self.LRU_dict:
            node = self.LRU_dict[key]
            self.remove_node(node)
            self.add_head(node)
            node.value = value
        else:
            if len(self.LRU_dict) == self.capacity:
                self.remove_tail()
            node = ListNode(key, value)
            self.LRU_dict[key] = node
            self.add_head(node)

    def add_head(self, node):
        head_next = self.head.next
        self.head.next = node
        node.prev = self.head
        node.next = head_next
        head_next.prev = node

    def remove_tail(self):
        if len(self.LRU_dict) == 0:
            return
        tail_node = self.tail.prev
        del self.LRU_dict[tail_node.key]
        self.remove_node(tail_node)


# Your LRUCache object will be instantiated and called as such:
# obj = LRUCache(capacity)
# param_1 = obj.get(key)
# obj.put(key,value)
