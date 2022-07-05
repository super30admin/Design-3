"""
Leetcode-https://leetcode.com/problems/lru-cache/ (submitted)
TC- O(1), O(1) User space and O(2N) design space
Challenges- Coming up with a most efficient design solution
Lecture-https://youtu.be/WsIi8VGX5s0
FAQ-
Why linkedlist?
If we use array, we will need to shift elements in it as they are visited which will be O(N), our
LL implementation can do it in O(1)

Why doubly linked list?
When performing delete and add operation, we will have to go through the


NOTES-
This is how ordered list in python is implemented.


Design a data structure that follows the constraints of a Least Recently Used (LRU) cache.

Implement the LRUCache class:

LRUCache(int capacity) Initialize the LRU cache with positive size capacity.
int get(int key) Return the value of the key if the key exists, otherwise return -1.
void put(int key, int value) Update the value of the key if the key exists. Otherwise, add the key-value pair to the
cache. If the number of keys exceeds the capacity from this operation, evict the least recently used key.
The functions get and put must each run in O(1) average time complexity.


Example 1:
Input
["LRUCache", "put", "put", "get", "put", "get", "put", "get", "get", "get"]
[[2], [1, 1], [2, 2], [1], [3, 3], [2], [4, 4], [1], [3], [4]]
Output
[null, null, null, 1, null, -1, null, -1, 3, 4]

Explanation
LRUCache lRUCache = new LRUCache(2);
lRUCache.put(1, 1); // cache is {1=1}
lRUCache.put(2, 2); // cache is {1=1, 2=2}
lRUCache.get(1);    // return 1
lRUCache.put(3, 3); // LRU key was 2, evicts key 2, cache is {1=1, 3=3}
lRUCache.get(2);    // returns -1 (not found)
lRUCache.put(4, 4); // LRU key was 1, evicts key 1, cache is {4=4, 3=3}
lRUCache.get(1);    // return -1 (not found)
lRUCache.get(3);    // return 3
lRUCache.get(4);    // return 4


Constraints:
1 <= capacity <= 3000
0 <= key <= 104
0 <= value <= 105
At most 2 * 105 calls will be made to get and put.
"""

"""
*most optimized*
Ideation- Doubly linked list w/ Hashmap O(1) TC, O(1) User space and O(2N) design space

Few key things we need to pay attention than the basic understanding of the problem is that, the problem is about 
replacing the least visited node. So to solve this, we can either do it with an array or we can used linked list so
we don't have to move all the elements as the elements move. 

Now, how we implement is the linked list? 
Scenario 1: Get existing element O(1)
To do so, we again have to option - traverse list and look for the element and return it's value or to maintain a 
hashmap <key>:<nodes address> which will be O(1).
We will also have to move this node to the front of the list to make it recently visited.

Scenario 2: put operation on existing element O(1)
We will get the node from the hashmap in O(1) and change it's value.

Scenario 3: put operation on new key (capacity not full) O(1)
We will simply add the new element in the front of the linked list.

Scenario 4: put operation on new key (capacity full) O(1)
Delete the last node and insert first node. To do so, we can traverse to the second last element of the list and remove
the last element in O(N), or we can maintain a tail which will be more optimized O(1). So, we should maintain a tail.
Now, we will also need to delete the node from the hashmap. Since, we delete node using the tail, we will need a key in
the tail to do so. That's why we will create a node object - Node<key><value>

NOTE- 
1. Use dummy head tail to not worry about corner cases.
2. Maintain current capacity
"""


# LinkedList node class
class Node:
    def __init__(self, key, val):
        self.key = key
        self.val = val
        self.next = None
        self.prev = None


class LRUCache:

    def __init__(self, capacity: int):
        # dummy linked list
        self.head = Node(-1, -1)
        self.tail = Node(-1, -1)
        self.head.next = self.tail
        self.tail.prev = self.head

        self.hashmap = {}
        self.capacity = capacity
        self.currCapacity = 0

    def addInFront(self, node):
        node.prev = self.head
        node.next = self.head.next
        self.head.next = node
        node.next.prev = node

    def unLinkNode(self, node):
        node.prev.next = node.next
        node.next.prev = node.prev

    def get(self, key: int) -> int:
        # key does not exist
        if key not in self.hashmap:
            return -1
        # if key exists - move it to most recently visited and return its value
        else:
            # unlink target node from its place
            target = self.hashmap[key]
            self.unLinkNode(target)

            # add it to front of the list
            self.addInFront(target)
            return target.val

    def put(self, key: int, value: int) -> None:
        # scenario: already existing key
        if key in self.hashmap:
            self.hashmap[key].val = value

            # unlink from its current position and add it to front
            self.unLinkNode(self.hashmap[key])
            self.addInFront(self.hashmap[key])

        # scenario: operation on new element
        else:
            # scenario: operation on new element (under capacity)
            if self.currCapacity < self.capacity:
                newNode = Node(key, value)
                # make it recently visited
                self.addInFront(newNode)
            # scenario: operation on new element (full capacity)
            else:
                # remove last element from hashmap and LL
                lastNode = self.tail.prev
                del self.hashmap[lastNode.key]
                secondLastNode = self.tail.prev.prev
                secondLastNode.next = self.tail
                self.tail.prev = secondLastNode
                # reduce capacity since the node is removed
                self.currCapacity -= 1

                # put the new node as recently visited
                newNode = Node(key, value)
                self.addInFront(newNode)

            self.hashmap[key] = newNode
            # increase capacity since new element added
            self.currCapacity += 1

# Your LRUCache object will be instantiated and called as such:
# obj = LRUCache(capacity)
# param_1 = obj.get(key)
# obj.put(key,value)
