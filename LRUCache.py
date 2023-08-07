class LRUCache:
    class Node:
        
        def __init__(self,k, v):
            self.key=k
            self.value=v
            self.prev=None
            self.next=None

    def __init__(self, capacity: int):
        self.hash_map={}
        self.head=self.Node(-1,-1)
        self.tail=self.Node(-1,-1)
        self.head.next=self.tail
        self.head.prev=None
        self.tail.prev=self.head
        self.tail.next=None
        self.capacity=capacity
    
    def addToHead(self,NewNode):
        NewNode.next=self.head.next
        self.head.next.prev=NewNode
        NewNode.prev=self.head
        self.head.next=NewNode

    def removeFromList(self,currNode):
        currNode.prev.next=currNode.next
        currNode.next.prev=currNode.prev
        currNode.prev=None
        currNode.next=None
        
    def get(self, key: int) -> int:
        if key not in self.hash_map:
            return -1
        current=self.hash_map.get(key)
        self.removeFromList(current)
        self.addToHead(current)
        return current.value

    def put(self, key: int, value: int) -> None:
        if key in self.hash_map :
            current=self.hash_map.get(key)
            current.value=value
            self.hash_map[key]=current
            self.removeFromList(current)
            self.addToHead(current)
            return
        if len(self.hash_map)==self.capacity:
            self.hash_map.pop(self.tail.prev.key)
            self.removeFromList(self.tail.prev)
        newNode=self.Node(key,value)
        self.addToHead(newNode)
        self.hash_map[key]=newNode



# Your LRUCache object will be instantiated and called as such:
# obj = LRUCache(capacity)
# param_1 = obj.get(key)
# obj.put(key,value)