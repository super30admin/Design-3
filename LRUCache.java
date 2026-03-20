

// Space Complexity: O(n)
// Time Complexity: O(1)
// Ran successfully on leetcode: yes
class LRUCache {


    int cap;
    HashMap<Integer, ListNode> map;
    ListNode head;
    ListNode tail;

    class ListNode {
        int key, val;
        ListNode prev, next;

        ListNode(int key, int val) {
            this.key = key;
            this.val = val;
        }
    }

    public LRUCache(int capacity) {
        this.cap = capacity;
        map = new HashMap<>();
        head = new ListNode(-1, -1);
        tail = new ListNode(-1, -1);
        head.next = tail;
        tail.prev = head;
    }
//    Time: O(1)
    public int get(int key) {
        if (!map.containsKey(key)) return -1;
        ListNode node = map.get(key);
        removeNode(node);
        addToHead(node);

        return node.val;
    }

    // Time: O(1)
    public void put(int key, int value) {
        if (map.containsKey(key)) {
            ListNode node = map.get(key);
            node.val = value;
            removeNode(node);
            addToHead(node);
        } else {
            if (map.size() == cap) {
                removeLRU();
            }
            ListNode node = new ListNode(key, value);
            map.put(key, node);
            addToHead(node);
        }
    }

    // Time: O(1)
    private void removeNode(ListNode node) {
        node.prev.next = node.next;
        node.next.prev = node.prev;
    }
    // Time: O(1)
    private void addToHead(ListNode node) {
        node.next = head.next;
        node.prev = head;
        head.next.prev = node;
        head.next = node;
    }
    // Time: O(1)
    private void removeLRU() {
        ListNode lru = tail.prev;
        removeNode(lru);
        map.remove(lru.key);
    }
}

/**
 * Your LRUCache object will be instantiated and called as such:
 * LRUCache obj = new LRUCache(capacity);
 * int param_1 = obj.get(key);
 * obj.put(key,value);
 */