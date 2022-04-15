//TC:O(1)  for all operation
//SC:O(n) for map space and node space  

class LRUCache {
    class Node{
        int key;
        int value;
        Node prev;
        Node next;
        public Node(int key,int value){
            this.key = key;
            this.value=value;
        }
    }
    HashMap<Integer,Node> map;   //integer to node map so that the reference of node is stored in map and getting location of node in doubly linked list will become O(1)
    Node head;
    Node tail;
    int capacity;
    
    private void addToHead(Node node){
        node.next=head.next;
        node.prev=head;
        head.next=node;
        node.next.prev=node;
    }
    
    private void removeNode(Node node){
        node.next.prev=node.prev;
        node.prev.next=node.next;
    }

    public LRUCache(int capacity) {
        this.map = new HashMap<>();
        this.head = new Node(-1, -1);   //taking dummy head
        this.tail = new Node(-1, -1);   //taking dummy tail
        this.head.next = tail;        //intialising a doubly linked list
        this.tail.prev = head;
        this.capacity = capacity;
    }
    
    public int get(int key) {
        if(!map.containsKey(key)) return -1;     //if node does not exists return -1
        
        Node node = map.get(key);     //if it exists then get the node and remove it and move it to MRU i.e to head
        removeNode(node);
        addToHead(node);
        return node.value;
    }
    
    public void put(int key, int value) {
        //if node exists
        if(map.containsKey(key)){
            Node node = map.get(key);
            node.value = value;
            removeNode(node);  //after udpadating the value of the node , make it the most recent used by moving it to the head
            addToHead(node);
        }else{
            //if node does not exists i.e. it is fresh node
            //then check capacity
            if(capacity == map.size()){
                //if capacity is full then remove from tail i.e. remove LRU node from doubly linkedlist as well as from the map
                Node tailPrev = tail.prev;
                removeNode(tailPrev);
                map.remove(tailPrev.key);
            }
            //Now we have capacity , so add node to the head and add it to the map
            Node newNode = new Node(key,value);
            addToHead(newNode);
            map.put(key,newNode);
            
        }
    }
}

/**
 * Your LRUCache object will be instantiated and called as such:
 * LRUCache obj = new LRUCache(capacity);
 * int param_1 = obj.get(key);
 * obj.put(key,value);
 */