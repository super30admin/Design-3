# Time Complexity :O(1)
# Space Complexity :O(n)
# Did this code successfully run on Leetcode : yes
# Any problem you faced while coding this : no


# Your code here along with comments explaining your approach
#node class
class Node():
    def __init__(self,key,val):
        self.key = key
        self.val = val
        self.prev = None
        self.next = None
class LRUCache(object):

    def __init__(self, capacity):
        """
        :type capacity: int
        """
        # capacity
        self.capacity = capacity
        # head and tail dummy node
        self.head = Node(-1,-1)
        self.tail = Node(-1,-1)
        # connect them properly
        self.head.next = self.tail
        self.tail.prev = self.head
        # Dict saving all nodes
        self.nodes = {}
        # no of nodes
        self.length = 0
    # private insert method
    def __insertinfront(self,newNode):
        newNode.next = self.head.next
        self.head.next = newNode
        newNode.next.prev = newNode
        newNode.prev = self.head
    # private function for eviction of least recently used
    def __evict(self):
        self.tail.prev = self.tail.prev.prev
        self.tail.prev.next = self.tail
        self.length -=1
        

    def get(self, key):
        """
        :type key: int
        :rtype: int
        """
        # if this key not in nodes return 01
        if key not in self.nodes:
            return -1
        temp = self.nodes[key]
        # remove node from its place
        temp.next.prev = temp.prev
        temp.prev.next = temp.next
        # insert it infront
        self.__insertinfront(temp)
        # return val of node
        return temp.val
        

    def put(self, key, value):
        """
        :type key: int
        :type value: int
        :rtype: None
        """
        # if put before update and make it most recently used
        if key in self.nodes:
            temp = self.nodes[key]
            temp.val = value
            temp.next.prev = temp.prev
            temp.prev.next = temp.next
            self.__insertinfront(temp)
        # if first time to insert
        else:    
            # create a new node for it dll
            newNode = Node(key,value)
            # insert it infront
            self.__insertinfront(newNode)
            # if length at capacity
            if self.length == self.capacity:
                # remove least recently used
                del self.nodes[self.tail.prev.key]
                self.__evict()
            # increase length
            self.length +=1
            # add new node to the dict
            self.nodes[key] = newNode
# Your LRUCache object will be instantiated and called as such:
# obj = LRUCache(capacity)
# param_1 = obj.get(key)
# obj.put(key,value)