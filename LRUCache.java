 //Time Complexity : O(1)
//Space Complexity :O(n)
//Did it run on leetcode  : yes


class LRUCache {
    
  
    class DLinkNode{
        
      int key;
      int value;
      DLinkNode next;
      DLinkNode prev;
        
      public DLinkNode(int key ,int value){
         this.key = key;
          this.value = value;
          
      }
        
    }
DLinkNode head;
  DLinkNode tail;
    
    public void addNode(DLinkNode newNode){
        newNode.next =  head.next;
        head.next.prev = newNode;
        newNode.prev = head;
        head.next = newNode;
        
    }
    
    
    public void removeNode(DLinkNode node){
         DLinkNode prev = node.prev;
         DLinkNode next = node.next;
         prev.next = next;
         next.prev= prev;
    }
    
   
        
        
        
    HashMap<Integer,DLinkNode> hashMap;
    int capacity;
   
    public LRUCache(int capacity){
        hashMap = new HashMap<>();
        this.capacity = capacity;
        head = new DLinkNode(-1,-1);
        tail = new DLinkNode(-1,-1);
        head.next = tail;
        tail.prev = head;
    }
    
    public int get(int key) {
        
        if(hashMap.containsKey(key)){
          DLinkNode node  = hashMap.get(key);
         removeNode(node);
         addNode(node);
         return node.value;
        }
        return -1;
    }
    
    public void put(int key, int value) {
        
    if(hashMap.containsKey(key)){  
      DLinkNode node =  hashMap.get(key);
      removeNode(node);
      addNode(node);
      node.value = value;
    }
    else{
        DLinkNode newNode = new DLinkNode(key,value);
         if(hashMap.size() == capacity){
            
             DLinkNode node = tail.prev;
             removeNode(node);
             hashMap.remove(node.key);
         }  
        addNode(newNode);
        hashMap.put(key,newNode);
        
    }
         
    }
}


/**
 * Your LRUCache object will be instantiated and called as such:
 * LRUCache obj = new LRUCache(capacity);
 * int param_1 = obj.get(key);
 * obj.put(key,value);
 */