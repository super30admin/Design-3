class LRUCache:
    """
        https://leetcode.com/problems/lru-cache/
        // Time Complexity : O(1)
        // Space Complexity : O(n)
            'n' is the capacity
    """

    class Node:
        """
             Node of Doubly Linked List
        """

        def __init__(self, key, val):
            self.key = key
            self.val = val
            self.next = None
            self.prev = None

    def __init__(self, capacity: int):
        self.dic = {}
        self.capacity = capacity
        # Dummy nodes
        self.head = self.Node(-1, -1)
        self.tail = self.Node(-1, -1)
        # Initial pointers
        self.head.next = self.tail
        self.tail.prev = self.head

    def _remove_node(self, node):
        # removing given node from
        # doubly linked list
        node.prev.next = node.next
        node.next.prev = node.prev

    def _add_to_head(self, node):
        # adding given node to head of
        # doubly linked list
        node.next = self.head.next
        node.prev = self.head
        self.head.next = node
        node.next.prev = node

    def get(self, key: int) -> int:
        # key not present in map
        if key not in self.dic:
            return -1
        node = self.dic[key]
        self._remove_node(node)
        self._add_to_head(node)
        return node.val

    def put(self, key: int, value: int) -> None:

        # key already in cache
        if key in self.dic:
            node = self.dic[key]
            node.val = value
            self._remove_node(node)
            self._add_to_head(node)
        else:
            # key not present
            node = self.Node(key, value)
            # capacity is full
            # remove the least used
            if self.capacity == len(self.dic):
                lru_node = self.tail.prev
                self._remove_node(lru_node)
                self.dic.pop(lru_node.key)
            self._add_to_head(node)
            self.dic[key] = node

# Your LRUCache object will be instantiated and called as such:
# obj = LRUCache(capacity)
# param_1 = obj.get(key)
# obj.put(key,value)
