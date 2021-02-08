#Time Complexity:O(1)
#Space Complexity:O(n)

class Node:
    def __init__(self,key:int,value:int):   #Declaring structure of a Node
        self.key=key
        self.val=value
        self.prev=None
        self.next=None
        
class LRUCache:
    def addToHead(self,node:Node)->None:                    #adding a node at the head node
        node.prev=self.head
        node.next=self.head.next
        self.head.next=node
        node.next.prev=node
    
    def removeNode(self,node:Node)->None:                   #removing a node between two nodes
        node.next.prev=node.prev
        node.prev.next=node.next
    
    def __init__(self, capacity: int):
        self.capacity=capacity                              #initializing the capacity, declaring a hashmap,declaring dummy heads and tails and linking them
        self.dict={}
        self.head=Node(-1,-1)
        self.tail=Node(-1,-1)
        self.head.next=self.tail
        self.tail.prev=self.head

    def get(self, key: int) -> int:                         #get a particular node with the given key if it exists else return -1
        if key not in self.dict:
            return -1
        node=self.dict[key]                                 #the node is removed from its current position and added to the head
        self.removeNode(node)
        self.addToHead(node)
        return node.val                                     #the value of the node is returned
        

    def put(self, key: int, value: int) -> None:            #if the node with the given key exists, it is removed from current position and added to the head
        if key in self.dict:
            node=self.dict[key]
            node.val=value
            self.removeNode(node)
            self.addToHead(node)
        else:
            if self.capacity==len(self.dict):               #This function removes node at the last position and adds new node at the head
                tailPrev=self.tail.prev
                self.removeNode(tailPrev)
                del self.dict[tailPrev.key]
            newNode=Node(key,value)
            self.addToHead(newNode)
            self.dict[key]=newNode


# Your LRUCache object will be instantiated and called as such:
# obj = LRUCache(capacity)
# param_1 = obj.get(key)
# obj.put(key,value)