// Time Complexity: O(n)
// Space Complexity: O(capacity)
class Node {
    int val;
    int key;
    Node next;
    Node prev;
}

class LRUCache {

    Map<Integer, Node> cache = new HashMap<>();
    public Node head;
    public Node tail;
    int capacity;
    int count = 0;

    private void addNode(Node node) {
        node.prev = head;
        node.next = head.next;

        head.next.prev = node;
        head.next = node;
    }

    private void removeNode(Node node) {
        Node prev = node.prev;
        Node next = node.next;

        prev.next = next;
        next.prev = prev;
    }

    private void moveToHead(Node node) {
        removeNode(node);
        addNode(node);
    }

    private Node popTail() {
        Node node = tail.prev;
        removeNode(node);
        return node;
    }
    public LRUCache(int capacity) {
        this.capacity = capacity;
        count = 0;
        head = new Node();
        head.prev = null;

        tail = new Node();
        tail.next = null;

        head.next = tail;
    }

    public int get(int key) {
        Node node = cache.get(key);
        if(node == null) {
            return -1;
        }

        moveToHead(node);
        return node.val;
    }

    public void put(int key, int value) {
        Node node = cache.get(key);
        if(node == null) {
            Node temp = new Node();
            temp.key = key;
            temp.val = value;

            addNode(temp);
            count++;
            cache.put(temp.key,temp);

            if(count > capacity) {
                Node removeNode =  popTail();
                cache.remove(removeNode.key);
                count--;
            }
        } else {
            node.val = value;
            moveToHead(node);
        }
    }
}

/**
 * Your LRUCache object will be instantiated and called as such:
 * LRUCache obj = new LRUCache(capacity);
 * int param_1 = obj.get(key);
 * obj.put(key,value);
 */