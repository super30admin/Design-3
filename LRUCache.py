#Time Complexity: O(1)
#Space Complexity: O(capacity)
#Successfully ran on leetcode

class LRUCache:
    class ListNode:
        def __init__(self,key,value):
            self.key = key
            self.value = value
            self.next = None
            self.prev = None
    def __init__(self, capacity: int):
        self.capacity = capacity
        self.map = {}
        self.head = self.ListNode(-1,-1)
        self.tail = self.ListNode(-1,-1)
        self.head.next = self.tail
        self.tail.prev = self.head
    def remove(self,node):
        node.prev.next = node.next
        node.next.prev = node.prev
    def addToHead(self,node):
        self.head.next.prev = node
        node.next = self.head.next
        node.prev = self.head
        self.head.next = node 
    def get(self, key: int) -> int:
        if key not in self.map:
            return -1
        node = self.map[key]
        self.remove(node)
        self.addToHead(node)
        return node.value
    def put(self, key: int, value: int) -> None:
        if key in self.map:
            self.map[key].value = value
            self.remove(self.map[key])
            self.addToHead(self.map[key])
            return
        if len(self.map)==self.capacity:
            tailPrev = self.tail.prev
            self.remove(tailPrev)
            del self.map[tailPrev.key]
        newNode = self.ListNode(key,value)
        self.addToHead(newNode)
        self.map[key] = newNode