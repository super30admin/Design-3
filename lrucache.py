# // Time Complexity :O(1) all the operation
# // Space Complexity :O(n) n is the capacity
# // Did this code successfully run on Leetcode :yes
# // Any problem you faced while coding this :no


# // Your code here along with comments explaining your approach




class Node:
    def __init__(self,key,val):
        self.key=key
        self.val=val
        self.next=None
        self.prev=None
class LRUCache:

    def __init__(self, capacity: int):
        self.map={}
        self.capacity=capacity
        self.head=Node(-1,-1)
        self.tail=Node(-1,-1)
        self.head.next=self.tail
        self.tail.prev=self.head
    
        

    def get(self, key: int) -> int:
        if key in self.map.keys():
            
            node=self.map[key]
            self.remove(node)
            self.inserthead(node)
            return node.val
        return -1
        
    def inserthead(self,node):
        node.next=self.head.next
        node.prev=self.head
        
        self.head.next=node
        node.next.prev=node
    def remove(self,node):
       
        node.prev.next=node.next

        node.next.prev=node.prev
    def removetail(self,node):
        
        node.prev.next=node.next
        node.next.prev=node.prev
        node.next=None
        node.prev=None
        
        
        
        
        
        
        
        

    def put(self, key: int, value: int) -> None:
        if key in self.map.keys():
            node=self.map[key]
            node.val=value
            self.remove(node)
            self.inserthead(node)
        else:
            print(len(self.map))
            if self.capacity==len(self.map):
                node=self.map[self.tail.prev.key]
                print(node.val)
                print(node.key)
                
                self.remove(node)
                
                self.map.pop(node.key)

            nodenew=Node(key,value)
            self.map[key]=nodenew
            self.inserthead(nodenew)
            
        


# Your LRUCache object will be instantiated and called as such:
# obj = LRUCache(capacity)
# param_1 = obj.get(key)
# obj.put(key,value)