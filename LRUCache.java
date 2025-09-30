//TC: O(1)
//SC: O(1)
//Approach: The get operation should be amortized O(1) which means we need a DS which gets us the value in O(1) time for a key
//We cannot use a LinkedList or a Stack for that purpose, we need a hashing DS, we will use a Map because we need to store key value pairs
//The put operation should be amortized O(1) and we can put the key-value pair by making space in the cache (remove lru node), for which we
//need to track the MRU and LRU, we can use a doubly linked list for this purpose

import java.util.HashMap;
import java.util.Map;

public class LRUCache {

    private static class ListNode {
        private final int key;
        private int value;
        private ListNode next, prev;

        private ListNode(int key, int value) {
            this.key = key;
            this.value = value;
        }
    }

    private final int capacity;
    private final Map<Integer, ListNode> cache;
    private final ListNode head;
    private final ListNode tail;

    public LRUCache(int capacity) {
        this.capacity = capacity;
        this.cache = new HashMap<>();
        this.head = new ListNode(-1, -1);
        this.tail = new ListNode(-1, -1);

        this.head.next = this.tail;
        this.tail.prev = this.head;
    }

    public int get(int key) {
        if (!this.cache.containsKey(key)) return -1;

        final ListNode node = this.cache.get(key);
        remove(node);
        add(node);

        return node.value;
    }

    public void put(int key, int value) {
        if (this.cache.containsKey(key)) {
            final ListNode node = this.cache.get(key);
            node.value = value;

            remove(node);
            add(node);
        } else {
            if (this.cache.size() == capacity) {
                final ListNode lru = tail.prev;
                remove(lru);
                this.cache.remove(lru.key);
            }

            final ListNode node = new ListNode(key, value);
            add(node);
            this.cache.put(key, node);
        }
    }

    private void add(ListNode node) {
        //head -> X -> A -> B -> C -> tail, add X
        node.next = head.next;
        node.prev = head;

        head.next.prev = node;
        head.next = node;

    }

    private void remove(ListNode node) {
        //head -> A -> B -> C -> tail, remove B

        node.prev.next = node.next;
        node.next.prev = node.prev;
    }
}
