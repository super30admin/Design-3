"""
// Time Complexity :O(1) For all methods.
// Space Complexity :O(n) As DLL is used.
// Did this code successfully run on Leetcode : Need to design code as per LC
// Any problem you faced while coding this : NA

//Explanation:
Code was failing for test case on line number 136,137,and 139.
In LRU 2 is inserted after 1.So cache ={2,1} So while inserting 3, 2 should have been removed.
The catch here is at line number x, lr.get(1) access LRU cache again and it makes cache ={1,2}
Hence 2 should be remove. Handled this case later in get() by calling updateMostRecent

Read inline comments.
"""
class LRUCache:

    def __init__(self, capacity: int):
        self.cache = {}
        self.maxSize = capacity
        self.currentSize = 0
        self.listofMostRecent = DoublyLinkedList()

    """
    	if key not in cache-> cache miss
    	if key is in cache, update it to most recently accessed key before accessing it
    """
    def get(self, key: int) -> int:
        if key not in self.cache:
            return -1
        else:
            self.updateMostRecent(self.cache[key])
            return self.cache[key].value
    """
	   case1 -> cache miss
	   case2-> cache is full, remove least recently used
	   case3-> cache still has space, increase the cache size by 1
	   irespective of case2 or case3, insert value in cache
	   case4 -> if key already exists, update associated value
	   update most recent value with the latest inserted key-value
	"""
    def put(self, key: int, value: int) -> None:
        if key not in self.cache:
            if self.currentSize == self.maxSize:
                self.removeLRU()
            else:
                self.currentSize += 1
            self.cache[key] = DoublyLinkedListNode(key,value)
        else:
            self.replaceKey(key,value)
        self.updateMostRecent(self.cache[key])

    def replaceKey(self,key,value):
        if key not in self.cache:
            raise Error("Key not in cache")
        self.cache[key].value = value

    """
    	update the head of the DLL.
    """
    def updateMostRecent(self,node):
        self.listofMostRecent.setHeadTo(node)

    """
	   removes the least recently used key-value from the end of the DLL
	   i.e remove tail of the DLL
	   *need to delete least recently used key-value from the hashMap(cache) as well
	"""
    def removeLRU(self):
        keytoRemove = self.listofMostRecent.tail.key
        self.listofMostRecent.removeTail()
        del self.cache[keytoRemove]


class DoublyLinkedList:
    def __init__(self):
        self.head = None
        self.tail = None

    def setHeadTo(self,node):
        #print("*",node.value)
        if self.head == node:
            return
        elif self.head == None:
            self.head = node
            self.tail = node
        elif self.head == self.tail:
            self.tail.prev = node
            self.head = node
            self.head.next = self.tail
        else:
            if self.tail == node:
                self.removeTail()
            node.removeBindings()
            self.head.prev = node
            node.next = self.head
            self.head = node

    def printDLL(self):
        current = self.head
        while current:
            print(current.value,current.prev,current.next)
            current = current.next

    def removeTail(self):
        if self.tail is None:
            return
        if self.tail == self.head:
            self.head = None
            self.tail = None
            return
        self.tail = self.tail.prev
        self.tail.next = None

class DoublyLinkedListNode:
    def __init__(self,key,value):
        self.key = key
        self.value = value
        self.prev = None
        self.next = None

    def removeBindings(self):
        if self.prev is not None:
            self.prev.next = self.next
        if self.next is not None:
            self.next.prev = self.prev
        self.prev = None
        self.next = None


# Your LRUCache object will be instantiated and called as such:
# obj = LRUCache(capacity)
# param_1 = obj.get(key)
# obj.put(key,value)
if __name__ == "__main__":
  lr = LRUCache(2)
  lr.put(1, 1)
  lr.put(2, 2)
  #print(lr.cache,lr.listofMostRecent.head.value,lr.listofMostRecent.tail.value)
  print("1 =",lr.get(1))
  lr.put(3, 3)
  print("2 =",lr.get(2))
  lr.put(4, 4)
  print("1 =",lr.get(1))
  print("3 =",lr.get(3))
  print("4 =",lr.get(4))
