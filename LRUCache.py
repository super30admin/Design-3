'''
Solution:
1.  In order to design LRU Cache, we need Doubly-Linked-List to store cache which makes it O(1) operation to remove a
    node from any position and also to insert at the Head (this is the place to store most recently accessed element).
2.  We also use a HashMap to store node's key and its address (complete Node object) for faster access.
3.  Two helper functions to remove node and insert node at head.

Time and Space Complexities of all functions are O(1)

--- Passed all testcases successfully on Leetcode.
'''


class Node(object):

    #   Node which is part of a Doubly-Linked-List having a key, value, nextNode reference and previousNode reference

    def __init__(self, key, value):
        self.key = key
        self.value = value
        self.next = None
        self.prev = None


class LRUCache(object):

    def __init__(self, capacity):
        """
        :type capacity: int
        """

        #   initialize LRU Cache with given capacity, dummy head and tail nodes pointing to each other
        self.capacity = capacity

        self.head = Node(-1, -1)        #   dummy head
        self.tail = Node(-1, -1)        #   dummy tail
        self.head.next = self.tail      #   head's next is initially pointing to tail
        self.tail.prev = self.head      #   tail's prev is initially pointing to head

        self.keyNodeMap = {}            #   HashMap to store node's key as Map's key and node itself as Map's value

    def __printCache(self):

        #   helper function to print elements stored in cache at a particular point of time

        currNode = self.head.next
        while (currNode != self.tail):
            print(currNode.key, currNode.value)
            currNode = currNode.next

        print('---DONE---')

    def __insertAtHead(self, node):

        #   Time Complexity:    O(1)    |   Space Complexity:   O(1)
        #   insert's a node at the head position (next node of dummy head)

        #   make correct references to the next and prev of the node in consideration
        node.prev = self.head
        node.next = self.head.next

        #   attach node's reference to dummy head and node's next node
        self.head.next = node
        node.next.prev = node

        return

    def __remove(self, node):

        #   Time Complexity:    O(1)    |   Space Complexity:   O(1)
        #   removes a node keeping intact its next and prev nodes.
        node.prev.next = node.next
        node.next.prev = node.prev

        return

    def get(self, key):
        """
        :type key: int
        :rtype: int
        """

        #   Time Complexity:    O(1)    |   Space Complexity:   O(1)

        if (key not in self.keyNodeMap):        #   if key not present in HashMap => can't extract from cache
            return -1
        else:                                   #   else
            node = self.keyNodeMap[key]         #   capture the actual node using key from HashMap
            self.__remove(node)                 #   remove the node from current position
            self.__insertAtHead(node)           #   add it to the front of the DLL as it is accessed latest
            return node.value                   #   return the value

    def put(self, key, value):
        """
        :type key: int
        :type value: int
        :rtype: None
        """

        #   Time Complexity:    O(1)    |   Space Complexity:   O(1)

        if (key in self.keyNodeMap):                #   if key present in HashMap => update the value
            self.keyNodeMap[key].value = value
            node = self.keyNodeMap[key]             #   capture the actual node using key from HashMap

            self.__remove(node)                     #   remove the node from current position
            self.__insertAtHead(node)               #   add it to the front of the DLL as it is accessed latest

        else:
            if (len(self.keyNodeMap) == self.capacity): #   if capacity is maximum
                node = self.tail.prev                   #   capture the tail node (least recently used)
                self.__remove(node)                     #   remove the node from current position
                self.keyNodeMap.pop(node.key)           #   remove the node from HashMap

            self.keyNodeMap[key] = Node(key, value)     #   place the node in the HashMap
            node = self.keyNodeMap[key]                 #   capture the actual node using key from HashMap
            self.__insertAtHead(node)                   #   add it to the front of the DLL as it is accessed latest

        return

# Your LRUCache object will be instantiated and called as such:
# obj = LRUCache(capacity)
# param_1 = obj.get(key)
# obj.put(key,value)
import itertools

gen = iter([1,2,3])
peek = next(gen)
print(list(itertools.chain([peek], gen)))