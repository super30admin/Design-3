'''
Approach:

Using a Doubly linkedList with head and tail, and HashMap

LL - we maintain last node as the least recently used and 1st node would be recently used
HashMap - {key: address of the node}

Whenever we have put we have 2 cases
1. key present in hashMap
    1. get the node address from hashMap
    2. update the data of the node
    3. remove form the linked List (removeNode)
    4. append to the start of the linked list (insertFirst)
2. key not present in hashMap
    create a newNode
    insert in hashMap
    1. if capacity == 0 (ie it is full)
        1. remove the last node from the list
        2. remove the last node from the hashMap
    2. if capacity > 0 
        1. reduce capacity

    insert the new node at the start of the list

Whenever we have get we have 2 cases
1. key present in hashMap
    1. get the node address from hashMap store the value into a variable
    2. remove the node from the LL
    3. add the node to the start of the list
    4. return the node's data[1]

2. key present not in hashMap
    return -1

Time Complexity

get O(1)
put O(1)

'''

class DLL:
    def __init__(self):
        self.head = DLLNode((-1,-1), None, None)
        self.tail = DLLNode((-1,-1), None, None)
        self.head.next = self.tail
        self.tail.next = self.head
    
    def insertFirst(self, node):
        temp = self.head.next
        node.next = temp
        node.prev = self.head
        self.head.next = node
        temp.prev = node
        
    
    def removeNode(self, node):
        prev = node.prev
        next = node.next
        prev.next = next
        next.prev = prev
        
    

class DLLNode:
    def __init__(self, data=None, next=None, prev=None):
        self.data = data
        self.next = next
        self.prev = prev
        

class LRUCache:

    def __init__(self, capacity: int):
        self.capacity = capacity
        self.hashMap = {} #key:address
        self.head = None
        self.obj = DLL()
        

    def get(self, key: int) -> int:
        if key in self.hashMap:
            resNode = self.hashMap[key]
            ans = resNode.data[1] 
            self.obj.removeNode(resNode)
            self.obj.insertFirst(resNode)
            return ans
        return -1
        
        

    def put(self, key: int, value: int) -> None:
        if key in self.hashMap:
            resNode = self.hashMap[key]
            resNode.data = (key,value)
            self.obj.removeNode(resNode)
            self.obj.insertFirst(resNode)
        else:
            newNode = DLLNode((key,value), None, None)
            if self.capacity == 0:
                delNode = self.obj.tail.prev
                self.obj.removeNode(delNode)
                self.hashMap.pop(delNode.data[0])
                self.hashMap[key] = newNode
            else:
                newNode = DLLNode((key,value), None, None)
                self.hashMap[key] = newNode
                
                self.capacity -= 1
            self.obj.insertFirst(newNode)
                


# Your LRUCache object will be instantiated and called as such:
# obj = LRUCache(capacity)
# param_1 = obj.get(key)
# obj.put(key,value)