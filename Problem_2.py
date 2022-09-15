"""
Problem 2: LRU Cache(https://leetcode.com/problems/lru-cache/)
Time Complexity : O(1)
Space Complexity : O(C), C = capacity

"""


class Node():
    def __init__(self):
        self.key = 0
        self.data = 0
        self.next = None
        self.prev = None
        
class LRUCache:
    
    def addNode_At_Start(self, node):
        """
        Add node at the start of the linked list
        """
        node.prev = self.head
        node.next = self.head.next
        
        self.head.next.prev = node
        self.head.next = node
        
    def removeNode(self,node):
        """
        Remove the given node 
        """
        node.next.prev = node.prev
        node.prev.next = node.next
        
    def __init__(self, capacity: int):
        # create an hash map to map keys and data in doubly LL
        self.key_map = {}
        self.head = Node()
        self.tail = Node()
        self.head.next = self.tail
        self.tail.prev = self.head
        self.capacity = capacity
        self._size = 0
        
        

    def get(self, key: int) -> int:
        if key not in self.key_map: return -1
        node = self.key_map.get(key)
        self.removeNode(node)
        self.addNode_At_Start(node)
        
        return node.data
            
        

    def put(self, key: int, value: int) -> None:
        if key in self.key_map:
            node = self.key_map.get(key)
            node.data = value
            self.removeNode(node)
            self.addNode_At_Start(node)
        else:
            node = Node()
            node.key = key
            node.data = value
            
            self.key_map[key] = node
            self.addNode_At_Start(node)
            self._size += 1
            
            if self._size > self.capacity:
                # pop the last eelement from the ll
                tail_node = self.tail.prev
                self.removeNode(tail_node)
                del self.key_map[tail_node.key]
                self._size -= 1
                
            
        


# Your LRUCache object will be instantiated and called as such:
# obj = LRUCache(capacity)
# param_1 = obj.get(key)
# obj.put(key,value)