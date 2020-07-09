---------------------------------LRU Cache -------------------------------------
# Time Complexity : O(N) to remove element from queue and append at last
# Space Complexity : O(2N)
# Did this code successfully run on Leetcode: Yes
# Any problem you faced while coding this : No
# 
# Here I have used a dictionary and queue to implement LRU Cache. when I get a put request, I will check weather the key is present in dict or not. If present I will update value
# remove thet key from queue and append to the end. If not present, I will check the length of dict, if it is more than capacity, I will 
# pop a key from queue and remove that element from dict and add new element and add that element key to queue. 
# For the get request, if we have the key in dict, we will return that value and move the key to the back of the queue.


class LRUCache:

    def __init__(self, capacity: int):
        self.d = {}
        self.queue = deque()
        self.c = capacity
        

    def get(self, key: int) -> int:
        if key in self.d:
            temp = self.d[key]
            self.queue.remove(key)
            self.queue.append(key)
            return temp
        else:
            return -1
        

    def put(self, key: int, value: int) -> None:
        
            
        if key in self.d:
            self.d[key] = value
            self.queue.remove(key)
            self.queue.append(key)
        else:
            if len(self.d)>=self.c:
                k = self.queue.popleft()
                del self.d[k]
                
            self.d[key] = value
            self.queue.append(key)
            
            

--------------------------------- LRU Cache -------------------------------------
# Time Complexity : O(1) 
# Space Complexity : O(2N)
# Did this code successfully run on Leetcode: Yes
# Any problem you faced while coding this : No
# 
# Here I have used double linked list which has key and value, next and prev pointers and a dict. Here when i get a put request. I will check the dict,
#if key is not present in the dict, I will create a node and append to the double linked list. If my dict is more than capacity, I will remove a node from my tail and remove that from my dict.
#Add a new key and value to linked list at the head and add it to the dict. If it is a get request, then I will check weather the key is present in dict or not, if yes i will return the node value.

class Node:
    def __init__(self, k,value):
        self.key = k
        self.val = value
        self.next = None
        self.prev = None
                

class LRUCache:

    def __init__(self, capacity: int):
        self.d = {}
        self.c = capacity
        self.head = Node(-1,-1)
        self.tail = Node(-1,-1)
        self.head.next = self.tail
        self.tail.prev = self.head
        

    def insertAtFront(self, node):
        node.prev = self.head
        node.next = self.head.next
        self.head.next = node
        node.next.prev = node
        
    
    def insertAtBack(self, node):
        node.next = self.tail
        node.prev = self.tail.prev
        self.tail.prev = node
        node.prev.next = node
    
    def deleteNode(self,node):
        node.prev.next = node.next
        node.next.prev = node.prev
        
    def get(self, key: int) -> int:
        if key in self.d:
            temp = self.d[key]
            self.deleteNode(temp)
            self.insertAtBack(temp)
            return temp.val
        else:
            return -1
        

    def put(self, key: int, value: int) -> None:
        node = Node(key, value)
        if key in self.d:
            self.deleteNode(self.d[key])
            self.d[key] = node
        else:
            if len(self.d)>=self.c:
                p = self.head.next
                self.head.next = p.next
                p.next.prev = self.head
                del self.d[p.key]
                
            self.d[key] = node
        self.insertAtBack(node)
            
        
 


# Your LRUCache object will be instantiated and called as such:
# obj = LRUCache(capacity)
# param_1 = obj.get(key)
# obj.put(key,value)