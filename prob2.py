# Time Complexity : O(1) as we use a map for O(1) operations
# Space Complexity : O(capacity) for map and doubly LL 
# Did this code successfully run on Leetcode : Yes
# Any problem you faced while coding this : No
# Your code here along with comments explaining your approach

# use a map to search in O(1) time and a linkedlist to maintain the order
# everytime you access an element, move that node to the head of the list,
# to put an element, if the key is present, update the value and move that 
# node to head, else if you have to insert a new node, if the capacity is reached, 
# pop from the tail and add the node to the head, else simply add the node to the head


class Node:
    def __init__(self, key=0, val=None, prev=None, next=None):
        self.key = key
        self.val = val
        self.prev = prev
        self.next = next


class LRUCache:
    
    def _add_node(self, node):
        
        node.next = self.head.next
        node.prev = self.head
        self.head.next = node
        node.next.prev = node
        
    def _remove_node(self, node):
        
        node.prev.next = node.next
        node.next.prev = node.prev
    
    def _move_to_head(self, node):
        
        self._remove_node(node)
        self._add_node(node)
        
    def _pop_tail(self):
        res = self.tail.prev
        self._remove_node(res)
        return res
        
    
    def __init__(self, capacity: int):
        self.capacity = capacity
        self.mydict = {}
        self.size = 0
        self.head, self.tail = Node(), Node()
        
        self.head.next = self.tail
        self.tail.prev = self.head
        

    def get(self, key: int) -> int:
        
        if key not in self.mydict:
            return -1
        node = self.mydict.get(key)
        # self._remove_node(node)
        self._move_to_head(node)
        return node.val
        

    def put(self, key: int, value: int) -> None:
        
        if key in self.mydict:
            node = self.mydict.get(key)
            node.val = value
            self._move_to_head(node)
        
        else:
            node = Node(key, value)
            self.mydict[key] = node
            
            
            if self.size == self.capacity:
                tailPrev = self.tail.prev
                self._pop_tail()
                del self.mydict[tailPrev.key]
                self.size -= 1
                
            self._add_node(node)
            self.size += 1