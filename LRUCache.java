import java.util.HashMap;
import java.util.Map;

//Time Complexity : get,Put - O(1)
//Space Complexity :O(1) because its user define data structure
//Did this code successfully run on Leet code :Yes
//Any problem you faced while coding this :
class LRUCache {
    class Node{
        int key;
        int val;
        Node next;
        Node prev;
        public Node(int key,int val){
        this.key = key;    
        this.val = val;
            next=null;
            prev=null;
        }
    }
    int capacity;
    Node head;
    Node tail;
    Map<Integer,Node> map;
    
    public LRUCache(int capacity) {
        this.capacity=capacity;
        map = new HashMap<Integer,Node>();
        head = new Node(-1,-1);
        tail = new Node(-1,-1);
        head.next=tail;
        tail.prev=head;
    }
    
    public void addNodeAtFront(Node node){
        Node temp = head.next;
        head.next = node;
        node.prev = head;
        node.next = temp;
        temp.prev = node;
    }
    
    public void deleteNode(Node node){
        Node temp = node.next;
        node.prev.next = node.next;
        temp.prev = node.prev;
    }
    
    public int get(int key) {
        if(!map.containsKey(key)){
            return -1;
        }
       Node node = map.get(key);
       deleteNode(node);
       addNodeAtFront(node);
       return node.val;
    }
    
    public void put(int key, int value) {
        Node node = new Node(key,value);
       if(map.containsKey(key)){
             Node temp = map.get(key);
             deleteNode(temp);
             addNodeAtFront(node);
             map.put(key,node);
        }else{
            if(map.size() >= this.capacity){ 
                map.remove(tail.prev.key);
                deleteNode(tail.prev);
                addNodeAtFront(node);
               
            }else{
                addNodeAtFront(node);
            }
             map.put(key,node);
        }
    }
}

