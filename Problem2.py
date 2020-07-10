# Time Complexity : O(1)
# Space Complexity : O(1)
# Did this code successfully run on Leetcode : Yes
# Any problem you faced while coding this : No
# Your code here along with comments explaining your approach
#   1) Create a double linkedlist 
#   2) Store key and node as Value inside the dict, so that search of the key will happen in O(1)
#       and we can directly reach to the node in the double LL by having the access to the Node reference.
class Node:
    def __init__(self,key,value):
        self.key = key 
        self.value = value 
        self.next = None 
        self.prev = None 
class LRUCache:
    head = None 
    tail = None 
    def __init__(self, capacity: int):
        self.map = {}
        self.head = Node(-1,-1)
        self.tail = Node(-1,-1)
        self.head.next = self.tail 
        self.tail.prev= self.head 
        self.cap = capacity
        
    def get(self, key: int) -> int:
        if key not in self.map:
            return -1 
        node = self.map[key]
        self.__remove(node)
        self.__add(node)
        return node.value 

    def put(self, key: int, value: int) -> None:
        if key in self.map:
            node = self.map[key] 
            node.value = value  # update the new value 
            self.__remove(node) 
            self.__add(node)    # mark the node to newly visited
        
        # If key is not present in the map
        else: 
            # if we dont have capacity then free up the space & add 
            if self.cap == len(self.map):
                tailNode = self.tail.prev
                self.__remove(tailNode)
                self.map.pop(tailNode.key)
            # if we have ample of capacity and the key is not present in our map
            newNode = Node(key,value)
            self.map[key] = newNode
            self.__add(newNode)

    def __remove(self,node):
        node.next.prev = node.prev 
        node.prev.next = node.next 
        
    def __add(self,node):
        node.next = self.head.next 
        node.prev = self.head 
        self.head.next = node 
        node.next.prev = node


# Your LRUCache object will be instantiated and called as such:
# obj = LRUCache(capacity)
# param_1 = obj.get(key)
# obj.put(key,value)