import java.util.*;

class Problem2 {
    class LRUCache {

        class LLNode {
            int key;
            int value;
            LLNode next;
            LLNode prev;

            LLNode(int key, int value) {
                this.key = key;
                this.value = value;
                this.next = null;
                this.prev = null;
            }
        }

        HashMap<Integer, LLNode> map;
        LLNode head;
        LLNode tail;
        int cap;

        public LRUCache(int capacity) {
            map = new HashMap<>();
            head = new LLNode(-1, -1);
            tail = new LLNode(-1, -1);
            head.next = tail;
            tail.prev = head;
            cap = capacity;
        }

        public int get(int key) {
            if (map.containsKey(key)) {
                LLNode node = map.get(key);
                node.prev.next = node.next;
                node.next.prev = node.prev;
                addFront(node);
                return node.value;
            }
            return -1;
        }

        public void put(int key, int value) {
            LLNode node;
            if (map.containsKey(key)) {
                node = map.get(key);
                node.value = value;
                map.remove(key);
                node.prev.next = node.next;
                node.next.prev = node.prev;
            } else {
                if (map.size() == cap) {
                    LLNode least = removeBack();
                    map.remove(least.key);
                }
                node = new LLNode(key, value);
            }
            map.put(key, node);
            addFront(node);
        }

        public LLNode removeBack() {
            LLNode node = tail.prev;
            node.prev.next = tail;
            tail.prev = node.prev;
            return node;
        }

        public void addFront(LLNode node) {
            node.next = head.next;
            node.prev = head;
            head.next.prev = node;
            head.next = node;
        }
    }
}
