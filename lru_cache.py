# // Time Complexity : O(n)
# // Space Complexity : O(n)
# // Did this code successfully run on Leetcode : Yes
# // Any problem you faced while coding this : Nope


# // Your code here along with comments explaining your approach

class Node:
    def __init__(self,key,value):
        self.key=key
        self.value=value
        self.prev=self.next=None

class LRUCache:

    def __init__(self, capacity: int):
        self.capacity=capacity
        self.cache={}
        self.head=Node(-1,-1)
        self.tail=Node(-1,-1)
        
        self.head.next=self.tail
        self.tail.prev=self.head

        
    def remove(self,node):
        node.prev.next = node.next
        node.next.prev = node.prev
        
    def insert(self,node):
        node.next = self.head.next
        node.prev = self.head
        self.head.next = node
        node.next.prev = node
        
        
    def get(self, key: int) -> int:
        if key in self.cache:
            self.remove(self.cache[key])
            self.insert(self.cache[key])
            return self.cache[key].value
        
        return -1

    def put(self, key: int, value: int) -> None:
        if key in self.cache:
            node=self.cache[key]
            node.value=value
            self.remove(node)
            self.insert(node)
        
        else:
            if len(self.cache)>=self.capacity:
                #Remove the LRU
                tailPrev=self.tail.prev
                #Remove from the ll and the hasmap
                self.remove(tailPrev)
                del self.cache[tailPrev.key]
            
            #Create a new node and add to both the ll and the hashmap
            new= Node(key,value)
            self.insert(new)
            self.cache[key]=new
            
        
        
            


# Your LRUCache object will be instantiated and called as such:
# obj = LRUCache(capacity)
# param_1 = obj.get(key)
# obj.put(key,value)