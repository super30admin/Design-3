
class LRUCache {
    
class Node{
 int key;
 int val;
 Node prev;
 Node next;
 

}

 Node head= new Node();
 Node tail= new Node();
 Map<Integer, Node> mp= new HashMap<>();
 int capacity;

 public LRUCache(int capacity) {
     this.capacity=capacity;
      head.next=tail;
     tail.prev=head;
 }
 
 public int get(int key) {
     Node n= mp.get(key);
     int res=-1;
     if(n!=null){
         
         remove(n);
         add(n);
         res= n.val;
     }
     
     return res;
     
 }
 
 public void put(int key, int value) {
     
     Node n= mp.get(key);
     if(n!=null){
         
         n.val=value;
         remove(n);
         add(n);
     }
     
     else{
       Node new_node= new Node();
        // mp.put(key,new_node);
         if(mp.size()==capacity){
              mp.remove(tail.prev.key);
               remove(tail.prev);
            
           
         }
           
         new_node.val=value;
         new_node.key=key;
         mp.put(key,new_node);
         add(new_node);
     }
     
     
 }
 
 public void add(Node node){
     Node n= head.next;
     head.next=node;
     node.next=n;
     n.prev=node;
     node.prev=head;
     
 }
 public void remove(Node node){
    Node n_next= node.next;
    Node n_prev= node.prev;
     n_prev.next=n_next;
     n_next.prev=n_prev;
     
 }
}

/**
* Your LRUCache object will be instantiated and called as such:
* LRUCache obj = new LRUCache(capacity);
* int param_1 = obj.get(key);
* obj.put(key,value);
*/