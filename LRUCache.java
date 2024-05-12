package Design-3;
// TC: O(1)
// SC: O(1)
public class LRUCache {
    int capacity;
    HashMap<Integer, Node> map;
    Node head;
    Node tail;

    class Node {
        int key;
        int value;
        Node next;
        Node prev;

        public Node(int key, int value) {
            this.key = key;
            this.value = value;
        }
    }

    public LRUCache(int capacity) {
        this.capacity = capacity;
        this.map = new HashMap<>();
        this.head = new Node(-1, -1);
        this.tail = new Node(-1, -1);
        head.next = tail;
        tail.prev = head;
    }

    public void removeNode(Node node) {
        node.prev.next = node.next;
        node.next.prev = node.prev;
    }

    public void addNode(Node node) {
        node.next = head.next;
        node.prev = head;
        head.next = node;
        node.next.prev = node;
    }

    public int get(int key) {
        if (map.containsKey(key)) {
            Node node = map.get(key);
            removeNode(node);
            addNode(node);
            return node.value;
        } else
            return -1;
    }

    public void put(int key, int value) {
        if(!map.containsKey(key)){
            if(map.size() == capacity){
                Node tailPrev = this.tail.prev;
                removeNode(tailPrev);
                map.remove(tailPrev.key);
            }
        }else{
            Node node = map.get(key);
            removeNode(node);
        }
        Node newNode = new Node(key,value);
        map.put(key, newNode);
        addNode(newNode);
    }
}
