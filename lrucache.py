class Node:
    def _init_(self, val, key):
        self.next = None
        self.prev = None
        self.val = val
        self.key = key


class LRUCache:
    maptonode = {}

    def __init__(self, capacity: int):
        head = Node(-1, -1)
        tail = Node(-1, -1)
        head.next = tail
        tail.prev = head
        self.capacity = capacity

    def removeNode(node: Node) -> None:

        node.prev.next = node.next
        node.next.prev = node.prev

    def addtoHead(node: Node) -> None:
        node.next = self.head.next
        node.prev = self.head
        self.head.next = node
        node.next.prev = node

    def get(self, key: int) -> int:
        if key not in self.maptonode.keys(): return -1

        node = self.maptonode[key]
        self.removeNode(node)
        self.addtoHead(node)
        return node.val

    def put(self, key: int, value: int) -> None:
        if key not in self.maptonode.keys:

            if self.maptonode.__len__() == self.capacity:
                nod = self.tail.prev
                self.removeNode(nod)
                del self.maptonode[nod.key]


            else:
                node = Node(value, key)
                self.capacity += 1
                self.addtoHead(node)
                self.maptonode[key] = node




        else:
            node = self.maptonode[key]
            node.val = val
            self.removeNode(node)
            self.addtoHead(node)

# space:- O(1)
# Time:- O(1)
# Your LRUCache object will be instantiated and called as such:
# obj = LRUCache(capacity)
# param_1 = obj.get(key)
# obj.put(key,value)