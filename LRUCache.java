/**
 * Time Complexity : O(1) 
 * Space Complexity : O(2n) for hashmap and doubly linked list
 */

import java.util.*;
public class LRUCache {
    class Node {
        Node next;                                              
        Node prev;
        int val;
        int key;
        Node(int k, int v){
            this.key = k;
            this.val = v;
            this.next = null;
            this.prev = null;
        }
    }
    
    int capacity;
    HashMap<Integer, Node> store;
    Node head;
    Node tail;

    public LRUCache(int capacity){
        this.store = new HashMap<>();                                                       
        this.capacity = capacity;
        this.head = new Node(-1,-1);
        this.tail = new Node(-1,-1);
        this.head.next = tail;                                                      
        this.tail.prev = head;
    }
    
    public void put(int key, int val){
        if(!store.containsKey(key)){
            if(capacity == store.size()){                                                   
                store.remove(tail.prev.key);                                            
                removeNode(tail.prev);                                                  
            }
            Node new_node = new Node(key, val);
            addToHead(new_node);                                                    
            store.put(key, new_node);
        } else {
            Node n = store.get(key);
            n.val = val;                                                           
            removeNode(n);                                                              
            addToHead(n);                                                           
        }
    }
    public int get(int key){
        if(!store.containsKey(key)){                                                
            return -1;
        } else {
            Node fetch = store.get(key);                                               
            removeNode(fetch);
            addToHead(fetch);                                                       
            return fetch.val;                                                       
        }
    }
    public void addToHead(Node fetch){
            fetch.next = head.next;                                                 
            fetch.prev = head;                                                      
            head.next = fetch;
            fetch.next.prev = fetch;                                               
    }
    
    public void removeNode(Node fetch){
        fetch.next.prev = fetch.prev;                                               
        fetch.prev.next = fetch.next;
    }
}