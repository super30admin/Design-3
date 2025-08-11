class DLLNode:
    def __init__(self, key, value):
        self.key=key
        self.value=value
        self.next=None
        self.prev=None

class LRUCache:
    def __init__(self, capacity: int):
        self.head=DLLNode(float('inf'), float('inf'))
        self.tail=DLLNode(float('inf'), float('inf'))
        self.head.next=self.tail
        self.tail.prev=self.head
        self.capacity=capacity
        self.hmap={}
        

    def rem(self, node):
        prv=node.prev
        nxt=node.next
        prv.next=nxt
        nxt.prev=prv
    
    def ins(self, node):
        prv=self.tail.prev
        prv.next=node
        node.prev=prv
        node.next=self.tail
        self.tail.prev=node

    def get(self, key: int) -> int:
        if key in self.hmap:
            node=self.hmap[key]
            self.rem(node)
            self.ins(node)
            return node.value
        return -1


    def put(self, key: int, value: int) -> None:
        if key in self.hmap:
            self.rem(self.hmap[key])
        newNode=DLLNode(key, value)
        self.hmap[key]=newNode
        self.ins(newNode)

        if len(self.hmap) > self.capacity:
            deleteNode=self.head.next
            self.rem(deleteNode)
            del self.hmap[deleteNode.key]