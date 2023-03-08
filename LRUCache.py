#All TC passed on leetcode


class Node:
    def __init__(self, key, val):
        self.val = val
        self.key = key
        self.next = None
        self.prev = None

class LRUCache:

    def __init__(self, capacity: int):
        self.head = Node(0,0)
        self.tail = Node(0,0)
        self.head.next = self.tail
        self.tail.prev = self.head
        self.map = {}
        self.capacity = capacity
        


    #Time complexity - O(1)
    #Space complexity - O(N) - N is capacity occupied by linked list and dictionary
    def get(self, key: int) -> int:
        if key not in self.map:
            return -1
        node = self.map[key]
        prevNode = node.prev
        nextNode = node.next
        prevNode.next = nextNode
        nextNode.prev = prevNode
        
        headNextNode = self.head.next
        self.head.next = node
        node.prev = self.head
        node.next = headNextNode
        headNextNode.prev = node

        return node.val
        

    #Time complexity - O(1)
    #Space complexity - O(N) - N is capacity occupied by linked list and dictionary
    def put(self, key: int, value: int) -> None:
        if key in self.map:
            #delete the node and add it new val node at head
            node = self.map[key]
            node.val = value
            prevNode = node.prev
            nextNode = node.next
            prevNode.next = nextNode
            nextNode.prev = prevNode

            headNextNode = self.head.next
            self.head.next = node
            node.prev = self.head
            node.next = headNextNode
            headNextNode.prev = node

        else:
            if len(self.map) == self.capacity:
                #delete node at tail and add new node at head
                node = self.tail.prev
                self.tail.prev = node.prev
                node.prev.next = self.tail
                node.prev = None
                node.next = None
                self.map.pop(node.key)

                newNode = Node(key, value)
                headNextNode = self.head.next
                self.head.next = newNode
                newNode.prev = self.head
                newNode.next = headNextNode
                headNextNode.prev = newNode
                self.map[key] = newNode

            else:
                #add new node at head
                newNode = Node(key, value)
                headNextNode = self.head.next
                self.head.next = newNode
                newNode.prev = self.head
                newNode.next = headNextNode
                headNextNode.prev = newNode
                self.map[key] = newNode



        


# Your LRUCache object will be instantiated and called as such:
# obj = LRUCache(capacity)
# param_1 = obj.get(key)
# obj.put(key,value)