class Node:
    def __init__(self,key=0,val=0,prev=None,nxt=None):
        self.key=key
        self.val=val
        self.prev=prev
        self.nxt=nxt
        
class LRUCache:

    def __init__(self, capacity: int):
        self.capacity=capacity
        self.head=Node(-1,-1)
        self.tail=Node(-1,-1)
        self.d={}
        self.head.nxt=self.tail
        self.tail.prev=self.head
        
    def remove_node(self,node):
        node.nxt.prev=node.prev
        node.prev.nxt=node.nxt
        
    def add_to_head(self,node):
        node.nxt=self.head.nxt
        node.prev=self.head
        self.head.nxt=node
        node.nxt.prev=node

    def get(self, key: int) -> int:
        if key not in self.d:
            return -1
        
        node=self.d[key]
        self.remove_node(node)
        self.add_to_head(node)
        return node.val

    def put(self, key: int, value: int) -> None:
        if key in self.d:
            node=self.d[key]
            node.val=value
            self.remove_node(node)
            self.add_to_head(node)
            return
        
        if len(self.d)==self.capacity:
            new_tail=self.tail.prev
            self.remove_node(new_tail)
            del self.d[new_tail.key]
            
        node=Node(key,value)
        self.add_to_head(node)
        self.d[key]=node
            
        
        


# Your LRUCache object will be instantiated and called as such:
# obj = LRUCache(capacity)
# param_1 = obj.get(key)
# obj.put(key,value)