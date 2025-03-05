// Time Complexity : O(1) get O(1) put
// Space Complexity : O(n) capacity of the cache
// Did this code successfully run on Leetcode : Yes
// Any problem you faced while coding this : No


// Your code here along with comments explaining your approach
/*
Doubly Linked List and a map to improve search of an element
 */

class LRUCache {

    class Node{
        int key;
        int val;
        Node next;
        Node prev;
        Node(int key,int val){
            this.key=key;
            this.val=val;
        }
    }

    Node head;
    Node tail;
    HashMap<Integer,Node> map;
    int capacity;

    public LRUCache(int capacity) {
        this.map=new HashMap<>();
        this.head=new Node(0,0);
        this.tail=new Node(0,0);
        this.head.next=this.tail;
        this.tail.prev=this.head;
        this.capacity=capacity;
    }

    private void remove(Node node){
        node.next.prev=node.prev;
        node.prev.next=node.next;
    }

    private void addToHead(Node node){
        node.next=this.head.next;
        node.prev=this.head;
        this.head.next=node;
        node.next.prev=node;
    }

    public int get(int key) {
        if(!map.containsKey(key)) return -1;
        Node node=map.get(key);
        remove(node);
        addToHead(node);
        return node.val;
    }

    public void put(int key, int value) {
        if(map.containsKey(key)){
            Node node=map.get(key);
            node.val=value;
            remove(node);
            addToHead(node);
        }
        else {
            Node node=new Node(key,value);
            if(map.size()==capacity){
                Node prev=tail.prev;
                remove(prev);
                map.remove(prev.key);
            }
            addToHead(node);
            map.put(key,node);
        }
    }
}

/**
 * Your LRUCache object will be instantiated and called as such:
 * LRUCache obj = new LRUCache(capacity);
 * int param_1 = obj.get(key);
 * obj.put(key,value);
 */


