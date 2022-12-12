'''
Time Complexity -->
O(1) avg time for all the operations
Spacec Complexity -->
O(n) for all the nodes since we are using hashmap and dd linkedList
'''
class Node:   #Double linked list
    def __init__(self, key = -1, value = -1):
        self.key = key
        self.value = value
        self.next = None
        self.prev = None

class LRUCache:

    def __init__(self, capacity: int):
        self.capacity = capacity  #maintain teh capacity
        self.map = {}  #this is to store the data in hashmap which can be used for search operations
        #self.dblist = []   #this is to store the data in doubly linked list
        self.head = Node()
        self.tail = Node()
        self.head.next = self.tail
        self.tail.prev = self.head
        


    def addAtBeg(self, node):
        self.head.next.prev = node
        node.next = self.head.next
        node.prev = self.head
        self.head.next = node
    
    def delLRU(self):
        node = self.tail.prev
        node.prev.next = node.next
        node.next.prev = node.prev
        k = node.key
        del(node)
        del(self.map[k])
        


    def get(self, key: int) -> int:
        if key not in self.map:
            return -1
        else:

            #put this in front after head

            node = self.map[key]
            node.prev.next = node.next
            node.next.prev = node.prev
            self.addAtBeg(node)

            return node.value

        

    def put(self, key: int, value: int) -> None:
        if key in self.map:
            node = self.map[key]
            node.prev.next = node.next
            node.next.prev = node.prev
            del(node)
            del(self.map[key])
            newNode = Node(key,value)

            self.addAtBeg(newNode)
            self.map[key] = newNode

            return newNode.value 
            
        else:
            if self.capacity==0:
                self.delLRU()
                newNode = Node(key, value)
                self.addAtBeg(newNode)
                self.map[key] = newNode
            else:
                newNode = Node(key, value)
                self.addAtBeg(newNode)
                self.map[key] = newNode
                self.capacity-=1
                #print(self.capacity)
        #print(self.map)


        


        

                
                


            
        


        


# Your LRUCache object will be instantiated and called as such:
# obj = LRUCache(capacity)
# param_1 = obj.get(key)
# obj.put(key,value)