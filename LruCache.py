'''
space complexity: O(1)

'''
class Node:
    def __init__(self,key,val):
        self.key = key
        self.val = val
        self.prev = None
        self.next = None
class LRUCache:

    def __init__(self, capacity: int):
        self.capacity = capacity
        self.dict = {}
        self.head = Node(-1,-1)
        self.tail = Node(-1,-1)
        self.head.next = self.tail
        self.head.prev = None
        self.tail.next = None
        self.tail.prev = self.head
    
    #time complexity: O(1)
    def deleteNode(self,Node):
        Node.next.prev = Node.prev
        Node.prev.next = Node.next
    
    #time complexity: O(1)
    def putatHead(self,node):
        node.prev = self.head
        node.next = self.head.next
        self.head.next = node
        node.next.prev = node
        
    #time complexity: O(1)
    def get(self, key: int) -> int:
        if(key in self.dict):
            node = self.dict[key]
            self.deleteNode(node)
            self.putatHead(node)
            return node.val
        else:
            return -1
        
    #time complexity: O(1)
    def put(self, key: int, value: int) -> None:
        if(key in self.dict):
            node = self.dict[key]
            node.val = value
            self.deleteNode(node)
            self.putatHead(node)
        else:
            node = Node(key,value)
            self.dict[key]=node
            if self.capacity==0:
                tailprev = self.tail.prev
                self.deleteNode(tailprev)
                del self.dict[tailprev.key]
                self.putatHead(node)
            else:
                self.putatHead(node)
                self.capacity-=1
            


# Your LRUCache object will be instantiated and called as such:
# obj = LRUCache(capacity)
# param_1 = obj.get(key)
# obj.put(key,value)