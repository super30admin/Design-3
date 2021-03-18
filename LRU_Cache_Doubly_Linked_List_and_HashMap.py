# Created by Aashish Adhikari at 11:33 AM 2/4/2021

'''
Time Complexity:
put(): O(1)
get(): O(1)

Space Complexity:
put(): O(1)
get(): O(1)
'''

class ListNode(object):
    def __init__(self):
        self.key = None
        self.val = None
        self.next = None
        self.prev = None

class LRUCache(object):

    def __init__(self, capacity):
        """
        :type capacity: int
        """
        self.hm = {}
        self.head = ListNode()
        self.tail = ListNode()

        self.head.next = self.tail
        self.tail.prev = self.head

        self.capacity = capacity


    def get(self, key):
        """
        :type key: int
        :rtype: int
        """

        if key not in self.hm:
            return -1

        # increase the priority and return the value of the key

        # remove from current position
        temp = self.hm[key]
        temp.next.prev = temp.prev
        temp.prev.next = temp.next

        # add to the end
        temp.prev = self.tail.prev
        self.tail.prev.next = temp
        temp.next = self.tail
        self.tail.prev = temp

        return temp.val


    def put(self, key, val):
        """
        :type key: int
        :type value: int
        :rtype: None
        """



        if key not in self.hm:



            # before adding a new node, remove one Least Recently Used node
            # remove the least recently used node from the hashmap
            if len(self.hm) == self.capacity:
                lru_node = self.head.next

                self.head.next = lru_node.next
                lru_node.next.prev = self.head

                del(self.hm[lru_node.key])

            # now add the new node
            new_node = ListNode()
            new_node.key = key
            new_node.val = val

            new_node.prev = self.tail.prev
            self.tail.prev.next = new_node
            self.tail.prev = new_node
            new_node.next = self.tail

            self.hm[key] = new_node



        else:
            self.hm[key].val = val

            # remove from current position
            temp = self.hm[key]
            temp.next.prev = temp.prev
            temp.prev.next = temp.next

            # add to the end
            temp.prev = self.tail.prev
            self.tail.prev.next = temp
            temp.next = self.tail
            self.tail.prev = temp







# Your LRUCache object will be instantiated and called as such:
# obj = LRUCache(capacity)
# param_1 = obj.get(key)
# obj.put(key,value)