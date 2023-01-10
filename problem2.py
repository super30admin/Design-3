#time complexity: O(1)
#space complexity: O(capacity)
#ran on leetcode: Yes
#Use a double linked list and a hash map. The key in hashmap will be key and value will be address of the node in the double linked list. When key is accessed trough a get or put operation,  move this node to head. When full capacity is reached and a new key has to be inserted, remove the node in the tail and remove the corressponding hash table entry for that key as well.
class LinkedList:
    def __init__(self,val,key):
        self.left=None
        self.right=None
        self.val=val
        self.key=key
class LRUCache:

    def __init__(self, capacity: int):
        self.head=LinkedList(-1,-1)
        self.tail=LinkedList(-1,-1)
        self.head.right=self.tail
        self.tail.left=self.head
        self.capacity=capacity
        self.current=0
        self.D={}
        

    def get(self, key: int) -> int:
        if(key in self.D):
            temp=self.D[key]
            self.D[key].left.right=temp.right
            temp.right.left=temp.left
            temp.left=self.head
            temp.right=self.head.right
            self.head.right=temp
            temp.right.left=temp
            return self.D[key].val
        else:
            return -1

        

    def put(self, key: int, value: int) -> None:
        if(self.current==self.capacity and key not in self.D):
            #evict least recenlty used
            temp=self.tail.left
            temp.left.right=self.tail
            self.tail.left=temp.left
            self.current-=1
            #print("HERE")
            del(self.D[temp.key])
        if(key in self.D):
            temp=self.D[key]
            temp.val=value
            #remove the node from current position
            temp.left.right=temp.right
            temp.right.left=temp.left
        else:
            #create a new node
            temp=LinkedList(value,key)
            self.D[key]=temp
            self.current+=1
        #attach temp to head since it has become most recenlty used
        temp.left=self.head
        temp.right=self.head.right
        self.head.right=temp
        temp.right.left=temp
        

        


# Your LRUCache object will be instantiated and called as such:
# obj = LRUCache(capacity)
# param_1 = obj.get(key)
# obj.put(key,value)
