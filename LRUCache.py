# Time Complexity :
# O(1) 


# Space Complexity : 
# O(n)


# Approach:
# Approach by using [HashMap + DoublyLinkedList] for O(1) Get and Put operations.

class LRUCache(object):
    class Node:
        def __init__(self, key, value):
            self.key = key
            self.value = value
            self.prev = None
            self.next = None

    def addToHead(self, node):
        node.next = self.head.next
        node.prev = self.head
        node.next.prev = node
        self.head.next = node

    def removeNode(self, node):
        node.prev.next = node.next
        node.next.prev = node.prev


    def __init__(self, capacity):
        """
        :type capacity: int
        """
        self.head = self.Node(-1,-1)
        self.tail = self.Node(-1,-1)
        self.head.next = self.tail
        self.tail.prev = self.head
        self.hashMap = {}
        self.capacity = capacity

    def get(self, key):
        """
        :type key: int
        :rtype: int
        """
        if key not in self.hashMap:
            return -1
        # We found the node with this key
        node = self.hashMap[key]
        self.removeNode(node)
        self.addToHead(node)
        return node.value
        

    def put(self, key, value):
        """
        :type key: int
        :type value: int
        :rtype: None
        """
        if key in self.hashMap:
            node = self.hashMap[key]
            self.removeNode(node)
            self.addToHead(node)
            node.value = value  # update the value of current node
            return;
        
        if len(self.hashMap) == self.capacity:
            # delete the least recently used node from LL and Hashmap
            tailPrev = self.tail.prev
            self.removeNode(tailPrev)
            self.hashMap.pop(tailPrev.key)
        
        # Else add the new(key,value) pair to the Hashmap and DoublyLinkedList
        node = self.Node(key,value)
        self.addToHead(node)
        self.hashMap[key] = node