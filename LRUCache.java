/*
- LRU -> Least Recently Used cache. Both get and put operation should run in O(1) time. 
- DS -> doubly linkedlist and HashMap -> map to store key and the address of the node to get element in O(1) time
If you use singly linkedlist, we can get the node in O(1) but once we update the node, we need to move it to the most recently used side which again will take O(n) time. So doubly linkedlist works best where we have access to previous node
- To put a node, we will be checking the capacity has reached
    - if reached, remove the prev to the tail(LRU) node(because tail is dummy), remove the entry from map, add the new node to the next of head and add the entry to the map
    - if not reached, add the node to the map as well as the next of head
- To get a node, check if the node exists in the map
    - if it doesnt, return -1
    - if it does, get the address of the node from the map, remove the node from that position and add it to the next of the head as it is most recently access node.
- TC: O(1) -> as all the operations are performed in O(1) time
 */

class LRUCache {
    HashMap<Integer, Node> map;
    int capacity;
    Node head;
    Node tail;

    class Node{
        int key;
        int val;

        //doubly linkedlist
        Node prev;
        Node next;

        public Node(int key, int val){
            this.key = key;
            this.val = val;
        }
    }

    public LRUCache(int capacity) {
        this.capacity = capacity;
        this.map = new HashMap<>();
        this.head = new Node(-1,-1);
        this.tail = new Node(-1,-1);
        head.next = tail;
        tail.prev = head;
    }
    
    private void removeNode(Node node){
        node.prev.next = node.next;
        node.next.prev = node.prev;
        node.prev = null;
        node.next = null;
    }

    private void addToHead(Node node){
        node.prev = head;
        node.next = head.next;
        node.next.prev = node;
        head.next = node;
    }

    public int get(int key) {
        // search in map
        if(!map.containsKey(key))
            return -1;
        Node node = map.get(key);
        removeNode(node);
        addToHead(node);
        return node.val;
        
    }
    
    public void put(int key, int value) {
        if(map.containsKey(key)){
            Node node = map.get(key);
            node.val = value;
            removeNode(node);
            addToHead(node);
        }

        else{
            if(map.size() == capacity){
                Node lastNode = tail.prev;
                removeNode(lastNode);
                map.remove(lastNode.key);
            }
            Node newNode = new Node(key,value);
            addToHead(newNode);
            map.put(key, newNode);
        }
        
    }
}

/**
 * Your LRUCache object will be instantiated and called as such:
 * LRUCache obj = new LRUCache(capacity);
 * int param_1 = obj.get(key);
 * obj.put(key,value);
 */


 