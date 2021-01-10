//LC: 146
//Space Complexity: O(n)
//Time: O(1)
//Approach: Doubly LL 


class LRUCache {
    
    //Doubly LinkedList Node Data Structure
    class Node{
        int key, val;
        //Prev and Next Pointers
        Node prev, next;
        Node(int key, int val){
            this.key = key;
            this.val = val;
        }
    }
    //Head and Tail Nodes
    Node head, tail;
    
    //Add node to the beginning of the Linkedlist
    public void addHead(Node node){
        node.next = head.next;
        head.next = node;
        node.prev = head;
        node.next.prev = node;
    }
    
    //Remove Node from the LinkedList
    public void removeNode(Node node){
        node.prev.next = node.next;
        node.next.prev = node.prev;
    }    
    
    //HashMap to save the key and the Node reference
    Map<Integer, Node> map = new HashMap<>();
    //Global Cache Capacity
    int max;
    public LRUCache(int capacity) {
        max = capacity;
        
        //Initialization of Head and tail Nodes
        head = new Node(-1,-1);
        tail = new Node(-1,-1);
        head.next = tail;
        tail.prev = head;    
    }
    
    //Get the value for a given key
    public int get(int key) {
        
        //Given Key not present in the cache
        if(!map.containsKey(key)){
            return -1;
        }
        //If present, add it to the beginning, return value
        else{
            Node curr = map.get(key);
            int val = curr.val;
            removeNode(curr);
            addHead(curr);
            return val;
        }
    }
    
    
    //Put value in the Cache
    public void put(int key, int value) {
        
        //If the key already exists, add it to the beginning and update the value
        if(map.containsKey(key)){
            Node curr = map.get(key);
            curr.val = value;
            removeNode(curr);
            addHead(curr);
            map.put(key,curr);
        }
        else{
            //If cache full, add it to the beginning and update the value
            if(map.size() == max){
                Node tailPrev = tail.prev;
                removeNode(tailPrev);
                map.remove(tailPrev.key);             
            }
            
            //Always need to add it to the beginning
            Node newNode = new Node(key, value); 
            addHead(newNode);
            map.put(key, newNode);
        }
    }
}