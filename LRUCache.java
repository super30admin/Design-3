// TC: O(1)
// SC: O(capacity)

import java.util.HashMap;

public class LRUCache {

    class Node{
        int key;
        int val;
        Node prev;
        Node next;
        public Node(int key, int val){
            this.key = key;
            this.val = val;
        }
    }

    HashMap<Integer, Node> map;
    int capacity;
    int size;
    Node head, tail;

    public LRUCache(int capacity) {
        this.map = new HashMap<>();
        this.capacity = capacity;
        head = new Node(-1, -1);
        tail = new Node(-1, -1);
        head.next = tail;
        tail.prev = head;
    }
    
    public int get(int key) {
        if(map.containsKey(key)){
            Node n = map.get(key);
            moveToTail(n);
            return n.val;
        }
        return -1;
    }

    private void moveToTail(Node newNode){
        deleteNode(newNode);
        insertAtTail(newNode);
    }

    private void insertAtTail(Node newNode){
        newNode.prev = tail.prev;
        newNode.next = tail;

        tail.prev = newNode;
        newNode.prev.next = newNode;

        size++;
    }

    private void deleteNode(Node node){
        Node prv = node.prev;
        Node nxt = node.next;
        prv.next = nxt;
        nxt.prev = prv;
        size--;
    }

    public void put(int key, int value) {

        if(map.containsKey(key)){
            deleteNode(map.get(key));
        } else if(size == capacity){
            map.remove(head.next.key);
            deleteNode(head.next);
        }

        Node newNode = new Node(key, value);
        insertAtTail(newNode);
        map.put(key, newNode);
    }
}

/**
 * Your LRUCache object will be instantiated and called as such:
 * LRUCache obj = new LRUCache(capacity);
 * int param_1 = obj.get(key);
 * obj.put(key,value);
 */
