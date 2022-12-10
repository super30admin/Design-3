#Time complexity: Get, Put, initialize -> O(1)
#Space complexity: Storage -> O(capacity)

#Accepted on Leetcode

#Approach
#Use a doubly linked list with key, val, next and prev -> Maintain a hashMap with key:[Node] as the key value pairs
#New elements or newly touched element put at the head of the linked list while least recently will always bubble up to the tail
#Use Head and Tail dummy nodes to handle edge cases gracefully


#Doubly Linked List Node
class Node:
    def __init__(self, key, val, nextNode = None, prevNode = None):
        self.key = key
        self.val = val
        self.next = nextNode
        self.prev = prevNode

class LRUCache:

    #Most recently used at the head, least recently used at the tail
    def __init__(self, capacity: int):
        self.capacity = capacity
        self.head = Node(-1, -1) #dummy head
        self.tail = Node(-1, -1) #dummy tail
        self.head.next = self.tail
        self.tail.prev = self.head
        self.hashMap = {}

    def get(self, key: int) -> int:
        #check if key exists
        if key in self.hashMap:
            node = self.hashMap[key]
            #remove
            self.remove(node)
            #add to head - most recently used
            self.addToHead(node)
            #return val
            return node.val
        else:
            return -1

    #Valid call guaranteed
    def remove(self, node):
        node.next.prev = node.prev
        node.prev.next = node.next

    def addToHead(self, node):
        node.prev = self.head
        node.next = self.head.next
        self.head.next = node
        node.next.prev = node
        

    def put(self, key: int, value: int) -> None:
        #check if key exists
        if key in self.hashMap:
            node = self.hashMap[key]
            #update value
            node.val = value
            #remove node
            self.remove(node)
            #add to head - make it most recently used
            self.addToHead(node)
        else:
            #check hashmap length
            if len(self.hashMap) == self.capacity:
                nodeLeastRecent = self.tail.prev
                #remove least recently used
                self.remove(nodeLeastRecent)
                del self.hashMap[nodeLeastRecent.key]
            #add new node to head
            newNode = Node(key, value)
            self.hashMap[key] = newNode
            self.addToHead(newNode)


        


# Your LRUCache object will be instantiated and called as such:
# obj = LRUCache(capacity)
# param_1 = obj.get(key)
# obj.put(key,value)