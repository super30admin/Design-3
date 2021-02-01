# Time Complexity : O(1)
# Space Complexity : O(N) N = the size of Capacity perhaps O(1? since its just size of capacity)
# Did this code successfully run on Leetcode : Yes
# Any problem you faced while coding this : Had trouble implementing what was shown in 
# class. Understood what had to be done after I drew it out


# Your code here along with comments explaining your approach


class Node:
    def __init__(self, key, val):
        self.key = key 
        self.val = val 
        self.next = None
        self.prev = None
    
        
class LRUCache:
    """
    Can use LL and queue.
        - LL
            - get O(N)
            - put O(n)
            *should use hashmap to optimize get to O(1)
    
    """
    def addToHead(self, node):
        node.next = self.head.next
        node.prev = self.head
        self.head.next = node
        node.next.prev = node 
    
    def removeNode(self, node):
        node.next.prev = node.prev
        node.prev.next = node.next 

    def __init__(self, capacity: int):
        self.hm = defaultdict()
        self.capacity = capacity
        self.head = Node(-1,-1)
        self.tail = Node(-1,-1)
        self.head.next = self.tail 
        self.tail.prev = self.head 

    def get(self, key: int) -> int:
        if key not in self.hm:
            return -1 
        
        node = self.hm[key]
        self.removeNode(node)
        self.addToHead(node)
        
        return node.val

    def put(self, key: int, value: int) -> None:
        if key in self.hm:
            node = self.hm[key]
            node.val = value
            self.removeNode(node)
            self.addToHead(node)
        else:
            newNode = Node(key,value)
            if self.capacity == len(self.hm):
                tailPrev = self.tail.prev 
                self.removeNode(tailPrev)
                del self.hm[tailPrev.key]
            self.addToHead(newNode)
            self.hm[key] = newNode

# Your LRUCache object will be instantiated and called as such:
# obj = LRUCache(capacity)
# param_1 = obj.get(key)
# obj.put(key,value)