import java.util.*;
public class LRUCache {
    class Node{
        int key;
        int val;
        Node prev;
        Node next;
        public Node(int key,int val){
            this.val = val;
            this.key = key;
        }
    }
    HashMap<Integer,Node> map;
    Node head;
    Node tail;
    int capacity;
    public LRUCache(int capacity) {
        this.map = new HashMap<>();
        this.head = new Node(-1,-1);
        this.tail = new Node(-1,-1);
        this.head.next=this.tail;
        this.tail.prev=this.head;
        this.capacity = capacity;
    }
    private void remove(Node node){
        node.next.prev = node.prev;
        node.prev.next = node.next;
    }

    private void addatHead(Node node){
        node.next = head.next;
        node.prev = head;
        head.next = node;
        node.next.prev = node;
    }


    public int get(int key) {
        if(!map.containsKey(key))return -1;
        Node node = map.get(key);
        remove(node);
        addatHead(node);
        return node.val;

    }

    public void put(int key, int value) {
        //fresh node
        if(!map.containsKey(key)){
            Node newnode = new Node(key,value);

            if(map.size()==capacity){
                Node beforeTail = tail.prev;
                remove(beforeTail);
                map.remove(beforeTail.key);
            }
            addatHead(newnode);
            map.put(key,newnode);
        }
        //not a fresh node
        else{
            Node existing = map.get(key);
            existing.val = value;
            remove(existing);
            addatHead(existing);
        }
    }
}
