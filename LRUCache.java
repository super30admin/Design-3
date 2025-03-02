// Problem 2: LRU Cache(https://leetcode.com/problems/lru-cache/)

// Time Complexity : O(1)
// Space Complexity : O(capacity)
// Did this code successfully run on Leetcode : yes
// Any problem you faced while coding this : no

// Your code here along with comments explaining your approach in three sentences only
/*
 * Here, create a node class with key and value. Add all nodes to the hashmap with key and value as node. For get operation remove node from
 * its place and keep it next to head. For put operation if the key is same just update the value and if capacity is full remove the least
 * recently used that is node next to tail and add new one next to head.
 */
import java.util.HashMap;

class LRUCache {
    class Node{
        int val, key;
        Node next;
        Node prev;
        public Node(int key, int val){
            this.val = val;
            this.key = key;
        }
    }
    int cap;
    Node head, tail;
    HashMap<Integer, Node> map;

    public LRUCache(int capacity) {
        this.cap = capacity;
        this.head = new Node(-1,-1);
        this.tail = new Node(-1, -1);
        this.head.next = this.tail;
        this.tail.prev = this.head;
        this.map = new HashMap<>();
    }

    public void delete(Node node){  // remove a node
        node.prev.next = node.next;
        node.next.prev = node.prev;
    }

    public void add(Node node){ // add node next to head
        node.next = head.next;
        node.prev = head;
        head.next.prev = node;
        head.next = node; 
    }
    
    public int get(int key) {
        if(!map.containsKey(key)){
            return -1;
        }
        Node node = map.get(key);
        delete(node);
        add(node);
        return node.val;
        
    }
    
    public void put(int key, int value) {
        if(map.containsKey(key)){
            Node node = map.get(key);
            node.val = value;
            delete(node);
            add(node);
        }
        else {
            if(cap == map.size()){
            Node delNode = tail.prev;
            delete(delNode);
            map.remove(delNode.key);
        }
        Node node = new Node(key, value);
        add(node);
        map.put(key, node);
    }
}
}

/**
 * Your LRUCache object will be instantiated and called as such:
 * LRUCache obj = new LRUCache(capacity);
 * int param_1 = obj.get(key);
 * obj.put(key,value);
 */