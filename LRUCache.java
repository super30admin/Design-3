// Time Complexity : O(1)  [get: O(1);  put: O(1); deleteNode: O(1); addNodeUpfront: O(1); deleteFromBehind: O(1)]
// Space Complexity : O(n) [n: Capacity of the Cache; deleteNode: O(1); addNodeUpfront: O(1); deleteFromBehind: O(1)]
// Did this code successfully run on Leetcode : Yes
// Any problem you faced while coding this : No

import java.util.HashMap;
import java.util.Map;

class LRUCache {

    Map<Integer, Node> map;
    Node head;
    Node tail;
    int capacity;

    public LRUCache(int capacity) {
        this.capacity = capacity;
        map = new HashMap<>();
        head = new Node(-1, -1);
        tail = new Node(-1, -1);
        head.next = tail;
        head.prev = null;
        tail.prev = head;
        tail.next = null;
    }

    public int get(int key) {
        Node node = map.get(key);
        if (null == node) {
            return -1;
        } else {

            // Node removed from current place
            deleteNode(node);

            // add node at the front of the queue
            addNodeUpfront(node);
            return node.val;
        }
    }

    private void deleteNode(Node node) {
        Node prev = node.prev;
        Node next = node.next;
        prev.next = next;
        next.prev = prev;
    }

    private void addNodeUpfront(Node node) {
        Node curr = this.head.next;
        this.head.next = node;
        node.next = curr;
        node.prev = this.head;
        curr.prev = node;
    }

    private Node deleteFromBehind() {
        Node last = this.tail.prev;
        Node lastPrev = last.prev;
        lastPrev.next = this.tail;
        this.tail.prev = lastPrev;
        return last;
    }

    public void put(int key, int value) {
        Node node = map.get(key);
        if (null == node) {
            int size = this.map.size();
            node = new Node(value, key);
            if (size == this.capacity) {
                Node toBedeletedNode = deleteFromBehind();
                map.remove(toBedeletedNode.key);
            }
            // add node at the front of the Dequeue
            addNodeUpfront(node);
            map.put(key, node);
        } else {
            // removed from current place
            deleteNode(node);
            node.val = value;
            // add node at the front
            addNodeUpfront(node);
        }
    }
}

class Node {
    int val;
    Node next;
    Node prev;
    int key;

    Node(int val, int key) {
        this.val = val;
        this.key = key;
        this.next = null;
        this.prev = null;
    }
}
/**
 * Your LRUCache object will be instantiated and called as such:
 * LRUCache obj = new LRUCache(capacity);
 * int param_1 = obj.get(key);
 * obj.put(key,value);
 */