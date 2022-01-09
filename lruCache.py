'''
TC: O(1)
SC : O(Capacity)
'''
class Node:
	def __init__(self, key, value):
		self.key = key
		self.value = value
		self.next = None
		self.prev = None

class LRUCache:
    def __init__(self, capacity: int):
        self.map=dict()
        self.capacity=capacity
        self.head=Node(-1,-1)
        self.tail=Node(-1,-1)
        self.head.next=self.tail
        self.tail.prev=self.head
        
    def removeNode(self,node):
        node.prev.next=node.next
        node.next.prev=node.prev
        
    def AddToHead(self,node):
        node.next=self.head.next
        node.prev=self.head
        self.head.next=node
        node.next.prev=node
        
    def get(self, key: int) -> int:
        if not self.map.get(key):
            return -1    
        node=self.map.get(key)
        self.removeNode(node)
        self.AddToHead(node)
        return node.value
    
    def put(self, key: int, value: int) -> None:
        if self.map.get(key):
            tempNode=self.map.get(key)
            self.removeNode(tempNode)
            self.AddToHead(tempNode)
            tempNode.value=value
        else:
            if len(self.map) == self.capacity:
                tailPrev = self.tail.prev
                self.removeNode(tailPrev)
                del self.map[tailPrev.key]
            nnode = Node(key,value)
            self.AddToHead(nnode)
            self.map[key]=nnode

# Your LRUCache object will be instantiated and called as such:
# obj = LRUCache(capacity)
# param_1 = obj.get(key)
# obj.put(key,value)