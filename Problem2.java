// Space Complexity :O(capacity) --> hashmap
// Did this code successfully run on Leetcode : Yes
// Any problem you faced while coding this : No

// Your code here along with comments explaining your approach
// 1 . We use a hashMap to store the key and value pairs and a doubly linked list to maintain the order of most recently used.
// 2 . We initialise a doubly linked list with two nodes, head and tail pointing at each other for ease of access and ease of inserting values.
// 3 . To get value of a key,check if its present in hashmap and return its value . Now add this after head.
// 4 . To insert a key into cache ,check if key is already present . If its present then change its value to new value and 
//     push node after head to maintain the order. 
// 5. Else , if capacity is not reached , insert it into hashmap and add it after head of linked list.If capacity is reached then add this 
//     new node after head and add it to the hashmap.Remove the node before  the tail.
import java.util.HashMap;

public class LRUCache {
    public class Node{
        int key;
        int value;
        Node next;
        Node prev;
        public Node(int key,int value){
            this.key = key;
            this.value = value;
            this.prev = null;
            this.next = null;
        }
    }
    int capacity;
    HashMap<Integer,Node> map = new HashMap<>();
    int count;
    Node head;
    Node tail;
    public LRUCache(int capacity) {
        this.capacity = capacity;
        this.count = 0;
        head = new Node(0,0);
        tail = new Node(0,0);
        head.next = tail;
        tail.prev = head;
    }
    public void addtoHead(Node node){
        node.next = head.next;
        head.next.prev = node;
        node.prev = head;
        head.next = node;

    }
    public void removeNode(Node node){
        node.prev.next = node.next;
        node.next.prev = node.prev;
    }
    // Time Complexity :O(1) --> getting from hashmap

    public int get(int key) {
        if(map.containsKey(key)){
            removeNode(map.get(key));
            addtoHead(map.get(key));
            return map.get(key).value;
        }
        return -1;
    }
    // Time Complexity :O(1) --> inserting after head of linked list.

    public void put(int key, int value) {
        if(map.containsKey(key)){
            Node node = map.get(key);
            node.value = value;
            removeNode(node);
            addtoHead(node);

        }
        else{
            if(count<capacity){
                count++;
                Node node = new Node(key,value);
                map.put(key,node);
                addtoHead(node);
            }
            else{
                Node node = new Node(key,value);
                map.put(key,node);
                map.remove(tail.prev.key);
                removeNode(tail.prev);
                addtoHead(node);
            }
        }
    }

    public static void main(String[] args) {
        LRUCache obj = new LRUCache(2);
        obj.put(2,1);
        obj.put(1,1);
        obj.put(2,3);
        obj.put(4,1);
        int x = obj.get(1);
        int y = obj.get(2);
    }
}

