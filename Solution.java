class LRUCache {
    class Node {
        Node prev;
        Node next;
        int value;
        int key;

        public Node(int key, int value) {
            this.key = key;
            this.value = value;
        }
    }

    HashMap<Integer, Node> map;
    Node head;
    Node tail;
    int capacity;

    public LRUCache(int capacity) {
        this.capacity = capacity;
        map = new HashMap<>();
        head = new Node(-1, -1);
        tail = new Node(-1, -1);
        head.next = tail;
    }

    public int get(int key) {
        if (map.containsKey(key)) {
            insertFirst(map.get(key));
            return map.get(key).value;
        }
        return -1;
    }

    public void removeLast() {
        map.remove(tail.prev.key);
        Node prev = tail.prev.prev;
        prev.next = tail;
        tail.prev = prev;
    }

    public void insertFirst(Node curr) {
        if (curr.next != null && curr.prev != null) {
            curr.prev.next = curr.next;
            curr.next.prev = curr.prev;
        }
        curr.next = head.next;
        head.next = curr;
        curr.prev = head;
        curr.next.prev = curr;
        map.put(curr.key, curr);
    }

    public void put(int key, int value) {
        if (map.containsKey(key)) {
            Node curr = map.get(key);
            curr.value = value;
            map.put(key, curr);
            insertFirst(curr);
        } else {
            if (map.size() == capacity)
                removeLast();
            insertFirst(new Node(key, value));
        }
    }
}
