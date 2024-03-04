// Time complexity: O(1) in all cases
// Space complexity: O(1) no aux space

//Approach: Used a hash map and linked list to get and put in O(1)
// and map.size() to handle remove functions

import java.util.HashMap;
import java.util.Map;

class LRUCache {
    class ListNode {
        int val;
        int key;
        ListNode next;
        ListNode prev;

        public ListNode(int key, int value) {
            this.key = key;
            this.val = value;
            this.next = null;
            this.prev = null;
        }
    }

    int capacity;
    ListNode head;
    ListNode tail;
    Map<Integer, ListNode> map;

    public LRUCache(int capacity) {
        this.capacity = capacity;
        this.map = new HashMap();
        head = new ListNode(-1, 0);
        tail = new ListNode(-1, 0);
        // dummy pointers to get lru and mru
        head.next = tail;
        tail.prev = head;
    }

    public int get(int key) {
        if (!map.containsKey(key)) {
            return -1;
        }
        ListNode node = map.get(key);
        moveToFront(node);
        return node.val;
    }

    public void put(int key, int value) {
        if (map.containsKey(key)) {
            ListNode node = map.get(key);
            moveToFront(node);
            node.val = value;
            return;
        } else {
            if (map.size() == capacity) {
                ListNode toBeRemovedNode = head.next;
                head.next = toBeRemovedNode.next;
                toBeRemovedNode.next.prev = head;
                toBeRemovedNode.next = null;
                toBeRemovedNode.prev = null;
                map.remove(toBeRemovedNode.key);
            }

            ListNode newNode = new ListNode(key, value);
            map.put(key, newNode);
            newNode.next = tail;
            newNode.prev = tail.prev;
            tail.prev.next = newNode;
            tail.prev = newNode;
        }
    }

    private void moveToFront(ListNode node) {
        node.next.prev = node.prev;
        node.prev.next = node.next;

        // mru connections
        node.next = tail;
        node.prev = tail.prev;
        tail.prev.next = node;
        tail.prev = node;
    }

}

/**
 * Your LRUCache object will be instantiated and called as such:
 * LRUCache obj = new LRUCache(capacity);
 * int param_1 = obj.get(key);
 * obj.put(key,value);
 */