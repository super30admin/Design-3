# Time Complexity: O(1)
# Space Complexity: O(n)
# Does this code run on Leetcode: Yes
# Does you face any problem while solving this problem: No


class Node:
    def __init__(self, key, value):
        self.key = key
        self.value = value
        self.next = None
        self.prev = None

class LRUCache:

    def __init__(self, capacity: int):
        self.map = dict()
        self.capacity = capacity
        self.head = Node(0,0)
        self.tail = Node(0,0)
        self.head.next = self.tail
        self.tail.prev = self.head
        self.count = 0

    def removeNode(self,node):
        node.prev.next = node.next
        node.next.prev = node.prev

    def addToHead(self, node):
        node.next = self.head.next
        node.prev = self.head
        self.head.next = node
        node.next.prev = node
        
    def get(self, key: int) -> int:
        if key not in self.map:
            return -1
        self.removeNode(self.map[key])
        self.addToHead(self.map[key])
        return self.map[key].value

    def put(self, key: int, value: int) -> None:
        if key in self.map:
            tempNode = self.map[key]
            tempNode.value = value
            self.removeNode(tempNode)
            self.addToHead(tempNode)
        else:
            if self.count == self.capacity:
                tail_prev = self.tail.prev
                self.removeNode(tail_prev)
                self.map.pop(tail_prev.key)
                self.count -= 1
            temp = Node(key, value)
            self.map[key] = temp
            self.addToHead(temp)
            self.count += 1
