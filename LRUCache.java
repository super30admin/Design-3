// Time Complexity : O(1)
// Space Complexity : O(capacity)
// Did this code successfully run on Leetcode : Yes
// Any problem you faced while coding this : No
import java.util.HashMap;

class LRUCache {
    class ListNode {
        int key;
        int val;
        ListNode next;
        ListNode prev;

        ListNode(int k, int x) {
            key = k;
            val = x;
            next = null;
            prev = null;
        }
    }

    private ListNode head;
    private ListNode tail;
    private int capacity;
    private HashMap<Integer, ListNode> map;

    private void addToHead(ListNode curr) {
        curr.prev = head;
        curr.next = head.next;
        head.next = curr;
        curr.next.prev = curr;
    }

    private void removeNode(ListNode curr) {
        curr.next.prev = curr.prev;
        curr.prev.next = curr.next;
    }

    public LRUCache(int capacity) {
        this.capacity = capacity;
        this.head = new ListNode(-1, -1);
        this.tail = new ListNode(-1, -1);
        this.head.next = this.tail;
        this.tail.prev = this.head;
        this.map = new HashMap<>();
    }

    public int get(int key) {
        if (!map.containsKey(key)) {
            return -1;
        }
        ListNode node = map.get(key);
        removeNode(node);
        addToHead(node);
        return node.val;
    }

    public void put(int key, int value) {
        if (map.containsKey(key)) {
            ListNode node = map.get(key);
            node.val = value;
            removeNode(node);
            addToHead(node);
        } else {
            if (map.size() == capacity) {
                ListNode x = tail.prev;
                removeNode(x);
                map.remove(x.key);
            }
            ListNode node = new ListNode(key, value);
            map.put(key, node);
            addToHead(node);
        }
    }
}

/**
 * Your LRUCache object will be instantiated and called as such:
 * LRUCache obj = new LRUCache(capacity);
 * int param_1 = obj.get(key);
 * obj.put(key,value);
 */