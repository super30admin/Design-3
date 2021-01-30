'''
    Time Complexity:
        O(n)

    Space Complexity:
        O(1)

    Did this code successfully run on LeetCode?:
        Yes

    Problems faced while coding this:
        None

    Approach:
        DoublyLinkedList + HashMap
'''

class LRUCache:
    def __init__(self, capacity: int):
        self.capacity = capacity
        self.lookup = {}
        self.dl = DoublyLinkedList()

    def get(self, key: int) -> int:
        if key not in self.lookup:
            return -1

        node = self.lookup[key]
        self.dl.move_to_front(node)
        return node.val

    def put(self, key: int, value: int) -> None:
        node = self.lookup.get(key)
        if node:
            node.val = value
            self.dl.move_to_front(node)
        else:
            node = self.dl.add(key, value)
            self.lookup[key] = node

        if len(self.lookup) > self.capacity:
            last_node = self.dl.remove_tail()
            del self.lookup[last_node.key]


class Node:
    def __init__(self, key, val, prev=None, next=None):
        self.key = key
        self.val = val
        self.prev = prev
        self.next = next


class DoublyLinkedList:
    def __init__(self):
        self.head = Node(None, None)
        self.tail = Node(None, None)
        self.head.next = self.tail
        self.tail.prev = self.head

    def add(self, key, val):
        node = Node(key, val)
        self._add_to_front(node)
        return node

    def remove_tail(self):
        node = self.tail.prev
        self._remove(node)
        return node

    def _remove(self, node):
        node.prev.next = node.next
        node.next.prev = node.prev

    def move_to_front(self, node):
        self._remove(node)
        self._add_to_front(node)

    def _add_to_front(self, node):
        first = self.head.next
        self.head.next = node
        node.prev = self.head
        node.next = first
        first.prev = node


# Your LRUCache object will be instantiated and called as such:
# obj = LRUCache(capacity)
# param_1 = obj.get(key)
# obj.put(key,value)
