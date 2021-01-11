/*
class Node:
    def __init__(self, key, value):
        self.next = None
        self.prev= None
        self.key = key
        self.value = value
class LRUCache:

    def __init__(self, capacity: int):
        self.map = {}
        self.cap = capacity
        self.head = Node(-1,-1)
        self.tail = Node(-1,-1)
        self.head.next = self.tail
        self.tail.prev = self.head

    def get(self, key: int) -> int:
        if key in self.map:
            node = self.map[key]
            self.remove(node)
            self.addToBeg(node)
            return node.value
        
        return -1
        

    def put(self, key: int, value: int) -> None:
        if key in self.map:
            node = self.map[key]
            node.value = value
            self.remove(node)
            self.addToBeg(node)
            return
        
        elif len(self.map) == self.cap:
            self.map.pop(self.tail.prev.key)
            self.remove(self.tail.prev)
            
        cur = Node(key, value)
        self.map[key] = cur
        self.addToBeg(cur)
    
    def remove(self, node):
        node.prev.next = node.next
        node.next.prev = node.prev
    
    def addToBeg(self, node):
        node.next = self.head.next
        self.head.next.prev = node
        self.head.next = node
        node.prev = self.head
*/

// Time Complexity : O(1)
// Space Complexity : O(capacity) 
// Did this code successfully run on Leetcode : Yes
// Any problem you faced while coding this : No


// Your code here along with comments explaining your approach: I maintained a doubly linked list and everytime a key comes I removed and 
// put it to beginning to maintain LRU 

    
class Node{
    Node next, prev;
    int key, value;
    public Node(int key, int value){
        this.key = key;
        this.value = value;
    }
}
class LRUCache {
    Node head, tail;
    HashMap<Integer, Node> map;
    int cap;
    public LRUCache(int capacity) {
        map = new HashMap<>();
        head = new Node(-1, -1);
        tail = new Node(-1, -1);
        head.next = tail;
        tail.prev = head;
        cap = capacity;
    }
    
    public int get(int key) {
        if (map.containsKey(key)){
            Node cur = map.get(key);
            remove(cur);
            addToBeg(cur);
            return cur.value;
        }
        return -1;
    }
    
    public void put(int key, int value) {
        Node cur = head;
        if (map.containsKey(key)){
            cur = map.get(key);
            cur.value = value;
            remove(cur);
            addToBeg(cur);
            return;
        }
        else if (map.size() == cap){
            Node temp = tail.prev;
            map.remove(temp.key);
            remove(temp);
        }
        cur = new Node(key, value);
        map.put(key, cur);
        addToBeg(cur);
        
    }
    private void addToBeg(Node node){
        node.next = head.next;
        head.next.prev = node;
        head.next = node;
        node.prev = head;
    }
    private void remove(Node node){
        node.next.prev = node.prev;
        node.prev.next = node.next;
    }
}