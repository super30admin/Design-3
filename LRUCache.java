package com.ds.rani.design;

import java.util.HashMap;
import java.util.Map;

//approach:solved using hashmap and doubly linkedlist, hashmap stores key and refrence to the node
///doubly linkedlist to maintain insertion order and easy deletion when we have access to that node only
//time complexity:o(1)
//space complexity: o(1)

public class LRUCache {

    //Node for doubly linked list
    class Node {
        int key;
        int val;
        Node prev;
        Node next;

        Node(int key, int val) {
            this.key = key;
            this.val = val;
        }

    }

    int capacity;
    Map<Integer, Node> map;
    Node head;
    Node tail;

    public LRUCache(int capacity) {
        this.capacity = capacity;
        map = new HashMap<>();
        head = new Node( -1, -1 );
        tail = new Node( -1, -1 );
        head.next = tail;
        tail.prev = head;
    }

    //recently used will be added after head, head is dummy node ,we always add new node after head
    private void addToHead(Node curr) {
        curr.prev = head;
        curr.next = head.next;
        curr.next.prev = curr;
        head.next = curr;

    }

    private void deleteNode(Node node) {
        node.next.prev = node.prev;
        node.prev.next = node.next;
    }


    public int get(int key) {
        if (map.containsKey( key )) {
            Node curr = map.get( key );
            deleteNode( curr );
            addToHead( curr );
            return curr.val;
        }
        return -1;
    }

    public void put(int key, int val) {
        //case 1: if key already exists in the map
        if (map.containsKey( key )) {
            //delete existing entry and add the    new node at head
            Node curr = map.get( key );
            curr.val = val;
            deleteNode( curr );
            addToHead( curr );
        } else {
            Node curr = new Node( key, val );
            //case 2: map doesnt contains entry and it doesnt has space to add new entry
            if (map.size() == capacity) {
                //delete the last node in linkedlist  and from map to make a space  for new entry
                Node tailPrev = tail.prev;
                deleteNode( tailPrev );
                map.remove( tailPrev.key );

            }
            addToHead( curr );
            map.put( key, curr );

        }
    }
}
