"""
Problem: 146. LRU Cache
Leetcode: https://leetcode.com/problems/lru-cache/
Approach: Using Doubly Linked List to maintain the recency and hashmap to get element in O(1)
Time complexity : O(1) both for put and get.
Space complexity : O(capacity) since the space is used only for a hashmap and double linked list with at most capacity + 1 elements.
Does this code run on Leetcode: Yes
"""


class DLL:
    def __init__(self, key, value):
        self.key = key
        self.value = value
        self.next = None
        self.prev = None


class LRUCache:
    def __init__(self, capacity: int):
        self.hashmap = {}
        self.capacity = capacity
        self.head = DLL(0, 0)
        self.tail = DLL(0, 0)
        self.head.next = self.tail
        self.tail.prev = self.head
        self.size = 0

    def addNode(self, node):
        node.prev = self.head
        node.next = self.head.next
        self.head.next.prev = node
        self.head.next = node

    def removeNode(self, node):
        prev = node.prev
        nxt = node.next
        prev.next = nxt
        nxt.prev = prev

    def remove_from_tail(self):
        temp = self.tail.prev
        self.removeNode(temp)
        return temp

    def move_to_head(self, node):
        self.removeNode(node)
        self.addNode(node)

    def get(self, key: int) -> int:
        if key in self.hashmap:
            node = self.hashmap[key]
            self.move_to_head(node)
            return node.value
        return -1

    def put(self, key: int, value: int) -> None:
        new_node = DLL(key, value)
        if key in self.hashmap:
            fetch_node = self.hashmap[key]
            fetch_node.value = value
            self.move_to_head(fetch_node)
        else:
            if self.size == self.capacity:
                rem = self.remove_from_tail()
                self.hashmap.pop(rem.key)
                self.size -= 1
            self.addNode(new_node)
            self.hashmap[key] = new_node
            self.size += 1

# Your LRUCache object will be instantiated and called as such:
# obj = LRUCache(capacity)
# param_1 = obj.get(key)
# obj.put(key,value)