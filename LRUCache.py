'''
TC: O(1) - all operations are O(1) since it has capacity restricted 
SC: O(capacity) - at all times this would be the space occupied
'''
class Node:
    def __init__(self, key, val, prev = None, next = None):
        self.key = key
        self.val = val
        self.prev = prev
        self.next = next

class LRUCache:

    def __init__(self, capacity: int):
        self.head = Node(-1,-1)
        self.tail = Node(-1,-1)
        self.head.next = self.tail
        self.tail.prev = self.head
        self.capacity = capacity
        #key to node 1 -> 1*
        self.search = {}
    
    def add(self, node):
        node.prev = self.head
        node.next = self.head.next
        self.head.next.prev = node
        self.head.next = node

    def get(self, key: int) -> int:
        if key in self.search:
            node = self.search[key]
            p, n = node.prev, node.next
            p.next = n
            n.prev = p
            self.add(node)
            return node.val
        else:
            return -1
        
    def put(self, key: int, value: int) -> None:
        if len(self.search) < self.capacity:
            #Insert
            if key in self.search:
                node = self.search[key]
                p, n = node.prev, node.next
                p.next = n
                n.prev = p
                node.val = value
            else:
                node = Node(key, value)
            self.add(node)
            self.search[key] = node            
        else:
            if key in self.search:
                node = self.search[key]
                p, n = node.prev, node.next
                p.next = n
                n.prev = p
                self.add(node)
                node.val = value
                self.search[key] = node 
            else:
                #Delete and Insert
                temp = self.tail.prev
                if temp != self.head:
                    p = temp.prev
                    p.next = self.tail
                    self.tail.prev = p
                    temp.prev = None
                    temp.next = None
                    del self.search[temp.key]
                    node = Node(key, value)
                    self.add(node)
                    self.search[key] = node

# Your LRUCache object will be instantiated and called as such:
obj = LRUCache(2)
param_1 = obj.get(1)
print(param_1)
obj.put(1,1)
obj.put(2,2)
param_1 = obj.get(1)
print(param_1)
obj.put(3,3)
param_1 = obj.get(2)
print(param_1)
param_1 = obj.get(3)
print(param_1)
