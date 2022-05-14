#LRU Cache
# // Time Complexity :  O(1) as get and put are constant
# // Space Complexity : O(c) - c is the capacity
# // Did this code successfully run on Leetcode :yes
# // Any problem you faced while coding this :no



class LRUCache:
    
    class Node:
        def __init__(self, key=None, val=None):
            self.key=key
            self.val=val
            self.next=None
            self.prev=None
            
        

    def __init__(self, capacity: int):
        self.head = self.Node()
        self.tail = self.Node()
        self.head.next = self.tail
        self.tail.prev = self.head
        self.map = {}
        self.cap=capacity
        
    def insert_front(self, curr):
        curr.next = self.head.next
        curr.prev = self.head
        self.head.next = curr
        curr.next.prev =curr
  
        
    def remove(self, curr):
        curr.prev.next = curr.next
        curr.next.prev = curr.prev
        
 

    def get(self, key: int) -> int:
        if (key in self.map):
            node = self.map[key]
            self.remove(node)
            self.insert_front(node)
            return node.val
        else:
            return -1
        

    def put(self, key: int, value: int) -> None:
        if(key in self.map):
            self.map[key].val = value
            node = self.map[key]
            self.remove(node)
            self.insert_front(node)
            return
        else:
            if(len(self.map)>=self.cap):
                node = self.tail.prev
                self.remove(node)
                del self.map[node.key]
            new = self.Node(key, value)
            self.map[key]=new
            self.insert_front(new)
            return
            
            