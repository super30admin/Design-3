# TC = O(1)
# SC = O(capacity) = O(1)
# Approach:
# 1.Maintain a Map that holds the Key:LinkedList node
# 2.A doubly linlked list is used to maintain the order
# 3.A tail and head dummy nodes are created in when initialized
# 4.A new node is added alway to the head using the addtohead function
# 5.If the length if the map is equal to the the capacity then we have 
  # to delete it from the the map and from the linked list that is the last value before the tail
# 6.if an existing item is updated then we update that nodes value,remove it from it location and
	# add it to the head

class LinkedList:
    def __init__(self,val,key):
        self.key = key
        self.val = val
        self.prev = None
        self.next = None
class LRUCache:

    def __init__(self, capacity: int):
        self.head = LinkedList(-1,-1)
        self.tail = LinkedList(-1,-1)
        self.Capacity = capacity
        self.Map = {}
        self.head.next = self.tail
        self.tail.prev = self.head

    def addNodetoHead(self,Node):
        Node.prev = self.head
        Node.next = self.head.next
        self.head.next = Node
        Node.next.prev = Node
    
    def RemoveNode(self,Node):
        Node.next.prev = Node.prev
        Node.prev.next = Node.next
        
    def get(self, key: int) -> int:
        if key not in self.Map:
            return -1
        Node = self.Map[key]
        self.RemoveNode(Node)
        self.addNodetoHead(Node)
        return Node.val
    
    def put(self, key: int, value: int) -> None:
        if key in self.Map:
            Node = self.Map[key]
            Node.val = value
            self.RemoveNode(Node)
            self.addNodetoHead(Node)
            
        else:
            if len(self.Map) == self.Capacity:
                LastNode = self.tail.prev
                self.RemoveNode(LastNode)
                del self.Map[LastNode.key]
                
            NewNode = LinkedList(value,key)
            self.addNodetoHead(NewNode)
            self.Map[key] = NewNode


# Your LRUCache object will be instantiated and called as such:
# obj = LRUCache(capacity)
# param_1 = obj.get(key)
# obj.put(key,value)