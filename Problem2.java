// Time Complexity : O(1)
// Space Complexity :O(1)
// Did this code successfully run on Leetcode :
// Any problem you faced while coding this :
class LRUCache {
    class Node{
        int val;
        int key;
        Node prev;
        Node next;
        public Node(){
            this.val = 0;
            this.prev = null;
            this.next = null;
        }
        public Node(int x,int y){
            this.key = x;
            this.val = y;
            this.prev = null;
            this.next = null;
        }
    }
    Node head; Node tail;
    Map<Integer,Node> map;
    int capacity; int count;
    public LRUCache(int capacity) {
        this.head = new Node(-1,-1);
        this.tail = new Node(-1,-1);
        this.map = new HashMap<>();
        head.next = tail;
        tail.prev = head;
        this.capacity = capacity;
        this.count = 0;
        
    }
    private void insert(Node curr){
        curr.next = head.next;
        head.next.prev = curr;
        curr.prev = head;
        head.next = curr;
    }
    private void remove(Node curr){
        curr.next.prev=curr.prev;
        curr.prev.next = curr.next;
    }
    
    public int get(int key) {
        if(!map.containsKey(key))
            return -1;
        Node curr = map.get(key);
        remove(curr);
        insert(curr);
        return curr.val;
    }
    
    public void put(int key, int value) {
        Node curr = map.get(key);
        if(curr == null){
            Node tmp = new Node(key,value);
            map.put(key,tmp);
            insert(tmp);
            count++;
            if(count > capacity){
                Node last = tail.prev;
                remove(last);
                map.remove(last.key);
                count--;
            }
        }
        else{
            curr.val = value;
            remove(curr);
            insert(curr);
        }

        
    }
}

/**
 * Your LRUCache object will be instantiated and called as such:
 * LRUCache obj = new LRUCache(capacity);
 * int param_1 = obj.get(key);
 * obj.put(key,value);
 */