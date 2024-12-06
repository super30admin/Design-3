class LRUCache {
    Map<Integer, Node> map;
    int capacity;
    Node head, tail;
    class Node{
        int key;
        int value;
        Node prev;
        Node next;

        public Node(int key, int value){
            this.key = key;
            this.value= value;
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
    
    public int get(int key) {
        if(!map.containsKey(key)){
            return -1;
        }
        Node tmp = map.get(key);
        removeNode(tmp);
        addToHead(tmp);
        return tmp.value;
    }
    
    public void put(int key, int value) {
        if(map.containsKey(key)){
            Node tmp = map.get(key);
            tmp.value = value;
            removeNode(tmp);
            addToHead(tmp);
        }else{
            if(map.size() == capacity){
                //remove lru node 
                Node lruNode = tail.prev;
                removeNode(lruNode);
                map.remove(lruNode.key);
            }
            Node curr = new Node(key,value);
            addToHead(curr);
            map.put(key, curr);
        }

    }

    public void removeNode(Node curr){
        curr.prev.next = curr.next;
        curr.next.prev = curr.prev;
        curr.next = null;
        curr.prev = null;
    }

    public void addToHead(Node curr){
        curr.next = head.next;
        curr.prev = head;
        head.next.prev = curr;
        head.next = curr;
    }
}