import java.util.HashMap;


// Did this code successfully run on Leetcode : Yes
// Any problem you faced while coding this :No 


// Your code here along with comments explaining your approach
class LRUCache {
    
    class Node{
        int key,value;        
        Node prev,next;
        
        public Node(int k, int v){
            this.key=k;
            this.value=v;
        }        
    }
    
    HashMap<Integer,Node> map;
    int capacity;
    Node head,tail;

// Time Complexity : O(1) removing node takes just changing pointers
// Space Complexity :O(1) no extra space
     private void removeNode(Node node){
         Node nextNode=node.next;
         Node prevNode=node.prev;
        prevNode.next=nextNode;
        nextNode.prev=prevNode;
    }
    
// Time Complexity : O(1) adding node takes just changing pointers
// Space Complexity :O(1)  no extra space required
    private void addToHead(Node node){
        node.next=head.next;
        node.prev=head;
        head.next.prev=node; 
        head.next=node;
    }
    
// Time Complexity : O(1) for get and O(1) for put
// Space Complexity :O(n) for the HashMap and O(n) for Doubly linked list
    public LRUCache(int capacity) {
        this.capacity=capacity;
        map=new HashMap<>();
        head= new Node(-1,-1);
        tail=new Node(-1,-1);
        head.next=tail;
        tail.prev=head;       
    }
       
    public int get(int key) {
        
        if(map.containsKey(key)){
            
            Node node=map.get(key);
            removeNode(node);
            addToHead(node);
            return node.value;
        }
        return -1;
    }
    
    public void put(int key, int value) {

        if(!map.containsKey(key)){
             if(map.size() == capacity){
                Node removedNode=tail.prev;
                removeNode(removedNode);
                map.remove(removedNode.key);
            }
                Node newnode= new Node(key,value);
                map.put(key,newnode);
                addToHead(newnode);
        }
        else{
            Node newnode=map.get(key);
            newnode.value=value;
            removeNode(newnode);
            addToHead(newnode);
        }        
    }
}

/**
 * Your LRUCache object will be instantiated and called as such:
 * LRUCache obj = new LRUCache(capacity);
 * int param_1 = obj.get(key);
 * obj.put(key,value);
 */