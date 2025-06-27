# Time Complexity : O(1) for both get and put operations
# Space Complexity : O(capacity), where capacity is the maximum size of the cache
# Did this code successfully run on Leetcode : Yes
# Any problem you faced while coding this : No

# Your code here along with comments explaining your approach
# I used a HashMap to store key-node pairs for O(1) access,
# and a Doubly Linked List to maintain the order of usage.
# The least recently used node is removed from the tail when capacity is exceeded.

class DLinkedNode:
    def __init__(self, key=0, value=0):
        self.key = key
        self.value = value
        self.prev = None
        self.next = None

class LRUCache:

    def __init__(self, capacity: int):
        self.cache = {}  # map key to node
        self.capacity = capacity
        self.size = 0

        # Dummy head and tail nodes
        self.head = DLinkedNode()
        self.tail = DLinkedNode()

        self.head.next = self.tail
        self.tail.prev = self.head

    # Add node right after head (most recently used position)
    def _add_node(self, node):
        node.prev = self.head
        node.next = self.head.next

        self.head.next.prev = node
        self.head.next = node

    # Remove an existing node from the linked list
    def _remove_node(self, node):
        prev = node.prev
        nxt = node.next

        prev.next = nxt
        nxt.prev = prev

    # Move a node to the head (most recently used)
    def _move_to_head(self, node):
        self._remove_node(node)
        self._add_node(node)

    # Remove the tail (least recently used node)
    def _pop_tail(self):
        node = self.tail.prev
        self._remove_node(node)
        return node

    def get(self, key: int) -> int:
        node = self.cache.get(key)
        if not node:
            return -1
        # move the accessed node to the head
        self._move_to_head(node)
        return node.value

    def put(self, key: int, value: int) -> None:
        node = self.cache.get(key)

        if not node:
            # create new node
            new_node = DLinkedNode(key, value)
            self.cache[key] = new_node
            self._add_node(new_node)
            self.size += 1

            if self.size > self.capacity:
                # remove LRU from list and dict
                tail = self._pop_tail()
                del self.cache[tail.key]
                self.size -= 1
        else:
            # update value and move to head
            node.value = value
            self._move_to_head(node)
