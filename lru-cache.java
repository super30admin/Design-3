// Time - O(1)
// Space - O(N)
class LRUCache {

    
    class Node {
        Node next;
        Node prev;
        int key;
        int value;
        public Node(int key, int value) {
            this.key = key;
            this.value = value;
        }
    }
    Node head, tail;
    int cap;        
    HashMap<Integer, Node> map;
    
    public LRUCache(int capacity) {
        map = new HashMap<>();
        head = new Node(-1, -1);
        tail = new Node(-1, -1);
        head.next= tail;
        tail.prev = head;
        cap = capacity;
    }
    
    private void remove(Node curr) {
        curr.next.prev = curr.prev;
        curr.prev.next = curr.next;
        
    }
    
    private void addToStart(Node curr) {
        curr.next = head.next;
        head.next.prev = curr;
        curr.prev = head;
        head.next = curr;
    }
    
    public int get(int key) {
        if(map.containsKey(key)) {
            Node curr = map.get(key);
            remove(curr);
            addToStart(curr);
            
            return curr.value;
        }
        return -1;
    }
    
    public void put(int key, int value) {
        Node curr = head;
        if(map.containsKey(key)) {
            curr = map.get(key);
            curr.value = value;
            remove(curr);
            addToStart(curr);
            return;
        }
        else if (cap == map.size()) {
            int K = tail.prev.key;
            remove(tail.prev);
            map.remove(K);
        }
        curr = new Node(key, value);
        addToStart(curr);
        map.put(key, curr);
    }
}
