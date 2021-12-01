// Time Complexity : O(1)
// Space Complexity : O(N)
// Did this code successfully run on Leetcode : Yes
// Any problem you faced while coding this : No

class LRUCache {
    
    class Node{
        int key;
        int val;
        Node prev;
        Node next;
        
        Node(int key,int val, Node prev, Node next){
            this.key=key;
            this.val=val;
            this.prev=prev;
            this.next=next;
        }
    }
    Node head;
    Node tail;
    HashMap<Integer,Node> map;
    int c;
    public LRUCache(int capacity) {
        head=new Node(-1,-1,null,null);
        tail=new Node(-1,-1,null,null);
        map=new HashMap<>();
        head.next=tail;
        c=capacity;
    }
    
    public int get(int key) {
        if(map.containsKey(key)){
            Node removedNode=removeNode(map.get(key));
            Node addedNode=addToHead(key,removedNode.val);
            return addedNode.val;
        }else{
            return -1;
        }     
    }
    
    public void put(int key, int value) {
        if(!map.containsKey(key)){
            if(map.size()==c){
                Node removedNode=removeFromTail();
                map.remove(removedNode.key);
            }  
        }else{
            Node removedNode=removeNode(map.get(key));
        }
        Node addedNode=addToHead(key,value);
        map.put(key,addedNode);
    }
    
    public Node addToHead(int key,int value){
         Node node=new Node(key,value,null,null);
            node.next=head.next;
            node.prev=head;
            node.next.prev=node;
            head.next=node;
            map.put(key,node);
        return node;
    }
    
    public Node removeFromTail(){
        Node node=tail.prev;
        tail.prev.prev.next=tail;
        tail.prev=tail.prev.prev;
        return node; 
    }
    
    public Node removeNode(Node node){
       node.next.prev=node.prev;
        node.prev.next=node.next;
        return node;  
    }
}

/**
 * Your LRUCache object will be instantiated and called as such:
 * LRUCache obj = new LRUCache(capacity);
 * int param_1 = obj.get(key);
 * obj.put(key,value);
 */