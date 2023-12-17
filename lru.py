class doublyLinkedList:
    def __init__(self, key,val=0, next=None,prev=None):
        self.key=key
        self.val = val
        self.next = next
        self.prev = prev

class LRUCache:

    def __init__(self, capacity: int):
        self.d={}
        self.capacity = capacity
        self.head = doublyLinkedList(-1,-1,None,None)
        self.tail = doublyLinkedList(-1,-1,None,None)
        self.head.next = self.tail
        self.tail.prev=self.head

    def get(self, key: int) -> int:
        if key in self.d:
            curr = self.d[key]
            self.removeNode(curr)
            self.addNodeToHead(curr)
            return curr.val
        else:
            return -1

    def removeNode(self,curr):
        curr.prev.next=curr.next
        curr.next.prev = curr.prev


    def addNodeToHead(self,curr):
        curr.prev=self.head
        curr.next = self.head.next
        self.head.next.prev = curr
        self.head.next = curr

        

    def put(self, key: int, value: int) -> None:
        if key in self.d:
            curr = self.d[key]
            self.removeNode(curr)
            self.addNodeToHead(curr)
            curr.val=value

        else:
            if len(self.d)==self.capacity:
                prev = self.tail.prev
                self.removeNode(prev)
                del self.d[prev.key]

            temp=doublyLinkedList(key,value,None,None)
            self.addNodeToHead(temp)
            self.d[key]=temp







        


# Your LRUCache object will be instantiated and called as such:
# obj = LRUCache(capacity)
# param_1 = obj.get(key)
# obj.put(key,value)