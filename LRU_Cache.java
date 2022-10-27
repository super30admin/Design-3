// TC : O(1)
// SC : O(n) -> capacity
// LRU Cache Design using HashMap && doubly linked list

class LRUCache {

    class Node{
        int key, value;;
        Node next,prev;
        Node(int key, int value) {
            this.key = key;
            this.value = value;
        }
    }
    int capacity;
    int size = 0;
    Node head, tail;
    HashMap<Integer, Node> hMap;
    private void addNode(Node cur) {
        if(head.next == null) {
            head.next = cur;
            cur.prev = head;
            cur.next = tail;
            tail.prev = cur;
            size++;
            return;
        }
        
        Node temp = head.next;
        head.next = cur;
        cur.next = temp;
        temp.prev = cur;
        cur.prev = head;
        size++;   
    }
    private void removeNode() {
        hMap.remove(tail.prev.key);
        tail.prev = tail.prev.prev;
        tail.prev.next = tail;
        size--;
    }
    private void removeNode(Node cur) {
        cur.prev.next = cur.next;
        cur.next.prev = cur.prev;
        size--;
    }
    public LRUCache(int capacity) {
        this.capacity = capacity;
        head = new Node(-1, -1);
        tail = new Node(-1, -1);
        hMap = new HashMap<>();
    }
    
    public int get(int key) {
        if(!hMap.containsKey(key)) {
            return -1;
        }
        Node temp = hMap.get(key);
        removeNode(temp);
        addNode(temp);
        return hMap.get(temp.key).value;
    }
    
    public void put(int key, int value) {
        if(!hMap.containsKey(key)) {
            Node temp = new Node(key, value);
            if(size == capacity)
                removeNode();
            hMap.put(key, temp);
            addNode(temp);
            return;
        }
        Node temp = hMap.get(key);
        temp.value = value;
        removeNode(temp);
        addNode(temp);
        hMap.remove(key);
        hMap.put(key, temp);
    }
}

/**
 * Your LRUCache object will be instantiated and called as such:
 * LRUCache obj = new LRUCache(capacity);
 * int param_1 = obj.get(key);
 * obj.put(key,value);
 */
