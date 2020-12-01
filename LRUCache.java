// Time Complexity : O(1) - storing the ref in HM
// Space Complexity : O(n)
// Did this code successfully run on Leetcode : Yes
// Any problem you faced while coding this : No


// Your code here along with comments explaining your approach
//Using doubly linked list to maintain the LRU and storing the ref to get and put and remove the elements.
import java.util.HashMap;

public class LRUCache {
    class Node{
        int key;
        int val;
        Node prev; Node next;

        public Node(int key, int val){
            this.key = key;
            this.val = val;
        }
    }

    Node head;
    Node tail;

    private void addToHead(Node node){
        node.next = head.next;
        node.prev = head;
        head.next = node;
        node.next.prev = node;
    }

    private void removeNode(Node node){
        node.prev.next = node.next;
        node.next.prev = node.prev;
    }

    HashMap<Integer,Node> map;
    int capacity;
    public LRUCache(int capacity) {
        this.capacity = capacity;
        map = new HashMap<>();
        head = new Node(-1,-1);
        tail = new Node(-1,-1);
        head.next = tail;
        tail.prev = head;

    }

    public int get(int key) {
        if(!map.containsKey(key)){
            return -1;
        }
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
            Node newnode = new Node(key,value);
            if(capacity == map.size()){
                Node tailprev = tail.prev;
                removeNode(tailprev);
                map.remove(tailprev.key);
            }
            addToHead(newnode);
            map.put(key,newnode);
        }



    }
}
