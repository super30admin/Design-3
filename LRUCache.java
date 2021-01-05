//Time complexity: O(1)
//Space complexity: O(N)
class LRUCache {

    class Node{
        int key, value;
        Node next, prev;
        public Node(int key, int value){
            this.key = key;
            this.value = value;
        }
    }
    
    Node head, tail;
    int cap;
    HashMap<Integer, Node> map;
    
    public LRUCache(int capacity) {
        head = new Node(-1,-1);
        tail = new Node(-1,-1);
        head.next = tail;
        tail.prev = head;
        cap = capacity;
        map = new HashMap();
    }
    
    private void remove(Node curr){
        curr.next.prev = curr.prev;
        curr.prev.next = curr.next;
    }
    private void addtobeg(Node curr){
        curr.next = head.next;
        head.next.prev = curr;
        curr.prev = head;
        head.next = curr;
    }
    
    public int get(int key) {
        if(map.containsKey(key)){
            Node curr = map.get(key);
            remove(curr);
            addtobeg(curr);
            return curr.value;
        }
        return -1;
    }
    
    public void put(int key, int value) {
        Node curr = head;
        if(map.containsKey(key)){
            curr = map.get(key);
            curr.value = value;
            remove(curr);
            addtobeg(curr);
            return;
        }
        else if(cap == map.size()){
            int Key = tail.prev.key;
            remove(tail.prev);
            map.remove(Key);
        }
        curr = new Node(key, value);
        addtobeg(curr);
        map.put(key, curr);
    }
}

/**
 * Your LRUCache object will be instantiated and called as such:
 * LRUCache obj = new LRUCache(capacity);
 * int param_1 = obj.get(key);
 * obj.put(key,value);
 */