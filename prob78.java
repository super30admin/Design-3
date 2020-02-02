// S30 Big N Problem #78 {Medium }
// 146. LRU Cache
// Time Complexity :O(1)
// Space Complexity :O(n) where n is the number of nodes(capacity)
// Did this code successfully run on Leetcode :Yes
// Any problem you faced while coding this :None


// Your code here along with comments explaining your approach
class LRUCache {
    class Node{
        int key; int value;
        Node prev; Node next;
        public Node(int key,int value){
            this.key=key;
            this.value=value;
        }
    }
    
    private void removeNode(Node node){
        node.next.prev=node.prev;
        node.prev.next=node.next;
    }
    
    private void addToHead(Node node){
        node.prev=head;
        node.next=head.next;
        node.next.prev=node;
        head.next=node;
    }
    
    HashMap<Integer,Node> map;
    Node head; Node tail;
    int capacity;
    public LRUCache(int capacity) {
        this.map=new HashMap<>();
        this.head=new Node(-1,-1);
        this.tail=new Node(-1,-1);
        this.head.next=this.tail;
        this.tail.prev=this.head;
        this.capacity=capacity;
    }
    
    
    
    public int get(int key) {
        if(!map.containsKey(key)) return -1;
        Node node=map.get(key); 
        removeNode(node);
        addToHead(node);
        return node.value;
    }
    
    public void put(int key, int value) {
        //key is already in the cache
        if(map.containsKey(key)){
            Node node=map.get(key);
            node.value=value;
            removeNode(node);
            addToHead(node);
        }else{
            Node newnode=new Node(key,value);
            //if cache is full
            if(map.size()>=capacity){
                Node leastUsed=tail.prev;
                map.remove(leastUsed.key);
                removeNode(leastUsed);
            }
            addToHead(newnode);
            map.put(key,newnode);
        }
    }
}

/**
 * Your LRUCache object will be instantiated and called as such:
 * LRUCache obj = new LRUCache(capacity);
 * int param_1 = obj.get(key);
 * obj.put(key,value);
 */