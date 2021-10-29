# Time Complexity: get() - O(1); put() - O(1)
# Space Complexity: O(C), where C - capacity of the cache

class Node:
    def __init__(self, key: int, val: int):
        self.key = key
        self.val = val
        self.next = None
        self.next = None


class LRUCache:
    # Initialize a dictionary and head and tail of the linked list
    def __init__(self, capacity: int):
        self.head = Node(-1, -1)
        self.tail = Node(-1, -1)
        self.head.next = self.tail
        self.tail.prev = self.head
        self.cap = capacity
        self.hmap = dict()

    # Remove node from the linked list
    def remove(self, node: Node) -> None:
        node.prev.next = node.next
        node.next.prev = node.prev

    # Add node to the head of the linked list
    def add_to_head(self, node: Node) -> None:
        node.next = self.head.next
        node.prev = self.head
        self.head.next.prev = node
        self.head.next = node

    def get(self, key: int) -> int:
        # If the node is present, move the node to the head and return its value
        if key in self.hmap:
            node = self.hmap[key]
            self.remove(node)
            self.add_to_head(node)
            return node.val
        return -1

    def put(self, key: int, value: int) -> None:
        # If the node is present, move the node to the head and update its value
        if key in self.hmap:
            node = self.hmap[key]
            self.remove(node)
            self.add_to_head(node)
            node.val = value
        # If not, remove the least recently used node from the tail and add the new node
        else:
            if self.cap == len(self.hmap):
                tail_prev = self.tail.prev
                self.remove(tail_prev)
                del self.hmap[tail_prev.key]
            node = Node(key, value)
            self.add_to_head(node)
            self.hmap[key] = node
            