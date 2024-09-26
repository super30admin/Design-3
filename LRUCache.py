# Time - O(1)
# Space - O(capacity)
# Approach - Always keep the recently accessed keys at the head of the linked list

class Node:
    def __init__(self, key, val):
        self.key = key
        self.val = val
        self.prev = None
        self.next = None

class LRUCache:

    def __init__(self, capacity: int):
        # create head an dtail as dummy nodes
        # make the connections to refelct a doubly linked list

        self.head = Node(-1, -1)        
        self.tail = Node(-1, -1)        
        self.head.next = self.tail
        self.tail.prev = self.head

        self.capacity = capacity
        self.map = {}

    def get(self, key: int) -> int:
        # when we access a key, it becomes the mostly recently used, so move to head

        if key not in self.map:
            return -1

        node = self.map[key]
        self.removeNode(node)
        self.addtoHead(node) # assigining to start of LL, ie head

        return node.val

    def put(self, key: int, value: int) -> None:

        # update the value of key if it already exists and then remove first and then add to head
        if key in self.map:
            node = self.map[key]
            node.val = value
            self.removeNode(node)
            self.addtoHead(node)

        else:
            # if capacity is already met, we need to remove the least recently used which is the last node, tail
            # also remove it from the map

            if len(self.map) == self.capacity:
                tailPrev = self.tail.prev
                self.removeNode(tailPrev)
                del self.map[tailPrev.key]

            # once removed least recently, add node to head and add to map
            node = Node(key, value)
            self.addtoHead(node)
            self.map[key] = node

    def addtoHead(self, node):
        node.next = self.head.next
        node.prev = self.head
        self.head.next = node
        node.next.prev = node
        return

    def removeNode(self, node):
        node.prev.next = node.next
        node.next.prev = node.prev
        return

 