"""
Approach: use a hashmap and doubly linkedlist to maintain the LRU functionality
1) have a head and tail node to easily add and remove nodes
2) the list is maintained such that the most recently used node is closer to the head and the LRU is closer to tail
3) hashmap helps get the value of required key in constant time. linked list helps maintain the LRU order in constant time

TC: O(1) for both get and put
SC: O(n) where n is capacity

"""


class Node:
    def __init__(self, key, val):
        self.key = key
        self.val = val
        self.prev = None
        self.next = None


class LRUCache:

    def __init__(self, capacity: int):
        self.capacity = capacity
        self.dict_ = {}
        self.head = Node(0, 0)
        self.tail = Node(0, 0)
        self.head.next = self.tail
        self.tail.prev = self.head

    def get(self, key: int) -> int:
        if key in self.dict_:
            node = self.dict_[key]
            self._remove(node)
            self._add(node)
            result = node.val
        else:
            result = -1
        return result

    def put(self, key: int, value: int) -> None:
        dict_ = self.dict_

        if key in dict_:
            self._remove(dict_[key])
        node = Node(key, value)
        self._add(node)
        self.dict_[key] = node
        if len(dict_) > self.capacity:
            remove_node = self.head.next
            del self.dict_[remove_node.key]
            self._remove(remove_node)

    def _remove(self, node):
        prev_node = node.prev
        next_node = node.next
        prev_node.next = next_node
        next_node.prev = prev_node

    def _add(self, node):
        prev_node = self.tail.prev
        prev_node.next = node
        node.prev = prev_node
        node.next = self.tail
        self.tail.prev = node