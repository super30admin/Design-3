// Space Complexity : O(n)
// Time Complexity: O(n)
// Did it Execute: Yes, 13ms

/* Basic idea is to create a Doubly linked List, and in case of adding, removal, s
earch put the element on the top and once it reaches the capacity, get the last element and remove it
*/

class LRUCache {
    
    class Node{
         int key;
         int val;
         Node prev;
         Node next;  
    }
    
    Map<Integer, Node> map;
    
    final Node head = new Node();
    
    final Node tail = new Node();
    
    int cache_capacity;

    public LRUCache(int capacity) {
        
        map = new HashMap<>(capacity);
        
        cache_capacity = capacity;
         
        head.next = tail;   // Initialize the DLL.
        
        tail.prev = head;
        
    }
    
    public int get(int key) {
        
        int result = -1;
        
        if(map.containsKey(key) && map.get(key) != null) {
            
            Node temp = map.get(key);
            result =  map.get(key).val;
            
            remove(temp);
            add(temp);
        }  
        
        return result;
         
    }
    
    public void put(int key, int value) {
        
        Node node =  map.get(key);
        
        if(node != null){
            
            remove(node);
            
            node.val = value;
            
            add(node);
            
        }
        else{
            
            if(map.size() == cache_capacity){
                map.remove(tail.prev.key);
                remove(tail.prev);
            }
            
            Node new_node = new Node();
            
            new_node.key = key;
            
            new_node.val = value;
        
            map.put(key, new_node);
            
            add(new_node);
            
        }
        
    }
    
    public void add(Node node){
        
        Node next_node = head.next;
        
        head.next = node;
        
        node.prev = head;
        
        node.next = next_node;
        
        next_node.prev = node;
        
    }
    
    public void remove(Node node){
        
        Node next_node = node.next;
        
        Node prev_node = node.prev;
        
        
        prev_node.next = next_node;
        
        next_node.prev = prev_node;
        
    }
}




