class LRUCache {
    public class Node{
        int key;
        int val;
        Node next;
        Node prev;
        public Node(int key,int val){
            this.key = key;
            this.val = val;
        }
    }
    int capacity = 0;
    HashMap<Integer, Node> map;
    Node head;
    Node tail;

    public LRUCache(int capacity) {
        head = new Node(-1,-1);
        tail = new Node(-1,-1);
        head.next = tail;
        head.prev = null;
        tail.next = null;
        tail.prev = head;
        this.capacity = capacity;
        map = new HashMap<>();
    }
    
    public int get(int key) {
        if(!map.containsKey(key)) return -1;
        Node n = map.get(key);
        remove(n);
        addToHead(n);
        return n.val;
    }
    
    public void put(int key, int value) {
        if(map.containsKey(key)){
            Node n = map.get(key);
            n.val = value;
            remove(n);
            addToHead(n);
            return;
        }

        if(capacity == map.size()){
            Node newN = new Node(key, value);
            map.remove(removeTail());
            addToHead(newN);
            map.put(key, newN);
        }else{
            Node newN = new Node(key, value);
            map.put(key, newN);
            addToHead(newN);
        }
    }

    private void remove(Node node){
        node.prev.next = node.next;
        node.next.prev = node.prev;
    }

    private void addToHead(Node node){
        node.next = head.next;
        node.prev = head;
        node.next.prev = node;
        head.next = node;
    }

    private int removeTail(){
        int key = tail.prev.key;
        tail.prev.prev.next = tail;
        tail.prev = tail.prev.prev;
        return key;
    }
}
