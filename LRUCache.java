// Time Complexity : O(1) for put and get operations
// Space Complexity : O(2*capacity) for linked list and hash map
// Did this code successfully run on Leetcode : Yes
// Any problem you faced while coding this : No


/*Approach
 * We are using Doubly linked list to maintain the order of insertion in the cache and improve the removal of the node.
 * Using Hash map to optimize the look if the key is present in the cache. Capacity of the cahce is checked using the
 * size of the map. Any update to cache, node is added to the head of the linked list.
 * */


import java.util.HashMap;
import java.util.Map;

public class LRUCache {

    int capacity;
    Node head;
    Node tail;
    Map<Integer, Node> map;
    public LRUCache(int capacity) {
        this.capacity = capacity;
        head = new Node(-1,-1);
        tail = new Node(-1,-1);
        head.next=tail;
        tail.prev=head;
        map = new HashMap<>();
    }

    private void removeNode(Node node){
        node.prev.next = node.next;
        node.next.prev = node.prev;
    }

    private void addToHead(Node node){
        node.prev = head;
        node.next = head.next;
        head.next = node;
        node.next.prev = node;
    }

    public int get(int key) {
        if(!map.containsKey(key)) return -1;

        Node node = map.get(key);
        removeNode(node);
        addToHead(node);
        return node.value;
    }

    public void put(int key, int value) {
        //if the key is present in the map
        if(map.containsKey(key)){
            Node node = map.get(key);
            node.value = value;
            removeNode(node);
            addToHead(node);
        }
        else {// Key not present in the map
            Node newNode = new Node(key, value);
            if(map.size()==capacity){
                //since capacity is full we need to remove the element
                //previous of tail and from map
                Node prevTail = tail.prev;
                removeNode(prevTail);
                map.remove(prevTail.key);
            }
            addToHead(newNode);
            map.put(key, newNode);
        }
    }

    class Node{
        int key, value;
        Node next, prev;

        public Node(int key, int value){
            this.key= key;
            this.value=value;
        }
    }
}
