# Created by Aashish Adhikari at 8:49 PM 2/3/2021

'''
Time Complexity:
get(): O(capacity)
put():O(capacity)

Space Complexity:
get():O(capacity)
put():O(capacity)
'''


class ListNode(object):
    def __init__(self, key, val):
        self.key, self.val,self.prev, self.next = key, val, None, None

class LRUCache(object):

    def __init__(self, capacity):
        self.head, self.tail, self.capacity = None, None, capacity

        # The tail with contain the most recent item i.e., highest priority.

    def replace(self, curr):
        curr.prev.next = curr.next
        curr.next.prev = curr.prev
        self.tail.next = curr
        curr.prev = self.tail
        curr.next = None
        self.tail = curr
        self.tail.next = None

    def get(self, key):

        if self.head is None:
            return -1
        elif self.head == self.tail:
            if self.head.key == key:
                return self.head.val
            else:
                return -1
        else:
            curr = self.head
            while curr is not None:
                if curr.key == key and curr == self.head:
                    temp =self.head
                    self.head = self.head.next
                    self.head.prev = None
                    self.tail.next = temp
                    temp.prev = self.tail
                    self.tail = temp
                    self.tail.next = None
                    return self.tail.val
                elif curr.key == key and curr == self.tail:
                    return self.tail.val
                elif curr.key == key:
                    self.replace(curr)
                    return self.tail.val
                else:
                    curr = curr.next
            return -1
    def put(self, key, val):

        if self.head is None:
            self.head = ListNode(key,val)
            self.tail = self.head
        elif self.head == self.tail:
            if self.head.key == key:
                self.head.val = val
            elif self.capacity == 1:
                self.head.key = key
                self.head.val = val
            else:
                new_node = ListNode(key,val)
                self.head.next = new_node
                new_node.prev = self.head
                self.tail = new_node
        else:
            ctr = 0
            curr = self.head
            KEY_FOUND = False
            while curr is not None:
                if curr.key == key and curr == self.head:
                    temp = self.head
                    self.head = self.head.next
                    self.head.prev = None
                    self.tail.next = temp
                    temp.prev = self.tail
                    self.tail = temp
                    self.tail.next = None
                    self.tail.val = val
                    KEY_FOUND = True
                    break
                elif curr.key == key and curr == self.tail:
                    curr.val = val
                    KEY_FOUND = True
                    break
                elif curr.key == key:
                    self.replace(curr)
                    self.tail.val = val
                    KEY_FOUND = True
                    break
                else:
                    pass
                ctr +=1
                curr = curr.next

            if not KEY_FOUND:
                new_tail = ListNode(key,val)
                self.tail.next = new_tail
                new_tail.prev = self.tail
                self.tail = new_tail
                if ctr == self.capacity:
                    self.head = self.head.next
                    self.head.prev = None