package S30.Design_3;

/*
Time Complexity : Get - O(1), Put - O(1)
Space Complexity : O(N)
Did this code successfully run on Leetcode : Yes
Any problem you faced while coding this : None
*/

import java.util.HashMap;

class LRUCache {

    HashMap<Integer,Node> map;
    Node head = null;
    Node tail = null;
    int capacity = 0;

    public class Node {

        int key;
        int value;
        Node prev;
        Node next;

        public Node(int key,int value){
            this.key = key;
            this.value = value;
            this.prev = null;
            this.next = null;
        }
    }

    public LRUCache(int capacity) {
        this.map = new HashMap<>(capacity);
        head = new Node(-1,-1);
        tail = new Node(-1,-1);
        head.next = tail;
        tail.prev = head;
        this.capacity = capacity;
    }

    //to be used when evicting cache
    public Node popTail(){

        Node prev = tail.prev;
        removeNode(prev);
        return prev;


    }

    //used when adding new member to cache or when shifting any node to head
    public void addNode(Node node){

        Node nextNode = head.next;
        node.prev = head;
        head.next = node;
        node.next = nextNode;
        if(nextNode != null) nextNode.prev = node;


    }

    //remove a node from linked list - to be used when shifting a node to the front
    public void removeNode(Node node){

        Node nextNode = node.next;
        Node prevNode = node.prev;
        if(prevNode != null) prevNode.next = nextNode;
        if(nextNode != null) nextNode.prev = prevNode;


    }

    public int get(int key) {

        if(!map.containsKey(key)) return -1;

        Node result = map.get(key);
        int value = result.value;
        removeNode(result);
        addNode(result);
        return value;

    }

    public void put(int key, int value) {


        if(map.get(key) == null){
            Node newnode = new Node(key,value);
            addNode(newnode);
            map.put(key,newnode);

            //evict the cache
            if(map.size() > capacity){
                Node tail = popTail();
                map.remove(tail.key);
            }


        } else{
            Node oldnode = map.get(key);
            oldnode.value = value;
            map.put(key,oldnode);
            removeNode(oldnode);
            addNode(oldnode);
        }

    }
}

/**
 * Your LRUCache object will be instantiated and called as such:
 * LRUCache obj = new LRUCache(capacity);
 * int param_1 = obj.get(key);
 * obj.put(key,value);
 */
