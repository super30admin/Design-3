# Time Complexity : O(1)
# Space Complexity : O(1); user space - O(n)(hashmap) + O(n)(LL);
# Did this code successfully run on Leetcode : Yes
# Any problem you faced while coding this : No
#
#
# Your code here along with comments explaining your approach

# creating a DlLinkedList Node class with prev, next, key, value as it's attributes.
class DlLinkedList:
    def __init__(self, key=0, value=0):
        self.key = key
        self.val = value
        self.next = None
        self.prev = None


class LRUCache:
    # creating dummy head
    # creating dummy tail to access last node in the linked list if capacity is full
    # pointing next of dummy to tail and prev of tail to dummy
    # creating a dictionary with key as keys and node as values.
    def __init__(self, capacity: int):
        self.capacity = capacity
        self.dummy = DlLinkedList()
        self.tail = DlLinkedList()
        self.dummy.next = self.tail
        self.tail.prev = self.dummy
        self.dictu = {}

    # inserting at the front of the linked list
    def insertAtBeginning(self, node):
        node.next = self.dummy.next
        self.dummy.next = node
        node.prev = self.dummy
        node.next.prev = node

    # removing node from the linked list.
    def remove(self, node):
        node.prev.next = node.next
        node.next.prev = node.prev

    def get(self, key: int) -> int:
        # if key is not in dictu, which means it is also not in linked list, simply return -1.
        if key not in self.dictu:
            return -1
        # if present remove that node and insert it at the beginning of the linked list
        # return it's val
        self.remove(self.dictu[key])
        self.insertAtBeginning(self.dictu[key])
        return self.dictu[key].val

    def put(self, key: int, value: int) -> None:
        # if key is not present in the dictu
        # if capacity is not full create a new_node and add it to both dictu and linkedlist
        # else if capacity is full, delete the last node in the linked list --> accessing it through tail
        # also delete it in dictu and then add the new_node to the beginning of the linked list
        if key not in self.dictu:
            node = DlLinkedList(key, value)
            if len(self.dictu) >= self.capacity:
                del self.dictu[self.tail.prev.key]
                self.remove(self.tail.prev)
            self.dictu[key] = node
            self.insertAtBeginning(node)
        # if key is present in the dictionary, which means it is also present in the linked list
        # just change it's value by accessing it through dictu
        # remove that node and move that node to the beginning of the linked list
        else:
            self.dictu[key].val = value
            self.remove(self.dictu[key])
            self.insertAtBeginning(self.dictu[key])


# Your LRUCache object will be instantiated and called as such:
obj = LRUCache(2)
obj.put(2, 2)
obj.put(1, 1)
print(obj.get(1))
obj.put(3, 3)
print(obj.get(2))
obj.put(4, 4)
print(obj.get(1))
print(obj.get(3))
print(obj.get(4))
