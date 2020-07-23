// Time Complexity : O(1)- for both get and put operations.
// Space Complexity :O(n) - space complexity for the hashmap.
// Did this code successfully run on Leetcode : Yes
// Any problem you faced while coding this :No
// Your code here along with comments explaining your approach; The approach is to create our own node class with key and value.Start by adding with two dummy nodes head,tail to the list. This dummy node helps removing an element from in between/end and adding it to the head.- O(1) put operations.
//A hashmap helps O(1) for get operations.

class LRUCache {
    class Node{
        int key;int val;
        Node next;
        Node prev;
        public Node(int key,int val){
            this.key=key;
            this.val=val;
       }
    }
    public void removeNode(Node node){
        node.prev.next=node.next;
        node.next.prev=node.prev;
  }
     public void addTohead(Node node){
        node.next=head.next;
        node.prev=head;
        head.next=node;
        node.next.prev=node;
         
  }
    Map<Integer,Node> hmap;
    Node head; Node tail;
    int capacity;
    public LRUCache(int capacity) {
        hmap=new HashMap<>();
        head=new Node(-1,-1);
        tail=new Node(-1,-1);
        head.next=tail;
        tail.prev=head;
        this.capacity=capacity;
    }
    
    public int get(int key) {
        //get the node value from the hash map and push it to the fromt in the doubly linked list.
        if(hmap.containsKey(key)){
            Node n=hmap.get(key);
        removeNode(n);
        addTohead(n);
        return n.val;
}
        else return -1;
    }
    
    public void put(int key, int value) {
        //when the key already exsists
        if(hmap.containsKey(key)){
            Node c=hmap.get(key);
            c.val=value;
            removeNode(c);
            addTohead(c);
        }
        else{
        if(hmap.size()==capacity){
           Node c=tail.prev;
           hmap.remove(c.key);
           removeNode(c);
           
        }
        Node curr =new Node(key,value);
        addTohead(curr);
        hmap.put(key,curr);  
            
        }
       
    }
}