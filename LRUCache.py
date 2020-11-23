#Time Complexity : O(1)
#Space Complexity : O(capacity)
#Did this code successfully run on Leetcode : Yes
#Three line explanation of solution in plain english: Using a doubly linked list and a dictionary we create a LRU Cache. The dictionary helps in being able to check whether the key exists in the LRUCache in 0(1) time. And the doubly linked list is to create LRU Cache, which basically keeps the LRU element at the head and the last used at the end of the list. So on adding new elements, we add at the end of the list. If we have to add an element with an already existing key, then we delete the original node and add the new key, value pair node at the end of the list. And finally when we need to get a certain key, value pair, we check if the key exists in the dictionary and if it does, since we used it the latest, we delete it from it's position and add it to the end of the linked list. This way are always able to maintain a LRU pattern in our data structure.

class ListNode:
    def __init__(self, key, val):
        self.key = key
        self.val = val
        self.prev = None
        self.next = None

class LRUCache:
    def __init__(self, capacity: int):
        self.dict = {}
        self.capacity = capacity
        self.head = ListNode(-1, -1)
        self.tail = ListNode(-1, -1)
        self.head.next = self.tail
        self.tail.prev = self.head

    def add(self, node):
        pre = self.tail.prev
        pre.next = node
        node.prev = pre
        node.next = self.tail
        self.tail.prev = node

    def delete(self, node):
        nex = node.next
        pre = node.prev
        pre.next = nex
        nex.prev = pre

    def get(self, key: int) -> int:
        if key not in self.dict:
            return -1
        else:
            curr = self.dict[key]
            self.delete(curr)
            self.add(curr)
            return curr.val

    def put(self, key: int, value: int) -> None:
        if key in self.dict:
            self.delete(self.dict[key])

        new_node = ListNode(key, value)
        self.add(new_node)
        self.dict[key] = new_node

        if len(self.dict) > self.capacity:
            curr = self.head.next
            self.delete(curr)
            del self.dict[curr.key]


# Your LRUCache object will be instantiated and called as such:
# obj = LRUCache(capacity)
# param_1 = obj.get(key)
# obj.put(key,value)
