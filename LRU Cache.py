class LRUCache:
    class ListNode:
        def __init__(self, key_val):
            self.val = key_val
            self.prev = None
            self.next = None

    def __init__(self, capacity: int):
        self.head = ListNode([None, None])
        self.tail = ListNode([None, None])
        self.head.next = self.tail  # Tail pointing
        self.tail.prev = self.head
        self.currsize = 0
        self.maxsize = capacity
        self.hmap = {}
        self.dq = collections.deque()

    def get(self, key: int) -> int:  # Time: O(1), Space: O(1)
        h = self.head
        t = self.tail
        if key not in self.hmap:
            return -1
        node = self.hmap[key]
        # Remove node from current position in cache
        node.prev.next = node.next
        node.next.prev = node.prev
        # Add it to the end of LL(most current position)
        t.prev.next = node
        node.prev = t.prev
        t.prev = node
        node.next = t
        # print("get", node.val)
        return node.val[1]

    def put(self, key: int, value: int) -> None:  # Time: O(1), Space: O(1)
        h = self.head
        t = self.tail
        # print(h)
        if key in self.hmap:
            self.hmap[key].val = [key, value]
            # Remove node from current position in cache
            node = self.hmap[key]
            node.prev.next = node.next
            node.next.prev = node.prev
            # Add it to the end of LL(most current position)
            t.prev.next = node
            node.prev = t.prev
            t.prev = node
            node.next = t
            return
        # Add new node at the back of the LL(most recent position)
        if self.currsize >= self.maxsize:
            # Remove Least recent node if capacity is full(From the start of the LL)
            rem_key = h.next.val
            # print("key to remove",rem_key)
            del self.hmap[rem_key[0]]
            h.next.next.prev = h
            h.next = h.next.next

        # Adding newnode into the LL
        newnode = ListNode([key, value])
        # print("New node to put", newnode.val)
        self.hmap[key] = newnode
        t.prev.next = newnode
        newnode.prev = t.prev
        t.prev = newnode
        newnode.next = t
        self.currsize += 1

        # print("Putting",key, h)
        # print("HASHMAP",self.hmap)

# Your LRUCache object will be instantiated and called as such:
# obj = LRUCache(capacity)
# param_1 = obj.get(key)
# obj.put(key,value)