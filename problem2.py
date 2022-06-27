class Node:
        def __init__(self,key,value):
            self.key = key 
            self.value = value 
            self.next = None
            self.prev = None
class LRUCache:
    
    def addNode(self,node,pre):
        node.next = pre.next
        pre.next.prev = node
        pre.next = node
        node.prev = pre
        self.hashmap[node.key]=node
    def removeNode(self,node):
        node.prev.next = node.next
        node.next.prev = node.prev
        self.hashmap.pop(node.key)
    def __init__(self, capacity: int):
        self.capacity = capacity
        self.hashmap = dict()
        self.head = Node(-1,-1)
        self.tail = Node(-1,-1)
        self.head.next = self.tail 
        self.tail.prev = self.head
        self.count = 0
    def get(self, key: int) -> int:
        if(key not in self.hashmap ):
            return -1
        curr = self.hashmap[key]
        self.removeNode(curr)
        self.addNode(curr,self.head)
        return self.hashmap[key].value

    def put(self, key: int, value: int) -> None:
        
        if(key in self.hashmap):
            curr = self.hashmap[key] 
            curr.value = value
            self.removeNode(curr)
            self.addNode(curr,self.head)
        else:
            
            if(self.capacity ==self.count):
                self.removeNode(self.tail.prev)
                self.count-=1
            node = Node(key,value)
            self.addNode(node,self.head)
            self.count+=1


# Your LRUCache object will be instantiated and called as such:
# obj = LRUCache(capacity)
# param_1 = obj.get(key)
# obj.put(key,value)