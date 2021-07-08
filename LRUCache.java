//Time Complexity :O(1) for get operation
//Space Complexity :O(capacity) for storing values.
//Did this code successfully run on Leetcode :yes
//Any problem you faced while coding this :When trying to update the pointer at hasNext() i felt little difficult;

class LRUCache {
    class Node{
        int key, value;
        Node prev = null, next = null;
        public Node(int key, int value){
            this.key = key;
            this.value = value;
        }
    }
    private Map<Integer,Node> cache;
    private Node head, tail;
    private int size, count;
    public LRUCache(int capacity) {
        cache = new HashMap<>();
        head = new Node(0,0);
        tail = new Node(0,0);
        head.next = tail;
        tail.prev = head;
        head.prev = null;
        tail.next = null;
        size = capacity;
        count = 0;
    }
    
    private void deleteNode(Node node){
        node.prev.next = node.next;
        node.next.prev = node.prev;
    }
    
    private void addNode(Node node){
        node.next = head.next;
        node.next.prev = node;
        head.next = node;
        node.prev = head;
    }
    
    public int get(int key) {
        Node node = cache.get(key);
        if(null != node){
            deleteNode(node);
            addNode(node);
            return node.value;
        }
    return -1;
    }
    
    public void put(int key, int value) {
        Node node = cache.get(key);
        if(null != node){
            node.value = value;
            deleteNode(node);
            addNode(node);
        }else{
            node = new Node(key,value);
            cache.put(key,node);
            if(count < size){
                count++;
                addNode(node);
            }else{
                cache.remove(tail.prev.key);
                deleteNode(tail.prev);
                addNode(node);
            }
        }
    }
}