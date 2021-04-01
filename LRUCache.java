class LRUCache {
    
    class Node{
        int key;
        int val;
        Node prev;
        Node next;
    }
    
    final Node head = new Node();
    final Node tail = new Node();
    Map<Integer, Node> node_map;
    int cache_capacity;

    //Initializing Data Structure and all corressponding values
    public LRUCache(int capacity) {
        node_map = new HashMap<>(capacity);
        this.cache_capacity = capacity;
        head.next = tail;
        tail.prev = head;
    }
    
    public int get(int key) {
        int result = -1;
        //get node from hashmap for corressponding key
        Node node = node_map.get(key);
        //if node exists, save its value in result
        //remove node and add it to the head as it is most recently used
        if(node != null){
            result = node.val;
            remove(node);
            add(node);
        }
        return result;
    }
    
    public void put(int key, int value) {
        Node node = node_map.get(key);
        //if node is already present in map 
        //remove and add it to the head
        if(node != null){
            remove(node);
            node.val = value;
            add(node);
        }else{//node doesnot already exists
            //iff capacity is reached, remove node from tail 
            if(cache_capacity == node_map.size()){
                node_map.remove(tail.prev.key);
                remove(tail.prev);
            }
            
            //add new node at head
            Node newNode = new Node();
            newNode.key = key;
            newNode.val = value;
            node_map.put(key, newNode);
            add(newNode);
        }
        
    }
    
    //to add nodes at the head
    public void add(Node node){
        Node head_next = head.next;
        node.next = head.next;
        node.prev = head;
        head.next = node;
        head_next.prev = node;
    }
    
    //to remove nodes from tail
    public void remove(Node node){
        Node prev_node = node.prev;
        Node next_node = node.next;
        
        prev_node.next = next_node;
        next_node.prev = prev_node;
    }
}

//Time Complexity: O(1)
//Space Complexity: O(1)

/**
 * Your LRUCache object will be instantiated and called as such:
 * LRUCache obj = new LRUCache(capacity);
 * int param_1 = obj.get(key);
 * obj.put(key,value);
 */