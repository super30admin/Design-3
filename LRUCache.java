//Time Complexity of Get and put O(1)
class LRUCache {
    class Node {
        Node prev;
        Node next;
        int val;
        int key;
       public Node(int val, int key)
       {this.val =val;
        this.key= key;
       }
    }
     
     int capacity;
     Node head, tail;
     HashMap<Integer, Node> h;
     public LRUCache(int capacity) {
         this.capacity = capacity;
         head= new Node(-1,-1);
         tail = new Node(-1,-1);
         head.next=tail; tail.prev=head;
         h = new HashMap<>();
     }
     private void delete()
     {
         Node del = head.next;
         Node next= del.next;
         head.next= next;
         next.prev= head;
         h.remove(del.key);
         
     }
     
     private void replace(Node curr)
     {
         Node prev= curr.prev;
         Node next= curr.next;
         
         prev.next= next;
         next.prev= prev;
         
         Node last = tail.prev;
         last.next= curr;
         curr.prev= last;
         curr.next= tail;
         tail.prev= curr;
     }
     
     
     public int get(int key) {
         
         if(h.containsKey(key))
         {
         replace(h.get(key));
         return h.get(key).val;
         }
         else return -1;
     }
     
     public void put(int key, int value) {
         if(h.containsKey(key))
         {Node curr = h.get(key);
          curr.val = value;
          replace(curr);
          h.put(key,curr);
         
         }
         
         else
         {
             if(h.size()==capacity)
                 delete();
             
         Node newEl = new Node(value, key);
         Node last = tail.prev;
         last.next= newEl;
         newEl.prev= last;
         newEl.next= tail;
         tail.prev= newEl;
         h.put(key, newEl);
             
         }
     }
 }
 
 /**
  * Your LRUCache object will be instantiated and called as such:
  * LRUCache obj = new LRUCache(capacity);
  * int param_1 = obj.get(key);
  * obj.put(key,value);
  */