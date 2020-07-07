'''
Time Complexity: Explained Below
Space Complexity: Explained Below
Did this code successfully run on Leetcode : Yes
Explanation: Create a doubly linked list to move around the cache and a hashmap for fast lookup. For get we remove the
element and move it to the front of the list as per LRU and for put we add the value to the list at the front if it
does not exist else we update the value and move it to the front of the cache.
'''
class Node:
    def __init__(self, key, value):
        self.key = key
        self.val = value
        self.next = None
        self.prev = None


class LRUCache:

    def __init__(self, capacity: int):
        self.head = Node(0, 0)
        self.tail = Node(0, 0)
        self.map = {}
        self.capacity = capacity

        # connect head and tail

        self.head.next = self.tail
        self.tail.prev = self.head

    def get(self, key: int) -> int:
        node = self.map.get(key)

        if node != None:
            self.remove(node)
            self.insertFirst(node)
            return node.val

        return -1

    def put(self, key: int, value: int) -> None:
        node = self.map.get(key)

        if node != None:
            node.val = value
            self.remove(node)
            self.insertFirst(node)
        else:
            node = Node(key, value)
            self.insertFirst(node)
            self.map[key] = node

            if len(self.map) > self.capacity:
                cursor = self.tail.prev
                self.remove(cursor)
                del self.map[cursor.key]

    def insertFirst(self, cursor) -> None:
        firstNode = self.head.next

        self.head.next = cursor
        cursor.prev = self.head

        cursor.next = firstNode
        firstNode.prev = cursor

    '''
    1-->2-->3

    '''

    def remove(self, cursor) -> None:
        prevNode = cursor.prev
        nextNode = cursor.next
        prevNode.next = nextNode
        nextNode.prev = prevNode

# Your LRUCache object will be instantiated and called as such:
# obj = LRUCache(capacity)
# param_1 = obj.get(key)
# obj.put(key,value)