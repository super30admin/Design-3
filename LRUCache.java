package Design_3;

import java.util.HashMap;

/**
 * We use doubly linked lists to maintain O(1) insertion and deletion at the
 * head and tail. Also the get access to nodes in O(1) we maintain a hashmap. The
 * most recently used nodes are pushed to the head of the list and the least
 * recently used ones will remain to the tail.
 * 
 * Time Complexity : get() - O(1) put() - O(1)
 * 
 * Space Complexity : O(n), where n is the size capacity of the LRU cache.
 * 
 * Did this code successfully run on Leetcode : yes
 * 
 * Any problem you faced while coding this : No
 */
class LRUCache {
    int capacity;
    class Node{
        int value;
        int key;
        Node next;
        Node prev;
         
        Node(int key, int value){
             this.value=value;
             this.key=key;
         }
    }
    Node head=new Node(-1,-1);
    Node tail=new Node(-1,-1);

    HashMap  <Integer, Node> map;

    public LRUCache(int capacity) {
        head.next=tail;
        tail.prev=head;
        this.capacity=capacity;
        map=new HashMap();
    }
    
    public int get(int key) {
        if(!map.containsKey(key))
            return -1;
        Node node=map.get(key);
        removeNode(node);
        addNode(node);
        return node.value;
    }
    
    public void put(int key, int value) {
        if(map.containsKey(key)){
            Node node=map.get(key);
            node.value=value;
            removeNode(node);
            addNode(node);
        }
        else{
            if(map.size()==capacity){
            Node node=tail.prev;
            removeNode(node);
            map.remove(node.key);
        }
        Node node=new Node(key,value);
        map.put(key, node);
        addNode(node);

        }
    }


    void removeNode(Node node){
        node.prev.next=node.next;
        node.next.prev=node.prev;
    }

    void addNode(Node node){
        node.next=head.next;;
        node.prev=head;
        node.next.prev=node;
        head.next=node;
    }
}

