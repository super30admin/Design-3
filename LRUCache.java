package Fifth_week.longest_word_dictionary;
// Time Complexity : O(1)
// Space Complexity : O(n) n : capacity
// Did this code successfully run on Leetcode : yes
// Any problem you faced while coding this : No

import java.util.HashMap;

public class LRUCache {

    class Node{
        int key;
        int val;
        Node prev;
        Node next;
        Node(int key,int val){
            this.key = key;
            this.val = val;
        }
      }

    public void addToHead(Node node){
        node.next = head.next;
        node.prev = head;
        head.next = node;
        node.next.prev = node;

    }

    public void removeNode(Node node){
        node.prev.next = node.next;
        node.next.prev = node.prev;
    }

    Node head;
    Node tail;
    int capacity;
    HashMap<Integer,Node> map;
    LRUCache(int capacity){
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
        addToHead(node);
        return node.val;
    }

    public void put(int key, int value) {

        if(map.containsKey(key)){
            Node node = map.get(key);
            node.val = value;
            removeNode(node);
            addToHead(node);
        } else{
            Node newNode = new Node(key,value);
            if(capacity == map.size()){
                Node tailPrev = tail.prev;
                removeNode(tailPrev);
                map.remove(tailPrev.key);
            }

            addToHead(newNode);
            map.put(key,newNode);

        }
    }

    public static void main(String[] args) {
        LRUCache lRUCache = new LRUCache(2);
        lRUCache.put(1, 1); // cache is {1=1}
        lRUCache.put(2, 2); // cache is {1=1, 2=2}
        System.out.println(lRUCache.get(1));
        lRUCache.get(1);    // return 1
        lRUCache.put(3, 3); // LRU key was 2, evicts key 2, cache is {1=1, 3=3}
        lRUCache.get(2);    // returns -1 (not found)
        lRUCache.put(4, 4); // LRU key was 1, evicts key 1, cache is {4=4, 3=3}
        lRUCache.get(1);    // return -1 (not found)
        lRUCache.get(3);    // return 3
        lRUCache.get(4);    // return 4
    }
}
