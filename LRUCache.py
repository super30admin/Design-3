#TC: O(1)
#SC: O(1)
class Node(object):


    def __init__(self, key, value):
        self.key = key
        self.value = value
        self.next = None
        self.prev = None


class LRUCache(object):

    def __init__(self, capacity):

        self.capacity = capacity

        self.head = Node(-1, -1)        
        self.tail = Node(-1, -1)       
        self.head.next = self.tail      
        self.tail.prev = self.head     

        self.keyNodeMap = {}            

    def __printCache(self):


        currNode = self.head.next
        while (currNode != self.tail):
            print(currNode.key, currNode.value)
            currNode = currNode.next

        print('---DONE---')

    def __insertAtHead(self, node):

        node.prev = self.head
        node.next = self.head.next

        self.head.next = node
        node.next.prev = node

        return

    def __remove(self, node):

        node.prev.next = node.next
        node.next.prev = node.prev

        return

    def get(self, key):

        if (key not in self.keyNodeMap):       
            return -1
        else:                           
            node = self.keyNodeMap[key]      
            self.__remove(node)              
            self.__insertAtHead(node)       
            return node.value                  

    def put(self, key, value):

        if (key in self.keyNodeMap):            
            self.keyNodeMap[key].value = value
            node = self.keyNodeMap[key]          

            self.__remove(node)                   
            self.__insertAtHead(node)             

        else:
            if (len(self.keyNodeMap) == self.capacity): 
                node = self.tail.prev                 
                self.__remove(node)                    
                self.keyNodeMap.pop(node.key)         

            self.keyNodeMap[key] = Node(key, value)     
            node = self.keyNodeMap[key]               
            self.__insertAtHead(node)                   

        return

import itertools

gen = iter([1,2,3])
peek = next(gen)
print(list(itertools.chain([peek], gen)))