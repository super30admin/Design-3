//TC: O(1)  
//SC: O(1)

class LRUCache {
    class Node{
        int key;
        int val;
        Node next;
        Node prev;
        public Node(int key, int val){
            this.key = key;
            this.val = val;
        }
    }
    private Node head;
    private Node tail;
    int capacity;
    private HashMap<Integer, Node> map;

    public void removeNode(Node node){
        node.prev.next = node.next;
        node.next.prev= node.prev;
    }

    public void addToHead(Node node){
        node.next= head.next;
        node.prev = head;
        head.next = node;
        node.next.prev = node;
    }

    public LRUCache(int capacity) {
        this.head = new Node(-1,-1);
        this.tail = new Node(-1,-1);
        this.head.next = tail;
        this.tail.prev = head;
        this.capacity = capacity;
        this.map = new HashMap<>();

    }

    public int get(int key) {
        if(!map.containsKey(key)){
            return -1;
        }
        Node curr = map.get(key);
        removeNode(curr);
        addToHead(curr);
        return curr.val;
    }

    public void put(int key, int value) {
        if(map.containsKey(key)){
            Node curr = map.get(key);
            curr.val = value;
            removeNode(curr);
            addToHead(curr);
        }else{
            if(map.size()==capacity){
                Node tailPrev = tail.prev;
                removeNode(tailPrev);
                map.remove(tailPrev.key);
            }
            Node newNode = new Node(key,value);
            addToHead(newNode);
            map.put(key,newNode);
        }
    }
}
