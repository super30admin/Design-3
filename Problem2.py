'''
1. For maintaining an LRU, we use a LinkedList and a Hashmap. Hashmap will have keys as keys and values as pointers to the nodes of LL.
2. Each entry in LRU Cache would be a node in the Linkedlist. We ensure get and put are done in O(1) using Linkedlist's O(1) modification TC.
3. There are two nodes to the left and right to always point to the LRU and MRU. 

 TC: O(1) for get and put
 SC: O(n) for hashmap and linkedlist
'''

class Node:
    def __init__(self,key,val):
        self.key = key
        self.val = val
        self.prev = self.next = None

class LRUCache:

    def __init__(self, capacity: int):
        self.left, self.right = Node(0,0), Node(0,0)
        self.left.next , self.right.prev = self.right, self.left
        
        self.cache = {}
        self.cap = capacity

    # Remove node from the list
    def remove(self, node: Node) -> None:
        prev, next = node.prev, node.next
        prev.next, next.prev = next, prev
    
    # Insert node at right
    def insert(self, node: Node) -> None:
        prev, next = self.right.prev, self.right
        prev.next = next.prev = node
        node.next,node.prev = next,prev


    def get(self, key: int) -> int:
        if key in self.cache:
            self.remove(self.cache[key])
            self.insert(self.cache[key])
            return self.cache[key].val    
        return -1

    def put(self, key: int, value: int) -> None:
        if key in self.cache:
            self.remove(self.cache[key])
        self.cache[key] = Node(key,value)
        self.insert(self.cache[key])
        if len(self.cache) > self.cap:
            lru = self.left.next
            self.remove(lru)
            del self.cache[lru.key]



# LRUCache object will be instantiated and called as such:
# obj = LRUCache(capacity)
# param_1 = obj.get(key)
# obj.put(key,value)