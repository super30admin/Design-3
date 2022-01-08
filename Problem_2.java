// Time Complexity : O(1)
// Space Complexity :O(1)
// Did this code successfully run on Leetcode : Yes
// Three line explanation of solution in plain english
// Create a doubli linkedList and HashMap of key and Node, In the node contains key, val, prev and next pointer. and Create two fucntion addToHead, removeNode
// Your code here along with comments explaining your approach
class LRUCache {

    class Node{
        int key; int val;
        Node prev; Node next;
        public Node(int key,int val){
            this.key = key;
            this.val = val;
        }
    }

    HashMap<Integer, Node> hm;
    Node head;
    Node tail;
    int capacity;

    private void removeNode(Node curr){
        curr.next.prev = curr.prev;
        curr.prev.next = curr.next;
    }

    private void addToTheHead(Node curr){
        curr.next = head.next;
        curr.prev = head;
        head.next = curr;
        curr.next.prev = curr;
    }

    public LRUCache(int capacity) {
        hm = new HashMap<>();
        head = new Node(-1,-1);
        tail = new Node(-1,-1);
        head.next = tail;
        tail.prev = head;
        this.capacity = capacity;
    }

    public int get(int key) {
        if(!hm.containsKey(key)) return -1;
        Node curr = hm.get(key);
        removeNode(curr);
        addToTheHead(curr);
        return curr.val;
    }

    public void put(int key, int value) {
        if(hm.containsKey(key)){
            Node curr = hm.get(key);
            curr.val = value;
            hm.put(key,curr);
            removeNode(curr);
            addToTheHead(curr);
        }else{
            if(capacity <= hm.size()){
                // hm is full;
                Node last = tail.prev;
                removeNode(last);
                hm.remove(last.key);
            }
            Node newNode = new Node(key, value);
            addToTheHead(newNode);
            hm.put(key, newNode);
        }
    }
}

/**
 * Your LRUCache object will be instantiated and called as such:
 * LRUCache obj = new LRUCache(capacity);
 * int param_1 = obj.get(key);
 * obj.put(key,value);
 */