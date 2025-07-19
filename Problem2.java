// Problem 2: LRU Cache(https://leetcode.com/problems/lru-cache/)

// Time Complexity : O(1)
// Space Complexity : o(2n) capacity
// Did this code successfully run on Leetcode : yes
// Any problem you faced while coding this : no


// Your code here along with comments explaining your approach
//we are using hashmap to get search o(1) where we are storing the node value of the linked list
//when we add, update, or give values, we remove the node from current position and add to the start
// we use head and tail, two dummy nodes to help manipulate the doubly linked list
// while adding new key, value, if the capacity is full, we remove last node from ll as its the lru node.

class LRUCache {
    class Node{
        Node previous;
        Node next;
        int key;
        int value;
        public Node(int key, int value){
            this.key=key;
            this.value=value;
        }
    }
    private int capacity;
    Node head;
    Node tail;
    HashMap<Integer,Node> map;
    public LRUCache(int capacity) {
        this.capacity=capacity;
        this.head=new Node(-1,-1);
        this.tail = new Node(-1,-1);
        this.map= new HashMap<>();
        head.next=tail;
        tail.previous = head;
    }
    private void removeNode(Node node){
        node.previous.next= node.next;
        node.next.previous=node.previous;
    }
    private void addHead(Node node){
        node.next=head.next;
        node.previous=head;
        head.next=node;
        node.next.previous=node;
    }
    public int get(int key) {
        if(!map.containsKey(key)) return -1;
        Node node = map.get(key);
        removeNode(node);
        addHead(node);
        return node.value;
    }
    
    public void put(int key, int value) {
        if(map.containsKey(key)){
            Node node = map.get(key);
            node.value=value;
            removeNode(node);
            addHead(node);
        }
        else{
            if(map.size()==capacity){
                //delete lru
                Node tailPrev= tail.previous;
                removeNode(tailPrev);
                map.remove(tailPrev.key);
            }
            Node newNode= new Node(key, value);
            addHead(newNode);
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