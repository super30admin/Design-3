# Time Complexity: O(1) - get, put
# Space Complexity: O(n)
class DoublyLinkedListNode:
    def __init__(self, key, val):
        self.key = key
        self.val = val
        self.next = None
        self.prev = None
        
class LRUCache(object):

    def __init__(self, capacity):
        """
        :type capacity: int
        """
        self.hashMap = {}
        self.head = DoublyLinkedListNode(-1,-1)
        self.tail = self.head
        self.capacity = capacity
        self.length = 0
        

    def get(self, key):
        """
        :type key: int
        :rtype: int
        """
        if key not in self.hashMap:
            return -1
        node = self.hashMap[key]
        val = node.val
        if node.next:
            node.prev.next = node.next
            node.next.prev = node.prev
            self.tail.next = node
            node.prev = self.tail
            node.next = None
            self.tail = node
        return val
        

    def put(self, key, value):
            """
            :type key: int
            :type value: int
            """
            if key in self.hashMap:
                node = self.hashMap[key]
                node.val = value
                if node.next:
                    node.prev.next = node.next
                    node.next.prev = node.prev
                    self.tail.next = node
                    node.prev = self.tail
                    node.next = None
                    self.tail = node   
            else:
                node = DoublyLinkedListNode(key, value)
                self.hashMap[key] = node
                self.tail.next = node
                node.prev = self.tail
                self.tail = node
                self.length += 1
                if self.length > self.capacity:
                    remove = self.head.next
                    self.head.next = self.head.next.next
                    self.head.next.prev = self.head
                    del self.hashMap[remove.key]
                    self.length -= 1



# Your LRUCache object will be instantiated and called as such:
# obj = LRUCache(capacity)
# param_1 = obj.get(key)
# obj.put(key,value)