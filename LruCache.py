#// Time Complexity : O(1) 
# // Space Complexity : O(n) 
# // Did this code successfully run on Leetcode : yes
# // Any problem you faced while coding this : no because i saw the class video and then did the problem.

class  Node:
    def __init__(self,key,value):
        self.key = key
        self.value = value
        self.next = None
        self.prev = None

class LRUCache:

    def __init__(self, capacity: int):
        self.capacity = capacity
        self.dicti = {}
        self.head = Node(-1,-1)
        self.tail = Node(-1,-1)
        self.head.next = self.tail
        self.tail.prev = self.head

    def remove(self,node):
        node.prev.next = node.next
        node.next.prev = node.prev
        node.prev = None
        node.next = None

    def insert(self,node):
        node.next = self.head.next
        self.head.next.prev = node
        self.head.next = node
        node.prev=self.head


    def get(self, key: int) -> int:
        if key not in self.dicti:
            return -1 
        self.remove(self.dicti[key])
        self.insert(self.dicti[key])
        return self.dicti[key].value
        

    def put(self, key: int, value: int) -> None:
     
        if key in self.dicti:
            self.dicti[key].value = value
            self.remove(self.dicti[key])
            self.insert(self.dicti[key])
        else:
            if len(self.dicti) == self.capacity:
                del self.dicti[self.tail.prev.key]
                self.remove(self.tail.prev)
                
            node = Node(key,value)
            self.insert(node)
            self.dicti[key] = node
            


        


# Your LRUCache object will be instantiated and called as such:
# obj = LRUCache(capacity)
# param_1 = obj.get(key)
# obj.put(key,value)