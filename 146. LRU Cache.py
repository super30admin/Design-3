# time complexity: O(1)
# space complexity: O(capacity)

class LRUCache:
    
    class Node:
        def __init__(self, key, value):
            self.key=key
            self.value=value
            nxt=None
            prev=None
        
    head=Node(-1,-1)
    tail=Node(-1,-1)
    
    def removeNode(self, node):
        self.node=node
        node.prev.nxt=node.nxt
        node.nxt.prev=node.prev
        
    def addToHead(self, node):
        self.node=node
        node.nxt=self.head.nxt
        node.prev=self.head
        self.head.nxt=node
        node.nxt.prev=node

    def __init__(self, capacity: int):
        self.head.nxt=self.tail
        self.tail.prev=self.head
        self.hashmap={}
        self.capacity=capacity
        

    def get(self, key: int) -> int:
        if key not in self.hashmap:
            return -1
        node=self.hashmap.get(key)
        self.removeNode(node)
        self.addToHead(node)
        return node.value

    def put(self, key: int, value: int) -> None:
        if key in self.hashmap:
            node=self.hashmap.get(key)
            node.value=value
            self.removeNode(node)
            self.addToHead(node)
            
        else:
            if len(self.hashmap)==self.capacity:
                tailprev=self.tail.prev
                self.removeNode(tailprev)
                del self.hashmap[tailprev.key]
                
                
            node=self.Node(key, value)
            node.value=value
            self.addToHead(node)
            self.hashmap[key]=node
                
            
        
            
            


# Your LRUCache object will be instantiated and called as such:
# obj = LRUCache(capacity)
# param_1 = obj.get(key)
# obj.put(key,value)