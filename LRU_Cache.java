//Time complexity : O(1)
//Space Complexity : O(1)
//Did it run on leetcode : yes

import java.util.HashMap;
import java.util.Map;

public class LRU_Cache {
    class Node{
        int val;
        int key;
        Node prev;
        Node next;

        public Node(int key, int val){
            this.key = key;
            this.val = val;
        }
    }
    //most recently used
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

    int capacity;
    Node head, tail;
    Map<Integer, Node> map;

    public LRU_Cache(int capacity) {
        this.capacity = capacity;
        map = new HashMap<>();
        this.head = new Node(-1,-1);
        this.tail = new Node(-1,-1);
        head.next = tail;
        tail.next = head;
    }

    public int get(int key) {
        if(!map.containsKey(key)) return -1;
        //fetch node for this key
        Node node = map.get(key);
        //remove node from current location
        removeNode(node);
        //add to most recently used i.e head
        addToHead(node);
        return node.val;
    }

    public void put(int key, int value) {
        if(map.containsKey(key)){
            Node node = map.get(key);
            //update value
            node.val = value;
            //remove node from current location
            removeNode(node);
            //add to most recently used i.e head
            addToHead(node);
        }else{
            //check capacity
            if(capacity == map.size()){
                //remove LRU
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
