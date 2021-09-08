
// Time Complexity : O(N)
// Space Complexity : O(N)
// Did this code successfully run on Leetcode : yes
// Any problem you faced while coding this : No


// Your code here along with comments explaining your approach
/*Approach
1) LRU Cache, we need to have the put, remove and detele method (if exceeds capacity)
2) Thus we use a Hashmap which will have the node value as key and node as the value
3) we will have a doubly linked list such that we just delete only the required node and link its next and prev node together
4) the one which we use again and again should be present in front of the linked list and the one we dont use should be pushed back
5) if we exceed capacity, we can take away the least recent used node. 
*/

import java.util.*;
class LRUCache {

    class Node{
        
        int key;
        int value;
        Node next;
        Node prev;
        
        
        public Node(int key,int value)
        {
            this.key=key;
            this.value=value;
        }
        
    }
    
    
    HashMap<Integer,Node> map;
        int capacity,count;
        Node head, tail;

    public LRUCache(int capacity) {
        
        this.capacity=capacity;
        map = new HashMap<>();
        head= new Node(0,0);
        tail= new Node(0,0);
        
        head.next =tail;
        head.prev=null;
        tail.next = null;
        tail.prev=head;
        count=0;
        
    }
    
    public int get(int key) {
        Node node = map.get(key);
        if(node==null)
            return -1;
        else if(node!=null)
        {
            remove(node);
            insert(node);
            return node.value;
            
        }
        return -1;
    }
    
    public void remove(Node node)
    {
        map.remove(node.key);
        node.prev.next=node.next;
        node.next.prev= node.prev;
        
    }
    
    public void insert(Node node)
    {
        map.put(node.key,node);
       // head=node;
        Node headNext= head.next;
        head.next=node;
        node.prev=head;
        headNext.prev=node;
        node.next=headNext;
        
    }
    
    
    
    
    public void put(int key, int value) {
        
        if(map.containsKey(key))
            remove(map.get(key));
        
        if(map.size()==capacity)
            remove(tail.prev);
        insert(new Node(key,value));
        
    }
}

/**
 * Your LRUCache object will be instantiated and called as such:
 * LRUCache obj = new LRUCache(capacity);
 * int param_1 = obj.get(key);
 * obj.put(key,value);
 */