// Time Complexity : O(1)
// Space Complexity : O(n)
// Did this code successfully run on Leetcode : yes
// Any problem you faced while coding this : no

public class LRUCache {

    class Node{
        int key;
        int val;
        Node prev;
        Node next;
        public Node(int key, int val){
            this.key = key;
            this.val = val;
        }
    }

    private void addtoHead(Node node){
        //put it in front of head
        node.next = head.next;
        node.prev = head;
        head.next = node;
        //reverse connection
        node.next.prev = node;
    }

    private void removeNode(Node node){
        //take out connection
        node.next.prev = node.prev;
        node.prev.next = node.next;
    }

    Node head;
    Node tail;
    int capacity;
    HashMap<Integer, Node> map;
    public LRUCache(int capacity) {
        //set new cache w/capacity
        map = new HashMap<>();
        this.capacity = capacity;
        head = new Node(-1, -1);
        tail = new Node(-1, -1);
        head.next = tail;
        tail.prev = head;
    }

    public int get(int key) {
        //if not exist
        if(!map.containsKey(key)) return -1;
        Node node = map.get(key);
        removeNode(node);
        addtoHead(node);
        return node.val;
    }

    public void put(int key, int value) {
        //if exists, change its value, if not create space for key/value -remove least used
        if(map.containsKey(key)){
            Node node = map.get(key);
            node.val = value;
            removeNode(node);
            addtoHead(node);
        }
        else{
            Node newNode = new Node(key, value);
            if(capacity == map.size()){
                Node tailPrev = tail.prev;
                removeNode(tailPrev);
                map.remove(tailPrev.key);
            }
            addtoHead(newNode);
            map.put(key, newNode);
        }
    }
}

/**
 * Your LRUCache object will be instantiated and called as such:
 * LRUCache obj = new LRUCache(capacity);
 * int param_1 = obj.get(key);
 * obj.put(key,value);
 */