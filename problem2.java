//time o(1)
//space o(capacity)

class LRUCache {
    
    class Node {
        int key;
        int value;
        Node next;
        Node prev;
    }
    
    HashMap<Integer, Node> map;
    Node head;
    Node tail;
    int capacity;
    int size;

    public LRUCache(int capacity) {
        this.map = new HashMap<>(); 
        
        this.head = new Node();
        this.tail= new Node();
        head.next = tail;
        tail.prev = head;
        
        this.capacity = capacity;
        this.size = 0;
    }
    
    public int get(int key) {
        Node node = map.get(key);
        if(node == null) {
            return -1;
        }
        
        moveToHead(node);
        return node.value;
    }
    
    public void put(int key, int value) {
        //check size. if size < capacity, add to head
        //remove tail and add to head
        
        
        Node node = map.get(key);
        
        if(node == null) {
            Node newNode = new Node();
            newNode.key = key;
            newNode.value = value;
            
            addToHead(newNode);
            size+=1;
            map.put(key, newNode);
            if(size > capacity) {
                Node temp = tail.prev;
                removeNode(temp);
                map.remove(temp.key);
                size -=1;
            }
        } else {
            node.value = value;
            moveToHead(node);
        }
        
    }
    
    void addToHead(Node node) {
        node.prev = head;
        node.next = head.next;
        
        head.next.prev = node;
        head.next = node;
    }
    
    void removeNode(Node node) {
        node.prev.next = node.next;
        node.next.prev = node.prev;
        
    }
    
    void moveToHead(Node node) {
        removeNode(node);
        addToHead(node);
    }
}

/**
 * Your LRUCache object will be instantiated and called as such:
 * LRUCache obj = new LRUCache(capacity);
 * int param_1 = obj.get(key);
 * obj.put(key,value);
 */
