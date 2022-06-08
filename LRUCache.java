// Time Complexity : O(1) all the methods
// Space Complexity : O(1),no auxiliary space used
// Did this code successfully run on Leetcode : Yes
// Any problem you faced while coding this : No
public class LRUCache {
        class DLinkedNode {
            int key;
            int val;
            DLinkedNode prev;
            DLinkedNode next;

            public DLinkedNode(int key, int val) {
                this.key = key;
                this.val = val;
            }
        }

        private void addToHead(DLinkedNode node) {
            node.next = head.next;
            node.prev = head;
            head.next = node;
            node.next.prev = node;
        }

        private void removeNode(DLinkedNode node) {
            node.next.prev = node.prev;
            node.prev.next = node.next;
        }

        DLinkedNode head;
        DLinkedNode tail;
        Map<Integer, DLinkedNode> map;
        int capacity;
        public LRUCache(int capacity) {
            this.head = new DLinkedNode(-1, -1);
            this.tail = new DLinkedNode(-1, -1);
            head.next = tail;
            tail.prev = head;
            this.map = new HashMap<>();
            this.capacity = capacity;
        }

        public int get(int key) {
            if(!map.containsKey(key)) return -1;
            DLinkedNode node  = map.get(key);
            removeNode(node);
            addToHead(node);
            return node.val;
        }

        public void put(int key, int value) {
            if(map.containsKey(key)) {
                DLinkedNode node  = map.get(key);
                node.val = value;
                removeNode(node);
                addToHead(node);
            } else {
                if(capacity == map.size()) {
                    //delete LRU nde
                    DLinkedNode tailPrev = tail.prev;
                    removeNode(tailPrev);
                    map.remove(tailPrev.key);
                }
                DLinkedNode newNode = new DLinkedNode(key, value);
                addToHead(newNode);
                map.put(key, newNode);

            }
        }

/**
 * Your LRUCache object will be instantiated and called as such:
 * LRUCache obj = new LRUCache(capacity);
 * int param_1 = obj.get(key);
 * obj.put(key,value);
 */
}
