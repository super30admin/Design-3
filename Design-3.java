

//Q1 : LRU Cache

//hashmap to keep track of recently removed element 
//doubly linked list to add current elemetnt , remove LRU element and update

//Time Complexity : O(1) for get and put
//Space : O (n)

class Node{
    int key;
    int value;
    Node prev;
    Node next;
    
    public Node(int key, int value){
        this.key = key;
        this.value = value;
    }
}
class LRUCache {
    HashMap<Integer, Node> map;
    int capacity, count;
    Node head, tail;
    
    public LRUCache(int capacity) {
        this.capacity = capacity;
        map = new HashMap<>();
        head = new Node(0,0);
        tail = new Node(0,0);
        head.next = tail;
        tail.prev = head;
        head.prev = null;
        tail.next = null;
        count = 0;        
    }
    
    public void deleteNode(Node node){
        node.prev.next = node.next;
        node.next.prev = node.prev;
    }
    
    public void addToHead(Node node){
        node.next = head.next;
        node.next.prev = node;
        node.prev = head;
        head.next = node;
    }
    
    public int get(int key) {
        if(map.get(key)!=null){
            Node node = map.get(key);
            int result = node.value;
            deleteNode(node);
            addToHead(node);
            return result;
        }
        return -1;
    }
    
    public void put(int key, int value) {
        if(map.get(key)!=null){
            Node node = map.get(key);
            node.value = value;
            deleteNode(node);
            addToHead(node);
        }
        else{
            Node node = new Node(key, value);
            map.put(key,node);
            if(count<capacity){
                count++;
                addToHead(node);
            }
            else{
                map.remove(tail.prev.key);
                deleteNode(tail.prev);
                addToHead(node);
            }
        }
    }
}