# Did this code successfully run on Leetcode : Yes
# Time Complexity: O(1) (for put and get function)
# Space Complexity: O(s) (where s is capacity of cache)

# Three line explanation of solution in plain english:
# - Use hashmap and doubly linked list to implement it.
# - Make functions like move to head,  add node to head, remove node and remove tail
# - For get function, If key is found in hashmap than return it and move that node to the head in doubley-linklist, else return -1.
# - For put function, If key is in the hashmap than move that node to the head in doubley-linklist and update it's value in hashmap. If key is not in the hashmap, create new node, add it to the hashmap and head. If size of hashmap exceeds capacity than prune linkelist from tail.

class Node:
#   Each node has prev, next, key and value.
    def __init__(self, k, v):
        self.key = k
        self.value = v
        self.prev = self
        self.next = self
        
class LRUCache:
    

    def __init__(self, capacity: int):
#       Initialize hashmap, size, head node and tail node.
        self.dic = {}
        self.size = capacity
        self.head = Node(0, 0)
        self.tail = Node(0, 0)
#       Point head and tail nodes to each other
        self.head.next = self.tail
        self.tail.prev = self.head
        
#   Insert to head function
    def insert_to_head(self, node):
        head_next = self.head.next
        self.head.next = node
        node.prev = self.head
        node.next = head_next
        head_next.prev = node
        
#   remove node function
    def remove_node(self, node):
        node.prev.next = node.next
        node.next.prev = node.prev
        
#   remove tail function
    def remove_tail(self):
        last = self.tail.prev
        del self.dic[last.key]
        self.remove_node(last)
        
#   move to head function
    def move_to_head(self, node):
        self.remove_node(node)
        self.insert_to_head(node)
        

    def get(self, key: int) -> int:
#       If key is found than move it to the head and return it's value
        if key in self.dic:
            node = self.dic[key]
            self.move_to_head(node)
            return node.value
#       else return -1
        else:
            return -1
            

    def put(self, key: int, value: int) -> None:
#       If key is in the hashmap than move that node to the head in doubley-linklist and update it's value in hashmap
        if key in self.dic:
            node = self.dic[key]
            self.move_to_head(node)
            node.value = value
#       If key is not in the hashmap, create new node, add it to the hashmap and head.
        else:
            node = Node(key, value)
            self.dic[key] = node
            self.insert_to_head(node)
            
#           If size of hashmap exceeds capacity than prune linkelist from tail
            if len(self.dic) > self.size:
                self.remove_tail()


# Your LRUCache object will be instantiated and called as such:
# obj = LRUCache(capacity)
# param_1 = obj.get(key)
# obj.put(key,value)
