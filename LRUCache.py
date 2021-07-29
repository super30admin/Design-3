# TC: O(1) for both get() and put(), as we get from hashmap in constant time and insert to the head of DLL and remove node in constant time too. 
# SC: O(C) as we have a doubly-linked-list and hashmap of size of the given capacity.

class NodeLL:
    def __init__(self, key, val):
        self.key = key
        self.val = val
        self.prev = None
        self.next = None
    
class DLL: 
    def __init__(self): 
        self.head = NodeLL(-1, -1)
        self.tail = NodeLL(-1, -1)
        self.head.next = self.tail
        self.tail.prev = self.head
    
    def addToHead(self, node):
        # node = NodeLL(key, value)
        node.next = self.head.next 
        self.head.next = node
        node.next.prev = node 
        node.prev = self.head
    
    def moveToHead(self, node): 
        self.removeNode(node)
        self.addToHead(node)
    
    def removeNode(self, node): 
        prev = node.prev
        next = node.next
        
        prev.next = next
        next.prev = prev
    
    def delFromTail(self): 
        prev = self.tail.prev
        self.removeNode(prev)
        return prev
        
class LRUCache:

    def __init__(self, capacity: int):
        self.capacity = capacity
        self.dll = DLL()
        self.hmap = {}
        
    def get(self, key: int) -> int:
        if key in self.hmap.keys(): 
            node = self.hmap.get(key)
            self.dll.moveToHead(node)
            return node.val
        else: 
            return -1
        

    def put(self, key: int, value: int) -> None:
        if key in self.hmap.keys(): 
            node = self.hmap.get(key)
            node.val = value
            self.dll.moveToHead(node)
        else: 
            if len(self.hmap) == self.capacity: 
#                 remove node from hmap and dll.
#                 create new node and insert at the beginning of dll and in hmap
                node_to_remove = self.dll.delFromTail()
                self.hmap.pop(node_to_remove.key)
                new_node = NodeLL(key, value)
                self.dll.addToHead(new_node)
                self.hmap[key] = new_node
            else: 
                self.hmap[key] = NodeLL(key, value)
                self.dll.addToHead(self.hmap.get(key))                   


# Your LRUCache object will be instantiated and called as such:
# obj = LRUCache(capacity)
# param_1 = obj.get(key)
# obj.put(key,value)
