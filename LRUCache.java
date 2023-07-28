// Time Complexity :O(1) for both put() and get()
// Space Complexity :O(n) where n is the capacity of the cache
// Did this code successfully run on Leetcode :yes
import java.util.HashMap;
public class LRUCache {
    class Node{
        private int key;
        private int value;
        private Node next;
        private Node prev;

        public Node(int key, int value){
            this.key = key;
            this.value = value;
        }
    }

    private Node head;
    private Node tail;
    private int capacity;
    private int count;
    private HashMap<Integer, Node> map;

    public LRUCache(int capacity) {
        this.capacity = capacity;
        this.map = new HashMap<>();
    }

    public int get(int key) {
        if(map.containsKey(key)){
            Node current = map.get(key);
            //reorder
            //current is head
            if(current == head){
                return current.value;
            }

            //current is at tail
            if(current == tail){
                tail = tail.next;
                current.prev = head;
                head.next = current;
                head = current;

            }else{
                Node prev = current.prev;
                Node next = current.next;

                current.prev = head;
                head.next = current;
                head = current;

                next.prev = prev;
                prev.next = next;
            }
            return current.value;
        }else{
            return -1;
        }
    }

    public void put(int key, int value) {
        //initial
        if(head == null){
            head = new Node(key, value);
            tail = head;
            map.put(key, head);
            count++;
            return;
        }

        //new page
        if(!map.containsKey(key)){
            //create a new node
            Node newNode = new Node(key, value);
            //attach to the head
            newNode.prev = head;
            head.next = newNode;
            head = newNode;
            //update the map
            map.put(key, newNode);
            count++;

            //now check if count exceeeded the capacity
            if(count > capacity){
                //update map
                map.remove(tail.key);
                //evict
                tail = tail.next;
                //reduce count
                count--;
            }
        }
        else{//existing page

            //get the node by key
            Node current = map.get(key);
            current.value = value;
            map.put(key, current);

            //reorder
            //current is head
            if(current == head){
                return;
            }

            //current is at tail
            if(current == tail){
                tail = tail.next;
                current.prev = head;
                head.next = current;
                head = current;

            }else{
                Node prev = current.prev;
                Node next = current.next;

                current.prev = head;
                head.next = current;
                head = current;

                next.prev = prev;
                prev.next = next;
            }
        }
    }
}
