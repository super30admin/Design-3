#
# @lc app=leetcode id=146 lang=python3
#
# [146] LRU Cache
#

# @lc code=start
'''
Time Complexity - O(1) for put and get as we are using hashMap for storing keys and node
Space Complexity - O(Capacity) as we are maintaining a HashMap for storing the keys and node and size at max would be same as capacity

This code works on Leetcode.
'''
class Node: #create the LinkedList Node
    def __init__(self, key, val):
        self.key = key
        self.val = val
        self.next = None
        self.prev = None

class LRUCache:

    def __init__(self, capacity: int): #create LinkedList
        self.head = Node(-1,-1)
        self.tail = Node(-1,-1) 
        self.hashMap = {}
        self.capacity = capacity
        self.head.next = self.tail
        self.tail.prev = self.head

    def removeNode(self, node): #O(1). remove a node from the LinkedList
        node.next.prev = node.prev
        node.prev.next = node.next

    def addToHead(self, node): #O(1) Add a Node to head of the LinkedList
        node.next = self.head.next
        node.prev = self.head
        self.head.next = node 
        node.next.prev = node
        

    def get(self, key: int) -> int:
        if key not in self.hashMap: #check if key is present in the cache through HashMap
            return -1 #return -1 if not present
        node = self.hashMap[key] #else get the node as we have used it
        self.removeNode(node) #remove the node from the list and add put in the front as it becomes most recently used
        self.addToHead(node)
        return node.val  #returnt the value of the node
        

    def put(self, key: int, value: int) -> None:
        if key in self.hashMap: #if node present in cache, update value, take out from the LinkedList and place it at first
            node = self.hashMap[key]
            node.val = value
            self.removeNode(node)
            self.addToHead(node)
        else:
            if self.capacity == len(self.hashMap): #if node not present, check the capacity of the Cache,
                #remove the previous
                leastRecent = self.tail.prev #if current size equals capacity, remove the last node as its least recent
                self.removeNode(leastRecent)
                self.hashMap.pop(leastRecent.key)
            newNode = Node(key, value) #create a new node for new entry
            self.addToHead(newNode) #Add it to head as its most recently used
            self.hashMap[key] = newNode #establish entry in hashMap



# Your LRUCache object will be instantiated and called as such:
# obj = LRUCache(capacity)
# param_1 = obj.get(key)
# obj.put(key,value)
# @lc code=end

