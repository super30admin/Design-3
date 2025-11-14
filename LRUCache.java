/*
 * LRUCache.java
 *
 * Approach:
 * - Use a HashMap for O(1) access to nodes by key, and a doubly-linked list to maintain
 *   usage order (most-recently-used at the front, least-recently-used at the back).
 * - On get(key): if present, move node to front and return its value.
 * - On put(key, value): if key exists, update value and move to front; otherwise create
 *   a new node, add to front, and if capacity exceeded remove the tail's previous node
 *   (least recently used) and delete it from the map.
 *
 * Time Complexity:
 * - get: O(1)
 * - put: O(1)
 *
 * Space Complexity:
 * - O(capacity) for the hashmap and linked list nodes.
 *
 * LeetCode:
 * - https://leetcode.com/problems/lru-cache/ (Problem 146)
 */

import java.util.HashMap;

public class LRUCache {

    static class Node {
        int value;
        int key;
        Node previous;
        Node next;

        public Node(int value, int key) {
            this.value = value;
            this.key = key;
        }
    }

    HashMap<Integer, Node> map;
    Node head;
    Node tail;
    int capacity;

    public LRUCache(int capacity) {
        this.capacity = capacity;
        map = new HashMap<>();
        head = new Node(-1,-1);
        tail = new Node(-1,-1);

        head.next = tail;
        tail.previous = head;
    }

    public int get(int key) {
        if (map.containsKey(key)) {
            Node currentNode = map.get(key);
            removeNode(currentNode);
            addInFront(currentNode);
            return currentNode.value;
        }
        return -1;
    }

    public void put(int key, int value) {

        if (map.containsKey(key)) {
            Node currentNode = map.get(key);
            removeNode(currentNode);
            addInFront(currentNode);

            currentNode.value = value;
        } else {
            if (map.size() >= capacity) {
                Node currentNode = tail.previous;
                removeNode(currentNode);
                map.remove(currentNode.key);
            }
            //add the node in front
            Node currentNode = new Node(value, key);
            addInFront(currentNode);
            map.put(key, currentNode);
        }
    }

    public void addInFront(Node node) {
        node.next = head.next;
        head.next = node;
        node.previous = head;
        node.next.previous = node;
    }

    public void removeNode(Node node) {
        node.previous.next = node.next;
        node.next.previous = node.previous;
        node.previous = null;
        node.next = null;
    }
}
