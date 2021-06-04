// Did this code successfully run on Leetcode : Yes 
// Any problem you faced while coding this : No

//TimeComplexity - O(1) for all operations, since we are either removing/adding the node to head based 
//SpaceComplexity - 2O(n) Where n is number of nodes inside the Doubly Linked List or HashMap 
class LRUCache {
    
    class Node{
        int key; int val;
        Node prev; Node next;
        public Node(int key, int val){
            this.key = key;
            this.val = val;
        }
    }
    
    private void removeNode(Node node)
    {
        node.next.prev = node.prev;
        node.prev.next = node.next;
    }

    private void addToHead(Node node){
        node.next = head.next;
        node.prev = head;
        head.next = node;
        node.next.prev = node;        
    }
    HashMap<Integer, Node> map;
    int capacity;
    Node head;
    Node tail;

    public LRUCache(int capacity) {
        this.capacity = capacity;
        map = new HashMap<>();
        head = new Node(-1, -1);
        tail = new Node(-1, -1);
        head.next = tail;
        tail.prev = head;
    }
    
    public int get(int key) {
        if(!map.containsKey(key)) return -1;
        Node node = map.get(key);
        removeNode(node);
        addToHead(node);
        return node.val;
    }
    
    public void put(int key, int value) {
        if(map.containsKey(key)){
            Node node = map.get(key);
            node.val = value;
            removeNode(node);
            addToHead(node);
        }else{
            if(capacity == map.size()){
                Node tailPrev = tail.prev;
                removeNode(tailPrev);
                map.remove(tailPrev.key);
            }
            Node newNode = new Node(key, value);
            addToHead(newNode);
            map.put(key, newNode);
        }
    }
}

