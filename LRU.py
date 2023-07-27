# Time complexity : O(1)
# Space complexity : O(n) to maintain the doubly linked list and the hash map

# The code ran on Leetcode


# maintain a doubly linked list with two dummy nodes : Head and Tail. Most recently used nods are towards the head pointer and the least recently used ones are towards the Tail pointer. Keep adding elements at the head pointer until capacity is full. When capacity is full, remove node that is closer to the tail pointer. Also maintain a hash map that maps keys to the pointer sin linked list

class ListNode:
    def __init__(self, data = (-1, -1)):
        self.data = data
        self.next = None
        self.prev = None

class LRUCache:

    def __init__(self, capacity: int):
        self.capacity = capacity
        self.head = ListNode((-1, -1))
        self.tail = ListNode((-1, -1))

        self.head.next = self.tail
        self.tail.prev = self.head

        self.map = {}


    def add_head(self, node):
        node.prev = self.head; node.next = self.head.next
        self.head.next = node
        node.next.prev = node

    def get(self, key: int) -> int:
        
        if key not in self.map:
            return -1
        # print(self.map[key].data)
        cur = self.map[key]
        out = cur.data[1]
        cur.prev.next = cur.next
        cur.next.prev = cur.prev

        cur.next = None; cur.prev = None

        self.add_head(cur)
        return out
        
    def put(self, key: int, value: int) -> None:
        # Updating values of already existing keys
        if key in self.map:
            self.map[key].data = (key, value)
            cur = self.map[key]

            cur.prev.next = cur.next
            cur.next.prev = cur.prev

            cur.next = None; cur.prev = None

            self.add_head(cur)

        else:
            if self.capacity > 0:
                new_node = ListNode((key, value))
                self.add_head(new_node)
                self.map[key] = new_node
                self.capacity -= 1
            else:
                new_node = ListNode((key, value))
                cur = self.tail.prev
                self.map.pop(cur.data[0])
                cur.prev.next = self.tail

                self.tail.prev = cur.prev
                cur.prev = None; cur.next = None

                self.add_head(new_node)
                self.map[key] = new_node


# Your LRUCache object will be instantiated and called as such:
# obj = LRUCache(capacity)
# param_1 = obj.get(key)
# obj.put(key,value)