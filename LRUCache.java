//Time Complexity :  O(1) for get, put operations
//Space Complexity :  O(n)
//Did this code successfully run on Leetcode : Yes

import java.util.*;

class LRUCache {
    class Node{
        int key, val;
        Node next, prev;       
        public Node(int key, int val){
            this.key = key;
            this.val = val;
        }
    }

    Node head, tail;
    int size, capacity;
    Map<Integer,Node> map;

    public LRUCache(int capa) {
        this.capacity = capa;
        this.size = 0;
        head = new Node(1,1);
        tail = new Node(1,1);
        head.next=tail;
        tail.prev=head;
        map=new HashMap<>();
    }

    private void removeNode(Node node){
        node.prev.next = node.next;
        node.next.prev = node.prev;
    }
    
    private void addNode(Node node){
        node.next = head.next;
        node.prev = head;
        node.next.prev = node;
        head.next = node;
     }
     
     public int get(int key) {
        if (map.containsKey(key)){
            Node node = map.get(key);
            removeNode(node);
            addNode(node);
            return node.val;
        } 
        return -1;
    }

    public void put(int key, int value) {
        if (map.containsKey(key)){
            Node node = map.get(key);
            node.val = value;
            removeNode(node);
            addNode(node);
        } else {
            if (size < capacity){
                Node node = new Node(key,value);
                map.put(key,node);
                addNode(node);
                size++;
            } else {
                Node node = new Node(key,value);
                map.put(key,node);
                addNode(node);
                Node delNode = tail.prev;
                removeNode(delNode);
                map.remove(delNode.key);
            }
        }
    }
}

/**
 * Your LRUCache object will be instantiated and called as such:
 * LRUCache obj = new LRUCache(capacity);
 * int param_1 = obj.get(key);
 * obj.put(key,value);
 */