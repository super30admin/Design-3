# Time Complexity: O(1)
# Space Complexity: O(n)

class Node:
    def __init__(self, key, val): # we are using doubly LL here
        self.key = key
        self.val = val
        self.next = None
        self.prev = None

class LRUCache(object):

    def __init__(self, capacity):
        """
        :type capacity: int
        """
        self.dic={}
        self.capacity=capacity 
        # create dummy head and tail nodes
        self.head = Node(-1,-1)
        self.tail = Node(-1,-1)
        self.head.next = self.tail
        self.tail.prev = self.head

    # remove the nose and adjust prev and next accordingly
    def removeNode(self, node):
        node.prev.next = node.next
        node.next.prev = node.prev

    # add a node at head of LL
    def addHead(self, node):
        node.next = self.head.next
        node.prev = self.head
        self.head.next = node
        node.next.prev = node

    def get(self, key):
        """
        :type key: int
        :rtype: int
        """
        if key not in self.dic:
            return -1
        # if the key is in dic, remove the current node and move it at head of LL
        node= self.dic[key]
        self.removeNode(node)
        self.addHead(node)
        return node.val
        

    def put(self, key, value):
        """
        :type key: int
        :type value: int
        :rtype: None
        """
        # if the key is in dic, update the value with new value and remove the current node and move it at head of LL
        if key in self.dic:
            node = self.dic[key]
            node.val = value
            self.removeNode(node)
            self.addHead(node)
        # else check for the capacity, if we are out of it- delete the LRU and add new node at the head of LL
        else:
            if len(self.dic) == self.capacity:
                lastNode = self.tail.prev
                self.removeNode(lastNode)
                del self.dic[lastNode.key]
            newNode= Node(key,value)
            self.addHead(newNode)
            self.dic[key] = newNode

        
        


# Your LRUCache object will be instantiated and called as such:
# obj = LRUCache(capacity)
# param_1 = obj.get(key)
# obj.put(key,value)
