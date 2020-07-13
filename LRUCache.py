class Node:
    def __init__(self,k,v):
        self.next=None
        self.prev=None
        self.key=k
        self.value=v
class LRUCache:

    def __init__(self, capacity: int):
        self.capacity=capacity
        self.tail=Node(0,0)
        self.head=Node(0,0)
        self.dic=dict()
        self.head.next=self.tail
        self.tail.prev=self.head

    def get(self, key: int) -> int:
        if key in self.dic:
            n=self.dic[key]
            self.remove(n)
            self.add(n)
            return n.value
        return -1

    def put(self, key: int, value: int) -> None:
        if key in self.dic:
            self.remove(self.dic[key])
        new=Node(key,value)
        self.add(new)
        self.dic[key]=new
        if len(self.dic)>self.capacity:
            n=self.head.next
            self.remove(n)
            del self.dic[n.key]
            
            
    def remove(self,new):
        n=new.next
        p=new.prev
        p.next=n
        n.prev=p
        
    def add(self,new):
        p=self.tail.prev
        new.next=self.tail
        self.tail.prev=new
        p.next=new
        new.prev=p
        
        
# Your LRUCache object will be instantiated and called as such:
# obj = LRUCache(capacity)
# param_1 = obj.get(key)
# obj.put(key,value)

#time complexity is O(1)
#space complexity is O(n)
