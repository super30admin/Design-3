import java.util.*;
class LRUCache {
    //https://leetcode.com/problems/lru-cache/
    public class Node{
        int key;
        int val;
        Node prev;
        Node next;
        
        public Node(int key,int val){
            this.key = key;
            this.val = val;
        }
        
    }
    private void removenode(Node node){
        node.prev.next = node.next;
        node.next.prev = node.prev;
    }
    private void addtohead(Node node){
        node.next = head.next;
        node.prev = head;
        head.next = node;
        node.next.prev = node;
    }
    HashMap<Integer,Node> map;
    int capacity;
    Node head;
    Node tail;
    public LRUCache(int capacity) {
        map = new HashMap<>();
        this.capacity = capacity;
        head = new Node(-1,-1);
        tail = new Node(-1,-1);
        head.next = tail;
        tail.prev= head;
    }
    // time complexity : 1
   // space complexity : 1
   // did it run on leetcode : yes
   // any doubts : no 
    public int get(int key) {
        if(!map.containsKey(key)) return -1;
        Node node = map.get(key);
        removenode(node);
        addtohead(node);
        return node.val;
        
    }
    // time complexity : 1
   // space complexity : 1
   // did it run on leetcode : yes
   // any doubts : no 
    public void put(int key, int value) {
        if(map.containsKey(key)){
            Node node = map.get(key);
            node.val = value;
            removenode(node);
            addtohead(node);
        }else{
            if(capacity == map.size()){
                Node tailprev = tail.prev;
                removenode(tailprev);
                map.remove(tailprev.key);
            }
            Node newNode = new Node(key,value);
            addtohead(newNode);
            map.put(key,newNode);
        }
    }
}