// Time Complexity : O(1)
// Space Complexity : O(n) n : capacity
// Did this code successfully run on Leetcode : yes
// Any problem you faced while coding this : No


// Your code here along with comments explaining your approach
// whenever access any node remove from list if exist
// add all access node at beggining

class LRUCache {
    int len;
    Map<Integer,Node> map;
    Node head;
    Node tail;
    public LRUCache(int capacity) {
        len = capacity;
        map = new HashMap<>();
    }
    private void removeTail(){
        tail = tail.prev;
        //tail.next.prev = null;
        tail.next = null;
    }
    private void remove(Node node){
        if(node == tail) removeTail();
        else{
            node.prev.next = node.next;
            node.next.prev = node.prev;
            node.prev = null;
            node.next = null;
        }
    }
    private void addToHead(Node node){
        if(head != null) {
            node.next = head;
            head.prev = node;
        }
        head = node;
    }
    public int get(int key) {
        //System.out.println(key);
        Node curr = map.get(key);
        if(curr == null) return -1;
        if(curr != head) {
            remove(curr);
            addToHead(curr);
        }
        return curr.val;
    }
    
    public void put(int key, int value) {
        //System.out.println(key);
        Node curr = map.get(key);
        if(curr == null){ 
            curr = new Node(); 
            curr.key = key;
            map.put(key,curr);
            addToHead(curr);
        } else if(curr != head) {
            remove(curr);
            addToHead(curr);
        }
        curr.val = value;
        if(tail == null) tail = curr;
        if(map.size()>len){
            int remove = tail.key;
            removeTail();
            map.remove(remove);
            //System.out.println("removed : " + remove + " " + map.size());
        }
    }
    class Node{
        int key;
        int val;
        Node prev;
        Node next;
    }
}