class LRUCache {
    Map<Integer, Node> map = new HashMap<>();
    Node head = new Node(-1, -1);
    Node tail = new Node(-1, -1);
    int capacity;
    public LRUCache(int capacity) {
        head.next = tail;
        tail.prev = head;
        this.capacity = capacity;
    }

    // TC: O(1)
    // SC: O(1)
    public int get(int key) {
        if (map.containsKey(key)) {
            Node n = map.get(key);
            remove(n);
            add(n);
            return n.value;
        }
        return -1;
    }

    // TC: O(1)
    // SC: O(1)
    public void put(int key, int value) {
        if (map.containsKey(key)) {
            Node n = map.get(key);
            remove(n);
            n.value = value;
            add(n);
            map.put(key, n);
        } else {
            Node newNode = new Node(key, value);
            add(newNode);
            map.put(key, newNode);
        }
        if (map.size() > capacity) {
            Node toBeRemoved = tail.prev;
            remove(toBeRemoved);
            map.remove(toBeRemoved.key);
        }
    }

    // TC: O(1)
    // SC: O(1)
    private void add(Node n) {
        Node headNext = head.next;
        head.next = n;
        n.next = headNext;
        n.prev = head;
        headNext.prev = n;
    }

    // TC: O(1)
    // SC: O(1)
    private void remove(Node n) {
        n.next.prev = n.prev;
        n.prev.next = n.next;
    }
}

class Node {
    int key;
    int value;
    Node next;
    Node prev;
    Node(int key, int value) {
        this.key = key;
        this.value = value;
    }
}

/**
 * Your LRUCache object will be instantiated and called as such:
 * LRUCache obj = new LRUCache(capacity);
 * int param_1 = obj.get(key);
 * obj.put(key,value);
 */