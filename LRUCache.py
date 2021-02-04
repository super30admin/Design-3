# Time Complexity : O(1)
# Space Complexity : O(N)
# Did this code successfully run on Leetcode : Yes
# Any problem you faced while coding this : No


# Your code here along with comments explaining your approach
# Initialize a Node class. Add functions for add and remove Node.
# Intialize head and tail to a dummy node for a doubly linked list and HashMap for lookup
# For get operation check if the node is in HashMap else return -1
# If it is in HashMap return its value and remove the node from linkedlist and add it to head
# For put operation check if the node is in HashMap if it is then remove the node from the linked list and add it to head
# If not then check if the capacity is equal to len of HashMap then remove the tail node from linked list and HashMap
# Create a new node with the given key and value and add it to head and HashMap
class Node:
    def __init__(self, key, val):
        self.key = key
        self.val = val
        self.prev = None
        self.next = None


class LRUCache:
    def __init__(self, capacity: int):
        self.capacity = capacity
        self.dict = {}
        self.head = Node(-1, -1)
        self.tail = Node(-1, -1)
        self.head.next = self.tail
        self.tail.prev = self.head

    def addNode(self, node):
        node.next = self.head.next
        node.prev = self.head
        self.head.next = node
        node.next.prev = node

    def removeNode(self, node):
        node.prev.next = node.next
        node.next.prev = node.prev

    def get(self, key: int) -> int:
        if key in self.dict:
            node = self.dict[key]
            self.removeNode(node)
            self.addNode(node)
            return node.val
        return -1

    def put(self, key: int, value: int) -> None:
        if key in self.dict:
            node = self.dict[key]
            self.removeNode(node)
        elif self.capacity == len(self.dict):
            node = self.tail.prev
            self.removeNode(node)
            del self.dict[node.key]
        node = Node(key, value)
        self.addNode(node)
        self.dict[key] = node


# Your LRUCache object will be instantiated and called as such:
# obj = LRUCache(capacity)
# param_1 = obj.get(key)
# obj.put(key,value)