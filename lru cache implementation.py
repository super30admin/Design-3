# LRU Cache
#
# eliminate least recently used
# 2 operations: get, put
#
# get: if key present → return value (and mark it most recent), else return -1
# put: 1) key exists → update value, move to most recent side
#      2) fresh key  → if capacity full, remove LRU item, then insert and mark most recent
#
# to maintain order of recency → use a linked list of nodes (key, value)
#
# hurdle 1: finding a key is O(n) linear search through LL
#   fix: use a dict → O(1) to check if key exists
#
# hurdle 2: dict maps key → value, but we still need to physically find the node in LL to move it
#   fix: map key → node reference (address of node), so we jump to it in O(1)
#
# hurdle 3: once we have node ref, removing it from a singly LL is still O(n)
#   because to remove a node we need its previous node, and we have to traverse to find it
#   fix: use doubly LL → each node has prev and next, so removal is O(1)
#     removal: node.prev.next = node.next  ,  node.next.prev = node.prev
#     (no need to clear node's own prev/next pointers but we do it for cleanliness)
#
# moving to most recent (add to head):
#   1) node.next = head.next
#   2) node.prev = head
#   3) head.next.prev = node   ← old first node points back to node
#   4) head.next = node
#
# use dummy head and tail sentinels so every real node always has valid prev/next
# layout: HEAD <-> [MRU ... LRU] <-> TAIL

class LRUCache:
    class Node:
        def __init__(self,key,value):
            self.key = key
            self.value= value
            self.prev=None
            self.next=None

    def __init__(self, capacity: int):
        self.map={}
        self.capacity = capacity
        self.head= self.Node(-1,-1) #dummy head
        self.tail=self.Node(-1,-1)  #dummy tail
        #make initial connections
        self.head.next=self.tail
        self.tail.prev=self.head 

    def removeNode(self,node):
        node.next.prev=node.prev
        node.prev.next=node.next
        node.prev=None
        node.next=None

    def addtoHead(self,node):
        node.prev=self.head
        node.next=self.head.next
        node.next.prev=node
        self.head.next=node

    def get(self, key: int) -> int:
        #map doesnt contain the key
        if key not in self.map:
            return -1
        #we get corresponding node ref, because we are maintaing integer and node in map
        node= self.map[key]  #ie node ref.
        self.removeNode(node)
        self.addtoHead(node)
        return node.value
        
    def put(self, key: int, value: int) -> None:
        #2cases: 1) node already in map so just replace value and move it to most recent side
        #2) node is fresh, if len(map)==capacity: ie remove least recently used node from the LL and also the map and make space to fresh node. ie add fresh node to both LL(to head) and map
        if key in self.map:
            node=self.map[key]
            node.value = value
            self.removeNode(node)
            self.addtoHead(node)
        else:
            if len(self.map)==self.capacity:
                lastNode=self.tail.prev
                self.removeNode(lastNode)
                del self.map[lastNode.key]
            newNode=self.Node(key,value)
            self.addtoHead(newNode)
            self.map[key]=newNode

# Your LRUCache object will be instantiated and called as such:
# obj = LRUCache(capacity)
# param_1 = obj.get(key)
# obj.put(key,value)
