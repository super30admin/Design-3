class LRUCache {

    class Node{
        int key; int val;
        Node prev; Node next;
        public Node(int key, int val){
            this.key = key;
            this.val = val;
        }
    }

    private void addToHead(Node node){
        node.next = head.next;
        head.next = node;
        node.prev = head;
        node.next.prev = node;

    }

    private void removeNode(Node node){
        node.next.prev = node.prev;
        node.prev.next = node.next;

    }

    private Node head;
    private Node tail;
    private HashMap<Integer, Node> map;
    private int capacity;

    public LRUCache(int capacity) {
        this.map = new HashMap<>();
        this.capacity = capacity;
        this.head = new Node(-1,-1);
        this.tail = new Node(-1,-1);
        this.head.next = this.tail;
        this.tail.prev= this.head;
        
    }
    
    public int get(int key) {
        if(!map.containsKey(key)) return -1;
        Node node = map.get(key);
        removeNode(node);
        addToHead(node);
        return node.val;
        
    }
    
    public void put(int key, int value) {
        if(map.containsKey(key)){
            Node node = map.get(key);
            node.val = value;
             removeNode(node);
             addToHead(node);

        }else{
            if(map.size() == capacity){
                Node tailprev = tail.prev;
                removeNode(tailprev);
                map.remove(tailprev.key);
            }
            Node newNode = new Node(key, value);
            addToHead(newNode);
            map.put(key, newNode);
        }
        
    }
}

/**
 * Your LRUCache object will be instantiated and called as such:
 * LRUCache obj = new LRUCache(capacity);
 * int param_1 = obj.get(key);
 * obj.put(key,value);
 */

 /*
 
 Least Recently Used, If we want to insert any new set inside a list, we need to remove the least recently used.

For this we can use Queue [FCFS] ot LinkedList. But by using these all the operations are O(n).

Then we can use HashMap, key and value as “just numbers not pointers” to optimize search to O(1). But all other operations except search will take O(n) [Searching, remove].

Next optimization is to put references in the hashmap, by this we can make searching and finding makes O(1), [we need to iterate to put “curr”, “prev” pointers ]but all other operations To remove Least Recent and to update most recent its O(n).

Removal of nodes in Singly Linked List id O(n) but  in the Doubly Linked List is O(1).

 
 
 
 
  */