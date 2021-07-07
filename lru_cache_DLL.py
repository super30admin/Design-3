# Key Idea: since we need to perform remove and add operations, using singly linked list will not be efficient. For removal, take time O(n)
# create node class to store doubly linked list

class Node:
    def __init__(self, key=-1, val=-1):
        self.next = None
        self.prev = None
        self.key = key
        self.val = val
       
class LRUCache:

    def __init__(self, capacity: int):
        self.head = Node(0, 0)
        self.tail = Node(0, 0)
        # to store key as given keys and value as pointer to corresponding doubly linked list node
        self.hashmap = {}
        # to create doubly linked list
        self.head.next = self.tail
        self.tail.prev = self.head
        self.capacity = capacity
    
    def addToHead(self, node) -> None:
        #TC: O(1)
        node.next = self.head.next
        node.prev = self.head
        self.head.next = node
        node.next.prev = node
    
    def removeNode(self, node) -> None:
        #TC : O(1)
        node.next.prev = node.prev
        node.prev.next = node.next

    def get(self, key: int) -> int:
        #TC: O(1)
        if not key in self.hashmap:
            return -1
        node = self.hashmap[key]
        self.removeNode(node)
        self.addToHead(node)
        return node.val
        

    def put(self, key: int, value: int) -> None:
        #TC:O(1)
        # if key is already present, update the exisiting key value in hashmap, remove node from DLL, add current node in DLL
        if  key in self.hashmap:
            node = self.hashmap[key]
            node.val = value
            self.removeNode(node)
            self.addToHead(node)
        else:
            # if capacity is full, remove both from hashmap and linked list
            if self.capacity == len(self.hashmap):
                tail_prev = self.tail.prev
                self.removeNode(tail_prev)
                del self.hashmap[tail_prev.key]
            
            # if not present, create new node and add to the head and update hashmap
            new_node = Node(key, value)
            self.addToHead(new_node)
            self.hashmap[key] = new_node
        


# Your LRUCache object will be instantiated and called as such:
# obj = LRUCache(capacity)
# param_1 = obj.get(key)
# obj.put(key,value)
