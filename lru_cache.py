# Time Complexity: O(1)
# Space Complexity: O(capacity)
# Maintaining the doubly linked list to manage the head and the tail
# of so that we can rearrange the elements based on use
class DLinkedList():
    def __init__(self):
        self.key = 0
        self.value = 0
        self.next = None
        self.prev = None


class LRUCache(object):
    # Initialize dummy  head and tail to access last and first element in the list
    # and map to maintain the key to node pair
    def __init__(self, capacity):
        """
        :type capacity: int
        """
        self.head = DLinkedList()
        self.tail = DLinkedList()

        self.head.next = self.tail
        self.tail.prev = self.head

        self.capacity = capacity

        self.hmap = {}
    # To remove node from doubly linked list
    def removeNode(self, node):
        node.prev.next = node.next
        node.next.prev = node.prev
    #  Adding node to the head of the list using dummy head we already defined
    def addToHead(self, node):
        node.next = self.head.next
        node.prev = self.head
        self.head.next = node
        node.next.prev = node
    # To get the node of given key we will get the node from hashmap
    # and we will remove that node from the list (any location) after that as it is LRU
    # we will add it to the head and will return its value
    def get(self, key):
        """
        :type key: int
        :rtype: int
        """
        if key not in self.hmap:
            return -1
        node = self.hmap[key]
        self.removeNode(node)
        self.addToHead(node)
        return node.value
    # In Put function we will create a node with given key and value check if
    # the key is already in hashmap if yes then we will remove that node and if the capacity
    # is full for doubly linked list then we will remove the least used node i.e. last node
    # or node before dummy tail node and remove it from hashmap also
    # In the end add that particular node to the head of the doubly linked list and add it to the hashmap also
    def put(self, key, value):
        """
        :type key: int
        :type value: int
        :rtype: None
        """
        nnode = DLinkedList()
        nnode.key = key
        nnode.value = value
        if key in self.hmap:
            cnode = self.hmap[key]
            self.removeNode(cnode)
            # self.addToHead(nnode)
            # self.hmap[key] = nnode
        elif self.capacity == len(self.hmap):
            node = self.tail.prev
            self.removeNode(node)
            self.hmap.pop(node.key)

        self.addToHead(nnode)
        self.hmap[key] = nnode

# Your LRUCache object will be instantiated and called as such:
# obj = LRUCache(capacity)
# param_1 = obj.get(key)
# obj.put(key,value)