# TC = O(1) for put and get
# SC = o(n) where n capacity

class Node:
    def __init__(self, key, val):
        self.key = key
        self.val = val

        self.prev = None
        self.next = None


class Dll:
    def __init__(self):
        self.size = 0
        self.head = Node(-1, -1)
        self.tail = Node(-1, -1)

        self.head.next = self.tail
        self.tail.prev = self.head

    def insertToHead(self, node):
        node.next = self.head.next
        self.head.next = node
        node.next.prev = node
        node.prev = self.head

        self.size += 1

    def removeNode(self, node):
        node.prev.next = node.next
        node.next.prev = node.prev

        self.size -= 1

    def removeLast(self):
        node = self.tail.prev
        self.removeNode(node)
        return node


class LRUCache:

    def __init__(self, capacity: int):
        self.dll = Dll()
        self.capacity = capacity
        self.hashmap = {}

    def get(self, key: int) -> int:
        if key not in self.hashmap:
            return -1

        node = self.hashmap[key]
        self.dll.removeNode(node)
        self.dll.insertToHead(node)

        return node.val

    def put(self, key: int, value: int) -> None:

        # if node is in the hashmap
        if key in self.hashmap:
            node = self.hashmap[key]
            node.val = value
            self.dll.removeNode(node)
            self.dll.insertToHead(node)

        else:
            # node is not in the hashmap and capacity is not full
            if self.capacity > self.dll.size:
                node = Node(key, value)
                self.hashmap[key] = node
                self.dll.insertToHead(node)

            else:
                # node not in the hashmap and capacity is full
                node = self.dll.removeLast()
                del self.hashmap[node.key]
                node = Node(key, value)
                self.hashmap[key] = node
                self.dll.insertToHead(node)

# Your LRUCache object will be instantiated and called as such:
# obj = LRUCache(capacity)
# param_1 = obj.get(key)
# obj.put(key,value)