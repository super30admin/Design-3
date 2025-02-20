//TimeComplexity: O(n)
//Space Complexity: O(n)

import java.util.HashMap;
import java.util.Stack;

public class LRUCache {

    class Node{
        int key, val;
        Node prev, next;
        public Node(int key, int val){
            this.key = key;
            this.val = val;
        }
    }

    HashMap<Integer, Node> hmap;
    Node head, tail;
    int capacity;

    public void addNode(Node n){
        Node nextn = head.next;
        n.next = nextn;
        nextn.prev = n;
        head.next = n;
        n.prev = head;
    }

    public void removeNode(Node n){
        Node nextn = n.next;
        Node prevn = n.prev;

        prevn.next = nextn;
        nextn.prev = prevn;
        n.next = null;
        n.prev = null;
    }

    public LRUCache(int capacity) {
        this.capacity = capacity;
        hmap = new HashMap<>();
        head = new Node(-1,-1);
        tail = new Node(-1,-1);
        head.next = tail;
        tail.prev = head;
    }

    public int get(int key) {
        if(hmap.containsKey(key)){
            Node n = hmap.get(key);
            removeNode(n);
            addNode(n);
            return n.val;
        }
        return -1;
    }

    public void put(int key, int value) {
        if(hmap.containsKey(key)){
            Node n = hmap.get(key);
            removeNode(n);
            n.val = value;
            addNode(n);
            return;
        }

        if(hmap.size() == capacity){
            Node n = tail.prev;
            removeNode(n);
            hmap.remove(n.key);
        }
        Node n = new Node(key,value);
        hmap.put(key, n);
        addNode(n);
        return;

    }
}
