// Time Complexity :
// Space Complexity :
// Did this code successfully run on Leetcode :yes
// Any problem you faced while coding this :


// Your code here along with comments explaining your approach
class LRUCache {
    
    class Node{
        int key;
        int value;
        Node next;
        Node prev;
        Node(int k,int v){
            this.key=k;
            this.value=v;
            this.prev=null;
            this.next=null;
        }
    }
    
    public void addFirst(Node node){
        head.next.prev=node;
        node.next=head.next;
        node.prev=head;
        head.next=node;
    }
    
    public void delete(Node node){
        //System.out.println(node.value);
        node.prev.next=node.next;
        node.next.prev=node.prev;
        node.next=null;
        node.prev=null;
    }

    
    Node head;
    Node tail;
    int cap;
    HashMap<Integer,Node> hash;
    public LRUCache(int capacity) {
        hash=new HashMap<Integer,Node>();
        cap=capacity;
        head=new Node(-1,-1);
        tail=new Node(-1,-1);
        head.next=tail;
        head.prev=null;
        tail.next=null;
        tail.prev=head;
    }
    
    public int get(int key) {
        if(hash.containsKey(key)){
            // System.out.println(hash);
            // System.out.println(key);
            Node temp=hash.get(key);
            delete(temp);
            addFirst(temp);
            return temp.value;
        }
        return -1;
    }
    
    public void put(int key, int value) {
        if(hash.containsKey(key)){
            Node node=hash.get(key);
            node.value=value;
            delete(node);
            addFirst(node);
            hash.put(key,node);
        }else{
            Node newNode=new Node(key,value);
            if(hash.size()==cap){
                Node prevTail=tail.prev;
                delete(prevTail);
                hash.remove(prevTail.key);
            }
            hash.put(key,newNode);
            addFirst(newNode);
        }
    }
}

/**
 * Your LRUCache object will be instantiated and called as such:
 * LRUCache obj = new LRUCache(capacity);
 * int param_1 = obj.get(key);
 * obj.put(key,value);
 */