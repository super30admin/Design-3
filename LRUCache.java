// Time Complexity :O(1)
// Space Complexity : O(n)
// Did this code successfully run on Leetcode :Yes
// Any problem you faced while coding this : 


// Your code here along with comments explaining your approach

// Double Linked list and Hashmap are good DS to maintain LRU
//1. using double link list we will maintain latest on head side and Least recent on tail side. so whenever a new node will come we will add it just after the head( in front)
// if the node is not in hashmap and hashmap is full then we will remove lru node from the List and add the new node in front along with adding it in map ( dont' forget to remove lru from map too
// if node is in hashmap,  get the node from map update the node with new value, delete it from the list and add it in front . also update it in hashmap 

class LRUCache {
   
    class DNode {
        
        int key; int value;
        DNode prev; DNode next;
       public DNode(int key, int val)
        {
            this.key = key;
            this.value = val;
        }
   }
    
    private void addNode(DNode curr)
    {
       curr.next = head.next;
       curr.next.prev = curr;
       head.next = curr;
       curr.prev = head;
    }
    
     
    private void removeNode(DNode node)
    {
        node.prev.next = node.next;
        node.next.prev = node.prev;
    }
    
    private void moveToHead(DNode node)
    {
        // delete
        removeNode(node);
        //add
        addNode(node);
    }
   
    
    int capacity;
     DNode head ;
     DNode tail ;
    HashMap<Integer,DNode> map ;
    
    public LRUCache(int capacity) {
        this.capacity = capacity;
         map = new HashMap<>();
        head = new DNode(-1,-1);
         tail = new DNode(-1,-1);
        head.prev = null;
        head.next = tail;
        tail.prev = head;
        tail.next = null;
    }
    
    public int get(int key) {
        
         if(map.containsKey(key))
         { 
             DNode  res = map.get(key);
             // delete
            removeNode(res);
            //add
            addNode(res);
             //moveToHead(res);
           return res.value;
         }
   
       
        return -1;
    }
    
    public void put(int key, int value) {
     
           if(map.containsKey(key))
            {
              // get the node from map based on key 
              DNode Node = map.get(key);
              // update it with new value
                Node.value = value;
                
               // remove it from the list
               removeNode(Node);
                //add it in front 
                 addNode(Node);
               // update the map too
                map.put(key,Node);
            }
            else
            {
                 DNode node = new DNode(key,value);
                // check if hashmap is full
                if(map.size() == capacity)
                {
                     // delete least recent node item
                   DNode lruNode = tail.prev;
                    removeNode(lruNode);
                    //remove it from Hashmap too
                   map.remove(lruNode.key);
                    
                    // add new node to list n map
                    addNode(node);
                    map.put(key,node);
                   
                   
                }
                else
                {
                    // add to the front 
                    addNode(node);
                    map.put(key,node);
                    
                    
                }
            }
            
          
        
    }
}

/**
 * Your LRUCache object will be instantiated and called as such:
 * LRUCache obj = new LRUCache(capacity);
 * int param_1 = obj.get(key);
 * obj.put(key,value);
 */