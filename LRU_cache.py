#Problem 1: LRU CACHE
# All operations are O(1)


class Node:
    def __init__(self,key,val):
        self.key=key
        self.val=val
        self.next=None
        self.prev=None

class LRUCache:

    def __init__(self, capacity: int):
        self.capacity=capacity
        self.h={}
        self.head=Node(-1,-1)
        self.tail=Node(-1,-1)

        #important to attach them
        self.head.next=self.tail
        self.tail.prev=self.head

    def addNodeHead(self,node):
        node.next=self.head.next
        self.head.next=node
        node.prev=self.head
        node.next.prev=node

    def removeNode(self,node):
        node.next.prev=node.prev
        node.prev.next=node.next

    def get(self, key: int) -> int:
        if key not in self.h: return -1
        node=self.h[key]
        self.removeNode(node)
        self.addNodeHead(node)
        return node.val


    def put(self, key: int, value: int) -> None:
        #scenario 1, node in cache
        if key in self.h:
            self.h[key].val=value
            self.get(key)
        else:
            if len(self.h)==self.capacity:
                last=self.tail.prev
                del self.h[last.key]
                self.removeNode(last)
            new=Node(key,value)
            self.h[key]=new
            self.addNodeHead(new)
