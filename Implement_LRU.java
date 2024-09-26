// Time Complexity :
// Space Complexity : 
// Did this code successfully run on Leetcode : Yes
// Any problem you faced while coding this : Used the front and rear concepts of the queue but then relaized I didn't need them


// Your code here along with comments explaining your approach

class LRUCache {
    
    int capacity;
    HashMap<Integer,Node> map = new HashMap<Integer,Node>();
    Node head = null;
    Node tail = null;
    
    class Node{
        int key;
        int value;
        Node pre;
        Node next;
        public Node(int key, int value){
            this.key = key;
            this.value=value;
        }
    }

    public LRUCache(int capacity) {
        this.capacity = capacity;
    }
    
    public int get(int key) {
        if(map.containsKey(key)){
            Node n = map.get(key);
            remove(n);
            setHead(n);
            return n.value;
        }
        return -1;
    }
    
    public void put(int key, int value) {
        //If key already exists
        if(map.containsKey(key)){
            Node old = map.get(key);
            old.value = value;
            remove(old);
            setHead(old);
        }
        else{
            Node created = new Node(key,value);
            //If capacity reached
            if(map.size()>=capacity){
                map.remove(tail.key);
                remove(tail);
                setHead(created);
            }
            else{
                setHead(created);
            }
            map.put(key,created);
        }
    }