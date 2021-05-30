import java.util.HashMap;

// Time Complexity : O(1) 
// Space Complexity : O(size)
// Did this code successfully run on Leetcode : yes
// Any problem you faced while coding this : yes


// Your code here along with comments explaining your approach

public class LRUchache {

    class Node{
        int key;
        int val;
        Node prev;
        Node next;
        
        public Node(int key, int val){
            this.key=key;
            this.val=val;
        }
    }
    
    private void removeNode(Node node){
        node.next.prev=node.prev;
        node.prev.next=node.next;
    }
    
    private void addNode(Node node){
        node.next=head.next;
        node.prev=head;
        head.next=node;
        node.next.prev=node;
        
    }
    
    HashMap<Integer, Node> sMap;
    Node head;
    Node tail;
    int capacity;
    
    public void LRUCache(int capacity) {
        sMap=new HashMap<>();
        head=new Node(-1,-1);
        tail=new Node(-1,-1);
        head.next=tail;
        tail.prev=head;
        this.capacity=capacity;
        
    }
    
    public int get(int key) {
        if(!sMap.containsKey(key))
            return -1;
        Node node= sMap.get(key);
        removeNode(node);
        addNode(node);
        
        return node.val;
        
    }
    
    public void put(int key, int value) {
        
        if(sMap.containsKey(key)){
            
            Node node=sMap.get(key); 
            node.val=value;
            removeNode(node);
            addNode(node);
            
            
        }else{
             
            if(capacity==sMap.size()){
                Node tailprev=tail.prev;
                 removeNode(tailprev);
                sMap.remove(tailprev.key);
                
            }
                Node newNode=new Node(key,value);
                 addNode(newNode);
                sMap.put(key,newNode);  
        }
        
    }
}
