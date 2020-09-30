//Time Complexity: O(1) for both put and get
//Space Complexity: O(n)
//Did run on leetcode: Yes
/**Approach: Used a hashmap and doubly linkedlist
*  hashmap store node reference of each key
*  the updated and new nodes are added at the tail nodes
*  and remove is done at the head of the node **/



class LRUCache {
    class Node{
        int key, val;
        Node prev, next;
        Node(int key, int val){
            this.key = key;
            this.val = val;
        }
    }
    
    int capacity;
    int size;
    Node head = new Node(0,0);
    Node tail = new Node(0,0);
    HashMap<Integer, Node> map = new HashMap<>();
    
    private void add(Node node){
        
        Node temp = tail.prev;
        //System.out.println("1");
        tail.prev = node;
        //System.out.println("2");
        node.prev = temp;
        //System.out.println("3");
        temp.next = node;
        //System.out.println("4");
        node.next = tail;
        //System.out.println("5");
        
    }
    
    private void remove(Node node){
        Node before = node.prev;
        Node after = node.next;
        before.next = after;
        after.prev = before;
    }
    
    private void update(Node node){
        remove(node);
        add(node);
    }

    public LRUCache(int capacity) {
        this.capacity = capacity;
        size = 0;
        head.next = tail;
        tail.prev = head;
             
    }
    
    public int get(int key) {
        Node node = map.get(key);
        if(node==null)
            return -1;
        else
            update(node);
        
        return node.val;
    }
    
    public void put(int key, int value) {
        Node node = map.get(key);
        if(node==null){
            //System.out.println("hey");
            node  = new Node(key,value);
            map.put(key, node);
            add(node);
            size += 1;
        }else{
            node.val = value;
            update(node);
        }
        
        if(size > capacity){
            Node temp = head.next;
            remove(temp);
            size -= 1;
            map.remove(temp.key);
        }
    }
}
