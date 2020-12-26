// Time Complexity :  O(1) 
// Space Complexity : O(N) 
// Did this code successfully run on Leetcode :Yes
// Any problem you faced while coding this :No
//map to store key value pairs

class LRUCache {
    class Node{
        int key;
        int val;
        Node next;
        Node prev;
        Node(int key, int val){
            this.key = key;
            this.val = val;
        }
    }
    Map<Integer, Node> map;//to store key value pairs
    int cap;
    Node head;
    Node tail;
    
    public LRUCache(int capacity) {
        head = new Node(-1,-1);
        tail = new Node(-1,-1);
        cap = capacity;
        map = new HashMap<>();
        head.next = tail;
        tail.prev = head;
    }
    private void addTohead(Node node){
        node.next = head.next;
        node.prev = head;
        head.next = node;
        node.next.prev = node;
    }
    
    private void removeNode(Node node){
        node.prev.next = node.next;
        node.next.prev = node.prev;
    }
    
    public int get(int key) {
        if(!map.containsKey(key)){
            return -1;   
        }else{
            Node node = map.get(key);
            removeNode(node);
            addTohead(node);
            return node.val;
        }
    }
    
    public void put(int key, int value) {
        if(!map.containsKey(key)){
            if(map.size() == cap){
                Node r = tail.prev;
                removeNode(r);
                map.remove(r.key);
            }
            Node temp = new Node(key,value);
            map.put(key,temp);
            addTohead(temp);
        }
        else{
            Node node = map.get(key);
            node.val = value;
            removeNode(node);
            addTohead(node);
        }
    }
}

/**
 * Your LRUCache object will be instantiated and called as such:
 * LRUCache obj = new LRUCache(capacity);
 * int param_1 = obj.get(key);
 * obj.put(key,value);
 */