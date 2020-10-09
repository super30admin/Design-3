import java.util.*;
/*
TC: 
get - O(1)
put - O(1)

SC: O(capacity) HashMap and LinkedList both will store atmost two entries.

*/


class LRUCache{

    Node head, tail;
    Map<Integer, Node> map;
    int size, capacity;

    public LRUCache(int capacity){
        map = new HashMap<>();
        head = new Node(0,0);
        tail = new Node(0,0);
        head.next = tail;
        tail.prev = head;
        this.capacity = capacity;
        this.size = 0;
    }

    public int get(int key){
        Node cur = map.get(key);
        if(cur == null) return -1;
        else{
            update(cur);
        }
        return cur.value;
    }
    
    public void put(int key, int value){
        Node cur = map.get(key);
        
        if(cur == null){
            Node n = new Node(key, value);
            map.put(key, n);
            add(n);
            size++;
        }else{
            cur.value = value;
            update(cur);
        }

        if(size > capacity){
            Node temp = head.next;
            remove(temp);
            size--;
            map.remove(temp.value);
        }
    }

    public void update(Node node){
        remove(node);
        add(node);
    }

    public void add(Node node){
        node.next = tail;
        node.prev = tail.prev;
        tail.prev = node;
        node.prev.next = node;
    }

    public void remove(Node node){
        node.prev.next = node.next;
        node.next.prev = node.prev;
    }

    public static void main(String[] args){
        LRUCache cache = new LRUCache(2);
        cache.put(2,1);
        cache.put(2,2);
        System.out.println(cache.get(2));
        cache.put(1,1);
        cache.put(4,1);
        System.out.println(cache.get(2));
    }
}

class Node{
    int key, value;
    Node next, prev;

    public Node(int key, int value){
        this.key = key;
        this.value = value;
    }
}