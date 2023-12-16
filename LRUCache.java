import java.util.HashMap;

public class LRUCache {
    // We will add 2 dummy nodes one at start and one at last
    // TC:O(1)
    // SC:O(n)
    int capacity;
    HashMap<Integer, Node> map;
    Node head;
    Node tail;
    class Node{
        int key;
        int value;
        Node next;
        Node prev;

        public Node(int key, int value){
            this.key = key;
            this.value = value;
        }
    }
    public LRUCache(int capacity) {
        this.capacity = capacity;
        this.map = new HashMap<>();
        this.head = new Node(-1,-1);
        this.tail = new Node(-1,-1);
        head.next = tail;
        tail.prev = head;
    }

    private void removeNode(Node node){
        node.prev.next = node.next;
        node.next.prev = node.prev;
    }

    private void addNodeToHead(Node node){
        node.prev = head;
        node.next = head.next;
        head.next = node;
        node.next.prev = node;
    }

    public int get(int key) {
        if(!map.containsKey(key)) return -1;
        Node temp = map.get(key);//as its accessed it should be moved first
        removeNode(temp);
        addNodeToHead(temp);
        return temp.value;
    }

    public void put(int key, int value) {
        if(map.containsKey(key)){
            Node temp = map.get(key);
            temp.value = value;
            removeNode(temp);
            addNodeToHead(temp);
        }else{
            if(map.size() == capacity){
                Node prev = tail.prev;
                map.remove(prev.key); // removing refference to node
                removeNode(prev);
            }
            Node newNode = new Node(key, value);
            addNodeToHead(newNode);
            map.put(key, newNode);
        }
    }
}

/**
 * Your LRUCache object will be instantiated and called as such:
 * LRUCache obj = new LRUCache(capacity);
 * int param_1 = obj.get(key);
 * obj.put(key,value);
 */