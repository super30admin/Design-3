# Time Complexity : O(1) for both functions
# Space Complexity : O(Capacity)
# Did this code successfully run on Leetcode : Yes
# Any problem you faced while coding this : No


# Your code here along with comments explaining your approach



class ListNode:
    def __init__(self, key, val):
        self.key = key
        self.val = val
        self.next = None
        self.prev = None
        

'''Main Idea'''
# A HashMap that has key to listnode reference
# A doubly linked list that keeps track of most recent on head and least recent at tails
class LRUCache:
    def __init__(self, capacity: int):
        self.cache = {}
        self.capacity = capacity
        
        self.head = ListNode(-1, -1) # creating sentinel head
        self.tail = ListNode(-1, -1) # creating sentinel tail
        self.head.next = self.tail # attaching them to each other
        self.tail.prev = self.head # this makes handling null nodes very convenient

    
    def append_to_head(self, node):
        node.next = self.head.next
        node.prev = self.head
        self.head.next = node
        node.next.prev = node
    
    def pop_from_tail(self):
        if self.tail.prev == self.head:
            return
        curr = self.tail.prev
        self.tail.prev = self.tail.prev.prev
        self.tail.prev.next = self.tail
        return curr
    
    def remove_node(self, node):
        node.next.prev = node.prev
        node.prev.next = node.next
        
        
    def get(self, key: int) -> int:
        if key not in self.cache:
            return -1
        
        # getting value and adding to head because this is most recent now
        node = self.cache[key]
        self.remove_node(node)
        self.append_to_head(node)
        return node.val
        
        

    def put(self, key: int, value: int) -> None:
        '''Whenever a key is used, we have to remove it from DLL and add it to head'''
        if key in self.cache:
            # updating value and adding to head because this is most recent now
            node = self.cache[key]
            self.remove_node(node)
            self.append_to_head(node)
            node.val = value
        
        else:
            newNode = ListNode(key, value) # make new node
            if len(self.cache) == self.capacity: # not maintaining size, can just use len of hashmap
                # evicting from tail
                node = self.pop_from_tail()
                del self.cache[node.key]
                #inserting new
                self.cache[key] = newNode
                self.append_to_head(newNode)
            else:
                # inserting new
                self.cache[key] = newNode
                self.append_to_head(newNode)
        
        
                
            
        
            
        


# Your LRUCache object will be instantiated and called as such:
# obj = LRUCache(capacity)
# param_1 = obj.get(key)
# obj.put(key,value)