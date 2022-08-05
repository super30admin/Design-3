// Time Complexity : O(1)
// Space Complexity : O(capacity)
// Did this code successfully run on Leetcode : Yes

import java.util.HashMap;

class LRUCache {

    class Node {
        Node prev, next;
        int key, value;

        public Node(int key, int value) {
            this.key = key;
            this.value = value;
        }
    }

    Node head, tail;
    HashMap<Integer, Node> hmap;
    int capacity;

    public void addNodeAtHead(Node node) {
        node.next = head.next;
        node.prev = head;
        head.next = node;
        node.next.prev = node;
    }

    public void removeNode(Node node) {
        node.prev.next = node.next;
        node.next.prev = node.prev;
    }

    public LRUCache(int capacity) {
        head = new Node(-1, -1);
        tail = new Node(-1, -1);
        head.next = tail;
        tail.prev = head;
        hmap = new HashMap<>();
        this.capacity = capacity;

    }

    public int get(int key) {
        if (!hmap.containsKey(key)) {
            return -1;
        }
        Node node = hmap.get(key);
        removeNode(node);
        addNodeAtHead(node);
        int value = node.value;
        return value;

    }

    public void put(int key, int value) {
        if (hmap.containsKey(key)) {
            Node node = hmap.get(key);
            removeNode(node);
            addNodeAtHead(node);
            node.value = value;
            return;
        }
        if (hmap.size() == capacity) {
            Node tailPrev = tail.prev;
            removeNode(tailPrev);
            hmap.remove(tailPrev.key);
        }

        Node newNode = new Node(key, value);
        addNodeAtHead(newNode);
        hmap.put(key, newNode);
        return;
    }
}

/**
 * Your LRUCache object will be instantiated and called as such:
 * LRUCache obj = new LRUCache(capacity);
 * int param_1 = obj.get(key);
 * obj.put(key,value);
 */