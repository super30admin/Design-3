import java.util.HashMap;

/*
## Problem 2: LRU Cache(https://leetcode.com/problems/lru-cache/)

Time Complexity :   O (1) 
Space Complexity :  O (capacity) 
Did this code successfully run on Leetcode :    Yes (146. LRU Cache)
Any problem you faced while coding this :       No

 */
// Input
// ["LRUCache", "put", "put", "get", "put", "get", "put", "get", "get", "get"]
// [[2], [1, 1], [2, 2], [1], [3, 3], [2], [4, 4], [1], [3], [4]]
// Output
// [null, null, null, 1, null, -1, null, -1, 3, 4]

class LRUCache {
    class Node{
        int key;
        int val;
        Node next;
        Node prev;
        
        public Node(int key, int val){
            this.key = key;
            this.val = val;
        }
    }
    
    private void addToHead(Node node){
        node.next = head.next;
        node.prev = head;
        head.next = node;
        node.next.prev = node;
    }
    
    private void removeNode(Node node){
        node.next.prev = node.prev;
        node.prev.next = node.next;
    }
    
    Node head;
    Node tail;
    int capacity;
    HashMap<Integer, Node> map;

    public LRUCache(int capacity) {
        map = new HashMap<>();
        this.head = new Node(-1, -1);
        this.tail = new Node(-1, -1);
        this.head.next = tail;
        this.tail.prev = head;
        this.capacity = capacity;
    }
    
    public int get(int key) {
        if(!map.containsKey(key))
            return -1;
        Node node = map.get(key);
        removeNode(node);
        addToHead(node);
        return node.val;
    }
    
    public void put(int key, int value) {
        // exists
        if(map.containsKey(key)){
            Node node = map.get(key);
            node.val = value;
            removeNode(node);
            addToHead(node);
        }
        // fresh node
        else{
            if(capacity == map.size()){
                // remove LRU node
                Node tailPrev = tail.prev;
                removeNode(tailPrev);
                map.remove(tailPrev.key);
            }
            Node newNode = new Node(key, value);
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