# Time Complexity : O(1) for both operations
# Space Complexity : O(capacity)
# Did this code successfully run on Leetcode : Yes 
# Any problem you faced while coding this : No

# Approach:

# Initial intuitions and the build up to final approach:

# Using Hashmap to store key-value pairs: Issues- How will we know ordering? How do we know priority?
# Solutions: Adding queue/linked list or another hashmap. 

# 1: Adding another hashmap to store key-priority pairs: However, when we add another element in first hashmap, we'll have to update all the priorities in second hashmap, making this approach expensive.
# get()-> O(n) and put()-> O(n), cause of updating priorities in both.

# 2: Queue: We'll put the pairs in the queue. But when queue's capacity is full, next put() operation would need to pop first element and add new item to queue making put()-> O(n). However, for the get() operation, we'd need to pop the elements from queue until we get the element and keep storing them in another queue. Then push the get element in the front of the queue making it an O(n) operation.

# 3: Singly Linked list: Head points to first, tail points to last element. Now, put() will be O(1) as we can move tail to point to new node and prev.next pointing to new node as well. We'll move head pointer ahead as well, to maintain the capacity. Our get() would need to iterate from head till that element and we'd need to remove the node for which we'll need the pointer at previous node. So, instead of making a singly linked list, we should make use of doubly linked list.

# 4: Doubly Linked list: Now, for get() when we reach at our element, we'll have a prev pointer. We can remove the node easily by doing prev.next = prev.next.next. However, we'll lose the pointer to the node that's being removed and we'll put it at the end with tail pointer pointing to it. But, get() is still O(n) as we'll have to traverse till that element. To eliminate traversing in O(n), we'll need to use hashmap along with it. In this hashmap, we'll store key-node pair where the stored node is just the reference to the linked list node. Now, rather than iterating over the linked list we can just access any node with the keys of hashmap and move the pointers acc. to remove node and add it to the end. This will make get() operation O(1). Also, we will initially have dummy head and tail nodes pointing to each other with values (0,0) and when put() is called, add the new node from the tail (tail.prev points to new node). 

class ListNode:
    def __init__(self, key, val):
        self.key = key
        self.val = val
        self.prev = None
        self.next = None
 
class LRUCache:

    def __init__(self, capacity: int):
        self.head = ListNode(-1, -1)
        self.tail = ListNode(-1, -1)
        self.head.next = self.tail
        self.tail.prev = self.head
        self.hmap = {}
        self.capacity = capacity

    def addNode(self, node):
        node.next = self.tail
        node.prev = self.tail.prev
        node.prev.next = node
        self.tail.prev = node
        
    def removeNode(self, node):
        before = node.prev
        after = node.next
        before.next = after
        after.prev = before
        
    def get(self, key: int) -> int:
        # node = ListNode(key, self.hmap[key])
        if key in self.hmap:
            node = self.hmap[key]
            self.removeNode(node)
            self.addNode(node)
            return node.val
        return -1
        
    def put(self, key: int, value: int) -> None:
        
        if key in self.hmap:
            self.removeNode(self.hmap[key])
        node = ListNode(key, value)
        self.addNode(node)
        
        self.hmap[key]= node
        if len(self.hmap)> self.capacity:
            temp = self.head.next
            self.removeNode(temp)
            del self.hmap[temp.key]
        
# Your LRUCache object will be instantiated and called as such:
# obj = LRUCache(capacity)
# param_1 = obj.get(key)
# obj.put(key,value)