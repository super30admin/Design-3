"""
Rasika Sasturkar
Time complexity: O(1), for get() and put() operations
Space complexity: O(capacity), space used by map and linkedlist.
"""


class Node:
    """
    Constructing a Node
    """

    def __init__(self, key, val):
        self.key = key
        self.val = val
        self.next = None
        self.prev = None


class LRUCache:
    """
    We use a Doubly linked list for easy insert and delete operations. Keep track of nodes
    in LRUCache using a map of the values in list and their node references.
    get() will check if the element is present in the map, if yes then delete it from that
    position and add it to the head of DLL.
    put() will insert the element in the list if the capacity is not exceeded. If it exceeds
    the capacity, the tail element is deleted and new element is added to the head of DLL.
    """

    def __init__(self, capacity: int):
        self.head = Node(-1, -1)
        self.tail = Node(-1, -1)
        self.head.next = self.tail
        self.tail.prev = self.head
        self.map = {}
        self.capacity = capacity

    def remove_node(self, node):
        node.next.prev = node.prev
        node.prev.next = node.next

    def add_to_head(self, node):
        node.next = self.head.next
        node.prev = self.head
        self.head.next.prev = node
        self.head.next = node

    def get(self, key: int) -> int:
        if key not in self.map:
            return -1
        node = self.map[key]
        self.remove_node(node)
        self.add_to_head(node)
        return node.val

    def put(self, key: int, value: int) -> None:
        # not a fresh node
        if key in self.map:
            node = self.map[key]
            node.val = value
            self.remove_node(node)
            self.add_to_head(node)
        else:
            # check capacity
            if len(self.map) == self.capacity:
                tail_prev = self.tail.prev
                self.remove_node(tail_prev)
                self.map.pop(tail_prev.key)
            new_node = Node(key, value)
            self.add_to_head(new_node)
            self.map[key] = new_node


def main():
    """
    Main function - examples from LeetCode problem to show the working.
    This code ran successfully on LeetCode and passed all test cases.
    """
    lRUCache = LRUCache(2)
    lRUCache.put(1, 1)  # cache is {1 = 1}
    lRUCache.put(2, 2)  # cache is {1 = 1, 2 = 2}
    print(lRUCache.get(1))  # return 1
    lRUCache.put(3, 3)  # LRU key was 2, evicts key 2, cache is {1 = 1, 3 = 3}
    print(lRUCache.get(2))  # returns -1 (not found)
    lRUCache.put(4, 4)  # LRU key was 1, evicts key 1, cache is {4 = 4, 3 = 3}
    print(lRUCache.get(1))  # return -1 (not found)
    print(lRUCache.get(3))  # return 3
    print(lRUCache.get(4))  # return 4


if __name__ == "__main__":
    main()
