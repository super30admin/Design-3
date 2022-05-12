'''
Accepted on leetcode(146)
Time - O(1), space - O(N), where N is the capacity of cache.
Here , we used two datastructures double linkedList and hashMap for achieve get and put operation in O(1)  Time. Put operation is always at the front of linked list, so it is O(1) but to make get also O(1) we used hashmap as in while getting the node value we use hasmap to get the value.
'''


# To implement double linked list we need a Node, created Node.
class Node:
    def __init__(self, key, val):
        self.key = key
        self.val = val
        self.prev = None
        self.next = None


class LRUCache:
    # Here, initialized all the pointers in double linked list and also map and size are initialized.
    def __init__(self, capacity: int):
        self.head = Node(None, None)
        self.tail = Node(None, None)
        self.head.next = self.tail
        self.tail.prev = self.head
        self.head.prev = None
        self.tail.next = None
        self.size = capacity
        self.map = {}

    '''For get operation the series of steps are:
    1. check if the key exists in hashmap, if yes save the node in a variable. If no return -1.
    2. If the key exists then check for that node in linked list and delete it.
    3. Then add the same node at the front of linked list as it is most recently accessed one now.
    4. Then return the node.val
    '''

    def get(self, key: int) -> int:

        if key in self.map:
            node = self.map[key]
            self.deleteNode(node)
            self.addNode_front(node)
            return node.val
        return -1

    ''' For put operation the series of steps are:
    1. Check if the key already exists in hashmap if so then we just have to change the value and make that node most recent one. For that we remove node and add it to the front with new value and also add the node to hashmap.
    2. If the length of hashmap > size of LRU cache, then, we should remove the element near tail as it is least recently used and delete that key in hashmap. then add the new node to linkedlist and hashmap.
    3. If there is no space or key issues then simply add the node at the front of linkedlist and hashmap.
    '''

    def put(self, key: int, value: int) -> None:

        if key in self.map:
            self.deleteNode(self.map[key])
        elif len(self.map) >= self.size:
            node = self.tail.prev
            self.deleteNode(node)
            del self.map[node.key]
        node = Node(key, value)
        self.addNode_front(node)
        self.map[key] = node

    ''' To add node at front , the series of steps are:
    1. first assign head.next to new node.next
    2. then, assign node to head.next. Now next is done.
    3. Then assign head to newnode.prev
    4. then assign node as previous of next node.
    '''

    def addNode_front(self, node):
        node.next = self.head.next
        self.head.next = node
        node.prev = self.head
        node.next.prev = node

    '''
    For delete we have 2 steps :
    1. assign deletenode.next to deletenode's previous.next
    2. assign deletenode.prev to deletenode's next.previous
    '''

    def deleteNode(self, node):
        node.prev.next = node.next
        node.next.prev = node.prev

# Your LRUCache object will be instantiated and called as such:
# obj = LRUCache(capacity)
# param_1 = obj.get(key)
# obj.put(key,value)