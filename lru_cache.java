// Time Complexity : O(1)
// Space Complexity : O(n)
// Did this code successfully run on Leetcode : Yes
// Any problem you faced while coding this : No


// Your code here along with comments explaining your approach
class LRUCache {

    class Node {
        int key;
        int val;
        Node prev;
        Node next;
        public Node(int key,int val){
            this.key = key;
            this.val = val;
        }
    }
    Node head;
    Node tail;
    HashMap<Integer,Node> hm;
    int capacity;

    private void removeNode(Node node){
        node.prev.next = node.next;
        node.next.prev = node.prev;
    }

    private void addToHead(Node node){
        node.next = head.next;
        node.prev = head;
        head.next = node;
        node.next.prev = node;
    }

    public LRUCache(int capacity) {
        hm = new HashMap<>(capacity);
        this.capacity = capacity;
        head = new Node(-1,-1);
        tail = new Node(-1,-1);
        head.next = tail;
        tail.prev = head;
    }

    public int get(int key) {
        if(!hm.containsKey(key)){
            return -1;
        }
        Node cur = hm.get(key);
        removeNode(cur);
        addToHead(cur);
        return cur.val;
    }

    public void put(int key, int value) { 
        if(hm.containsKey(key)){
            Node cur = hm.get(key);
            cur.val = value;
            removeNode(cur);
            addToHead(cur);
            return;
        }
        if(hm.size() == capacity){
            Node tailPrev = tail.prev;
            removeNode(tailPrev);
            hm.remove(tailPrev.key);
        }
        Node cur = new Node(key,value);
        addToHead(cur);
        hm.put(key,cur);
    }
}