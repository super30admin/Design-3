package demo;

import java.util.HashMap;

class LRUCache {
    Node head;
    Node tail;
    int size;
    int capacity;
    HashMap<Integer, Node> map;
    public class Node{
        int key;
        int value;
        Node prev;
        Node next;
        public Node(int key , int value){
            this.key =key;
            this.value=value;
        }
    }

    public LRUCache(int capacity) {
        this.map = new HashMap<>();
        this.capacity = capacity;
        this.head = new Node(0,0);
        this.tail = new Node(0,0);
        this.head.next = this.tail;
        this.tail.prev = this.head;

    }
    
    public int get(int key) {
        if(map.containsKey(key)){
            Node node = map.get(key);
            remove(node);
            addToHead(node);
            return node.value;
        }
        return -1;
    }
    
    public void put(int key, int value) {
         if(map.containsKey(key)){
             Node node = map.get(key);
             remove(node);
             addToHead(node);
         }else{
             Node node2 = new Node(key, value);
             if(size < capacity){
                 size++;
                 map.put(key, node2);
             }else{
                 map.remove(tail.prev.next);
                 remove(tail.prev);
             }
             addToHead(node2);
         }
    }
    
    public void remove(Node node){
        node.prev.next = node.next;
        node.next.prev = node.prev;
    }
    
    public void addToHead(Node node){
        node.next = head.next;
        node.prev = head;
        head.next = node;
        node.next.prev = node;
    }
}

/**
 * Your LRUCache object will be instantiated and called as such:
 * LRUCache obj = new LRUCache(capacity);
 * int param_1 = obj.get(key);
 * obj.put(key,value);
 */