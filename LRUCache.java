// Time Complexity - O(1)
// Space Complexity - O(n) where n = capacity of cache

// Intuition:
// We will be using HashMap and LinkedList in this problem. First we start with HashMap, storing key and value
// and we insert the key, val pair to LL but to get a LL directly from HashMap with just value is difficult.
// So we store key, node in the HashMap. But since the most recently visited node must be removed from its position
// and moved after the head, we also need to keep track of prev node to the recently visited node to remove it.
// To keep track of previous node, we can use Doubly LL, as we can track both next and prev of every node.

// Approach:
// 1. Get - We check if the key is present or not in the HashMap. If the key isn't present, we return -1. 
// Otherwise, we get the node by using Hashmap based on key and then remove the node from its position and insert it at the start as we have recently accessed it.
// 2. Put: We check if the node is already in cache or not. If it is, then we get the node from hashmap, change the value to new value, remove the node from its position and 
// insert it after the head. Otherwise, we check if the capacity is full or not, if capacity of cache is full, then we remove the last node (from LL, hashmap) which is before tail as its 
// least recently used and make space for new node. We add the new node to the map and insert it after the head of DLL.

// PS - Ordered Dict data structure maintains a HashMap with key, node pair along with a separate doubly linked list. Its not a straight forward hash based data structure.
// Technically, we are using Ordered Dict data structure in this problem


class LRUCache {
    class Node {
        int key, val;
        Node prev, next;
        public Node(int key, int val) {
            this.key = key;
            this.val = val;
        }
    }
    
    private void removeNode(Node node) {
        node.prev.next = node.next;
        node.next.prev = node.prev;
    }
    
    private void addtoHead(Node node) {
        node.prev = head;
        node.next = head.next;
        head.next = node;
        node.next.prev = node;
    }
    HashMap<Integer, Node> map;
    Node head; Node tail;
    int capacity;
    public LRUCache(int capacity) {
        this.capacity = capacity;
        map = new HashMap<>();
        head = new Node(-1,-1);
        tail = new Node(-1,-1);
        head.next = tail;
        tail.prev = head;
    }
    
    public int get(int key) {
        if(!map.containsKey(key)) return -1;
        Node node = map.get(key);
        removeNode(node);
        addtoHead(node);
        return node.val;
    }
    
    public void put(int key, int value) {
        if(map.containsKey(key)) {
            Node node = map.get(key);
            node.val = value;
            removeNode(node);
            addtoHead(node);
        } else {
            if(capacity == map.size()) {
                Node tailPrev = tail.prev;
                removeNode(tailPrev);
                map.remove(tailPrev.key);
            }
            Node newNode = new Node(key,value);
            map.put(key,newNode);
            addtoHead(newNode);
        }
    }
}

/**
 * Your LRUCache object will be instantiated and called as such:
 * LRUCache obj = new LRUCache(capacity);
 * int param_1 = obj.get(key);
 * obj.put(key,value);
 */