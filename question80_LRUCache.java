package Design3;

import java.util.HashMap;

public class question80_LRUCache {
    /* Created by palak on 7/3/2021 */
        /*
    Used Doubly LinkedList to store the elements. HashMap was used to store the node and the key of the Elements.
        Time Complexity: O(1)
        Space Complexity: O(n)
    */
    class Node {
        int key;
        int val;
        Node next;
        Node prev;
    }

    HashMap<Integer, Node> map = new HashMap<>();
    int capacity;
    Node head = new Node();
    Node tail = new Node();

    public question80_LRUCache(int capacity) {
        map = new HashMap<>();
        this.capacity = capacity;
        head.next = tail;
        tail.prev = head;
    }

    private void addNode(Node node) {
        Node headNext = head.next;
        headNext.prev = node;
        node.prev = head;
        node.next = headNext;
        head.next = node;
    }

    private void deleteNode(Node node) {
        node.next.prev = node.prev;
        node.prev.next = node.next;
    }

    public int get(int key) {
        int result = -1;
        Node node = map.get(key);
        if(node != null) {
            result = node.val;
            deleteNode(node);
            addNode(node);
        }
        return result;
    }

    public void put(int key, int value) {
        Node node = map.get(key);
        if(node != null) {
            deleteNode(node);
            node.val = value;
            addNode(node);
        } else {
            if(capacity == map.size()) {
                map.remove(tail.prev.key);
                deleteNode(tail.prev);
            }
            Node new_node = new Node();
            new_node.key = key;
            new_node.val = value;
            map.put(key, new_node);
            addNode(new_node);
        }
    }
}

/**
 * Your LRUCache object will be instantiated and called as such:
 * LRUCache obj = new LRUCache(capacity);
 * int param_1 = obj.get(key);
 * obj.put(key,value);
 */
