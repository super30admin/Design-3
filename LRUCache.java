/*
* Approach:
*  1. Using doubly linkedlist with key, value, prev and next.
    and use head, tail pointer as MRU and LRU resp.

    Also, use hashmap to store key with node references.
* 
*  2. When a key is inserted, create new node append to head as MRU.
    add key and node to hashmap.

    when key is fetched from hashmap, attach the node to start of linkedlist.
* 
*  3. If hashmap capacity reaches Cache capacity, remove lastNode by moving
tail pointer to front.
* 
* 
* Did this code successfully run on Leetcode : YES
* 
* Any problem you faced while coding this : NO
* 
* Time Complexity: 
    put - O(1)
    get - O(1)
* 
* Space Complexity: O(N) use of hashmap
* 
*/

import java.util.HashMap;

public class LRUCache {
    class Node {
        int key;
        int value;

        Node prev;
        Node next;

        public Node(int key, int value){
            this.key = key;
            this.value = value;
        }
    };

    Node head, tail;

    int capacity;

    HashMap<Integer, Node> hmap;

    public LRUCache(int capacity) {
        this.capacity = capacity;
        this.hmap = new HashMap<>();
        head = new Node(-1, -1);
        tail = new Node(-1, -1);
        head.next = tail;
        tail.prev = head;
    }

    public int get(int key) {
        if(hmap.containsKey(key)){
            Node node = hmap.get(key);

            node.prev.next = node.next;
            node.next.prev = node.prev;
            
            attachFront(node);
            
            return node.value;
        }

        return -1;
    }

    private void attachFront(Node node){
        node.prev = head;
        node.next = head.next;

        head.next = node;
        node.next.prev = node;
    }

    private void removeLastNode(){
        Node temp = tail.prev;

        hmap.remove(temp.key);

        tail.prev = tail.prev.prev;
        tail.prev.next = tail;

        temp.prev = null;
        temp.next = null;
    }

    public void put(int key, int value) {
        Node node;

        if(hmap.containsKey(key)){
            node = hmap.get(key);
            node.value = value;

            //detach links
            node.prev.next = node.next;
            node.next.prev = node.prev;
        } else {
            node = new Node(key, value);

            hmap.put(key, node);
        }

        attachFront(node);

        if(hmap.size() > capacity){
            removeLastNode();
        }
    }
}

/**
 * Your LRUCache object will be instantiated and called as such:
 * LRUCache obj = new LRUCache(capacity);
 * int param_1 = obj.get(key);
 * obj.put(key,value);
 */