'''
    Time complexity: O(1)
    Space Complexity: O(1)
'''
class Node:
    def __init__(self, key, val, prev=None, next=None):
        self.key = key
        self.val = val
        self.prev = prev
        self.next = next

class LRUCache:
    def __init__(self, capacity: int):
        self.capacity = capacity
        self.hashmap = {}
        self.head = Node(-1, -1)
        self.tail = Node(-1, -1)
        self.head.next = self.tail
        self.tail.prev = self.head
        self.size = 0

    def get(self, key: int) -> int:
        if key not in self.hashmap:
            return -1
        else:
            node = self.hashmap[key]
            
            self.removeNode(node)
            self.addToTail(node)

            return node.val

    def put(self, key: int, value: int) -> None:
        if key not in self.hashmap:
            node = Node(key, value)
            self.hashmap[key] = node

            if self.size == self.capacity:
                lru = self.head.next
                self.removeNode(lru)
                del self.hashmap[lru.key]
            else:
                self.size+=1
        else:
            node = self.hashmap[key]
            node.val = value

            self.removeNode(node)

        self.addToTail(node)

    def removeNode(self, node):
        node.prev.next = node.next
        node.next.prev = node.prev

    def addToTail(self, node):
        node.next = self.tail
        node.prev = self.tail.prev
        node.prev.next = node
        self.tail.prev = node


        


# Your LRUCache object will be instantiated and called as such:
# obj = LRUCache(capacity)
# param_1 = obj.get(key)
# obj.put(key,value)