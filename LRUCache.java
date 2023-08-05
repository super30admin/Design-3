package org.example;
// Time Complexity : O(1)
// Space Complexity : O(n) -> n is the number of nodes(creation of nodes and hashmap)
// Did this code successfully run on Leetcode : Yes
import java.util.HashMap;

public class LRUCache {

    HashMap<Integer,Node> map;
    int capacity;
    Node head;
    Node tail;

    class Node
    {
        int key;
        int value;
        Node next;
        Node prev;

        public Node(int key, int value)
        {
            this.key = key;
            this.value = value;
        }
    }

    public void removeNode(Node node)
    {
        node.prev.next = node.next;
        node.next.prev = node.prev;
    }

    public void addNodetoHead(Node node)
    {
        node.prev = head;
        node.next = head.next;
        node.next.prev = node;
        head.next = node;
    }

    public LRUCache(int capacity) {
        this.map = new HashMap<>();
        this.capacity = capacity;
        head = new Node(-1,-1);
        tail = new Node(-1,-1);
        head.next = tail;
        tail.prev = head;
    }

    public int get(int key) {
        if(map.containsKey(key))
        {
            Node temp = map.get(key);
            removeNode(temp);
            addNodetoHead(temp);
            return temp.value;
        }
        else  return -1;
    }

    public void put(int key, int value) {

        if(map.containsKey(key))
        {
            Node temp = map.get(key);
            temp.value = value;
            removeNode(temp);
            addNodetoHead(temp);
        }else{
            Node newNode = new Node(key,value);
            if(map.size() == capacity)
            {
                Node prevNode = tail.prev;
                map.remove(prevNode.key);
                removeNode(prevNode);
                addNodetoHead(newNode);
                map.put(key,newNode);
            }
            else
            {
                addNodetoHead(newNode);
                map.put(key,newNode);
            }
        }
    }
}

/**
 * Your LRUCache object will be instantiated and called as such:
 * LRUCache obj = new LRUCache(capacity);
 * int param_1 = obj.get(key);
 * obj.put(key,value);
 */