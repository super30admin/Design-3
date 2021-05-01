//lru cache 
import java.util.*;
public class Problem2 {
    class Node{
        int key;
        int value;
        Node next;
        Node prev;
        Node(int key, int value){
            this.key = key;
            this.value = value;
        }
    }
    
    public void remove(Node node){
        node.prev.next = node.next;
        node.next.prev = node.prev;
        
        
    }
    public void addToHead(Node node){
        Node next = head.next;
        node.next = next;
        node.prev = head;
        head.next.prev = node;
        head.next = node;
        
    }
    HashMap<Integer, Node> map;
    Node head;
    Node tail;
    int cap;
    public Problem2(int capacity) {
        map = new HashMap<>();
        head = new Node(-1,-1);
        tail = new Node(-1,-1);
        head.next = tail;
        tail.prev = head;
        cap = capacity;
    }
    
    
    public int get(int key) {
        if(map.containsKey(key)){
            Node curr = map.get(key);
            remove(curr);
            addToHead(curr);
            int value = curr.value;
            return value;
            
        }
        else{
            return -1;
        }
        
    }
    
    public void put(int key, int value) {
        //if map contains
        if(map.containsKey(key)){
            Node curr = map.get(key);
            remove(curr);
            addToHead(curr);
            curr.value = value;
        }
        else{
        //if map does not contains
            // System.out.println(map.size());
            if(map.size() ==  cap){
                Node tailPrev = tail.prev;
                // System.out.println("prev "+tailPrev.value);
                remove(tailPrev);
                map.remove(tailPrev.key);
                Node curr = new Node(key, value);
                addToHead(curr);
                map.put(key, curr);
            }
            else{
                //evict the least recently used
                Node curr = new Node(key, value);
                addToHead(curr);
                map.put(key, curr);
                
            }
        }
        // System.out.println(map);
        
        
    }
    public static void main(String[] args){
        Problem2 p = new Problem2(2);
        p.put(1,1);   
        p.put(2,2);
        System.out.println(p.get(1));
        p.put(3,3);
        System.out.println(p.get(2));



            
    }
    

}
