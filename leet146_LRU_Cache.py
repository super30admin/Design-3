# Solution

# // Time Complexity : Both Get and Put operations take O(1) time complexity
# // Space Complexity : O(capacity) since the double linked list and dictionary can have max size of capacity based on question
# // Did this code successfully run on Leetcode : Yes
# // Any problem you faced while coding this : Found difficult to move around nodes initially, though concept was totally clear
# So as per suggestion from S30, using addToHead and removeNode functions helps to work on linkedlist add/remove nodes easily


# // Your code here along with comments explaining your approach
# Approach is to use double linked list to save all the key/value as Nodes. This helps keep the time complexity O(1) for both
# get and put operations. If we have a request for either get/put we need to move that to head since that is the recently used
# node, so that means the node which is previous to tail is the least recently used element.

class LRUCache:
    class doubleLinkedList:
        def __init__(self,key=None,val=None,next=None,prev=None):
            self.key = key
            self.val = val
            self.next = next
            self.prev = prev

    def __init__(self, capacity: int):
        self.capacity = capacity
        self.keyDict = {}
        self.head = LRUCache.doubleLinkedList()
        self.tail = LRUCache.doubleLinkedList(None,None,None,self.head)
        self.head.next = self.tail
        self.len = 0
    
    def removeNode(self,node):
        node.prev.next = node.next
        node.next.prev = node.prev
    
    def addToHead(self,node):
        node.prev = self.head
        node.next = self.head.next
        self.head.next.prev = node
        self.head.next = node


    def get(self, key: int) -> int:
        if key in self.keyDict:
            keyNode = self.keyDict[key]
            self.removeNode(keyNode)
            self.addToHead(keyNode)
            return keyNode.val
        else:
            return -1

        

    def put(self, key: int, value: int) -> None:
        if key in self.keyDict:
            keyNode = self.keyDict[key]
            self.removeNode(keyNode)
            self.addToHead(keyNode)
            keyNode.val = value
        else:
            if self.len == self.capacity:
                del self.keyDict[self.tail.prev.key]
                self.removeNode(self.tail.prev)
                self.len -= 1

            self.len += 1
            newNode = LRUCache.doubleLinkedList(key,value)   
            self.addToHead(newNode)
            self.keyDict[key] = newNode


# Your LRUCache object will be instantiated and called as such:
if __name__ == "__main__":
    input = ["LRUCache","put","put","get","put","get","put","get","get","get"]
    inputVal = [[2],[1,1],[2,2],[1],[3,3],[2],[4,4],[1],[3],[4]]
    for i in range(len(input)):
        if input[i] == "LRUCache":
            capacity = inputVal[i]
            obj = LRUCache(capacity)
        elif input[i] == "put":
            obj.put(inputVal[i][0],inputVal[i][1])
        elif input[i] == "get":
            param_1 = obj.get(inputVal[i][0])
            print(param_1)
    