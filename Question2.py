# // Time Complexity : O(1) 
# // Space Complexity : O(1) 
# // Did this code successfully run on Leetcode : Yes
# // Any problem you faced while coding this : Class Solution.
class DLLNode:
    def __init__(self, key = None, value = None):
        self.next = None
        self.prev = None
        self.key = key
        self.val = value
class LRUCache:

    def __init__(self, capacity: int):
        self.hashMap = dict()
        self.capacity = capacity
        self.head = DLLNode()
        self.tail = DLLNode()
        self.head.next = self.tail
        self.tail.prev = self.head
    
    def removeNode(self, node):
        node.prev.next = node.next
        node.next.prev = node.prev
    
    def insertNodeAtHead(self, node):
        node.prev = self.head
        node.next = self.head.next
        self.head.next = node
        node.next.prev = node
    
    def removeNodeAtTail(self):
        toremove = self.tail.prev
        self.tail.prev.prev.next = self.tail
        self.tail.prev = self.tail.prev.prev
        return toremove

    def get(self, key: int) -> int:
        toreturn = -1
        if key in self.hashMap:
            toreturn =  self.hashMap[key].val
            node = self.hashMap[key]
            self.removeNode(node)
            self.insertNodeAtHead(node)
        return toreturn
        

    def put(self, key: int, value: int) -> None:
        if key in self.hashMap:
            node = self.hashMap[key]
            self.removeNode(node)
            node.val = value
            self.insertNodeAtHead(node)
            return
        if self.capacity != 0:
            self.hashMap[key] = DLLNode(key,value)
            self.capacity -=1
            self.insertNodeAtHead(self.hashMap[key])
            return
        node =  self.removeNodeAtTail()
        self.hashMap.pop(node.key)
        nodeToInsert = DLLNode(key,value)
        self.hashMap[key] = nodeToInsert
        self.insertNodeAtHead(nodeToInsert)

        


# Your LRUCache object will be instantiated and called as such:
# obj = LRUCache(capacity)
# param_1 = obj.get(key)
# obj.put(key,value)