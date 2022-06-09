// Time Complexity :O(1)
// Space Complexity :O(1)
// Did this code successfully run on Leetcode :yes
// Any problem you faced while coding this :No
// we are using hashmap to get key in O(1) time and doubly linked list to remove and add at start
//to keep track of least recently used node
class LRUCache {
    class Node {
        private int key;
        private int val;
        private Node prev;
        private Node next;

        Node(int key, int value) {
            this.key = key;
            this.val = value;
        }
    }

    private Node head;
    private Node tail;
    private int cap;
    private HashMap<Integer, Node> map;

    public LRUCache(int capacity) {
        head = new Node(-1, -1);
        tail = new Node(-1, -1);
        head.next = tail;
        tail.prev = head;
        map = new HashMap<>();
        cap = capacity;
    }

    public int get(int key) {
        if (map.containsKey(key)) {
            Node node = map.get(key);
            int result = node.val;
            remove(node);
            update(node);
            return result;
        }
        return -1;
    }

    private void remove(Node node) {
        node.next.prev = node.prev;
        node.prev.next = node.next;

        return;
    }

    private void update(Node node) {
        node.next = head.next;
        node.prev = head;
        head.next = node;
        node.next.prev = node;
        return;
    }

    public void put(int key, int value) {
        if (map.containsKey(key)) {
            Node node = map.get(key);
            node.val = value;
            map.put(key, node);
            remove(node);
            update(node);
        } else {
            Node node = new Node(key, value);
            map.put(key, node);
            update(node);
            if (map.size() > cap) {
                Node tailPrev = tail.prev;
                remove(tailPrev);
                map.remove(tailPrev.key);
            }
        }
        return;
    }
}

/**
 * Your LRUCache object will be instantiated and called as such:
 * LRUCache obj = new LRUCache(capacity);
 * int param_1 = obj.get(key);
 * obj.put(key,value);
 */