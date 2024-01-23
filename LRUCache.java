//T.C O(1) for all operations
//S.C O(n)
//Successfully ran in Leetcode website: yes

import java.util.HashMap;
import java.util.Map;

class LRUCache {

    int capacity;
    Map<Integer, Node> map;
    Node head;
    Node tail;

    class Node{
        int key;
        int value;
        Node next;
        Node prev;

        public Node(int key, int value){
            this.key = key;
            this.value = value;
        }
    }

    public LRUCache(int capacity) {
        this.capacity = capacity;
        this.map = new HashMap<>();
        this.head = new Node(-1, -1);
        this.tail = new Node(-1, -1);
        head.next = tail;
        tail.prev = head;

    }

    private void removeNode(Node node){
        node.prev.next = node.next;
        node.next.prev = node.prev;
    }

    private void addNodeToHead(Node node){
        node.prev = head;
        node.next = head.next;
        node.next.prev = node;
        head.next = node;
    }

    public int get(int key) {
        if(!map.containsKey(key)) return -1;
        Node temp = map.get(key);
        removeNode(temp);
        addNodeToHead(temp);
        return temp.value;
    }

    public void put(int key, int value) {
        if(map.containsKey(key)){
            Node temp = map.get(key);
            temp.value = value;
            removeNode(temp);
            addNodeToHead(temp);
        } else {
            if(map.size() == capacity){
                Node prev = tail.prev;
                map.remove(prev.key);
                removeNode(prev);
            }
            Node newN = new Node(key, value);
            addNodeToHead(newN);
            map.put(key, newN);
        }
    }
}

/**
 * Your LRUCache object will be instantiated and called as such:
 * LRUCache obj = new LRUCache(capacity);
 * int param_1 = obj.get(key);
 * obj.put(key,value);
 */