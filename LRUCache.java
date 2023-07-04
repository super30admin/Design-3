class LRUCache {

    class Node{
        int key; int value;
        Node prev; Node next;
        Node(int key, int value){
            this.key = key;
            this.value = value;
        }
    }

    Node head;
    Node tail; 
    int capacity;
    HashMap<Integer,Node> map;

    private void addToHead(Node curr){
        curr.next = head.next;
        curr.prev = head;
        head.next = curr;
        curr.next.prev = curr;
    }

    private void removeFromList(Node curr){
        curr.prev.next = curr.next;
        curr.next.prev = curr.prev;
    }

    public LRUCache(int capacity) {
        this.head = new Node(-1,-1);
        this.tail = new Node(-1,-1);
        this.head.next = this.tail;
        this.tail.prev = this.head;
        this.capacity = capacity;
        this.map = new HashMap<>();     
    }
    
    public int get(int key) {
        if(!map.containsKey(key)) return -1;
        Node curr = map.get(key);
        removeFromList(curr);
        addToHead(curr);
        return curr.value;
    }
    
    public void put(int key, int value) {
        if(!map.containsKey(key)){
            Node curr = new Node(key,value);
            if(map.size()==capacity){
                Node toRemove = tail.prev;
                removeFromList(toRemove);
                map.remove(toRemove.key);
                addToHead(curr);
                map.put(key,curr);
            }else{
                addToHead(curr);
                map.put(key,curr);
            }
        }else{
            Node curr = map.get(key);
            curr.value = value;
            removeFromList(curr);
            addToHead(curr);
        }
    }
}

/**
 * Your LRUCache object will be instantiated and called as such:
 * LRUCache obj = new LRUCache(capacity);
 * int param_1 = obj.get(key);
 * obj.put(key,value);
 */