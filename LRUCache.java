import java.util.HashMap;
import java.util.Map;

//Time Complexity : O(1)
//Space Complexity : O(n)
//Did this code successfully run on Leetcode : Yes
//Any problem you faced while coding this : No

class LRUCache {
    class Node{
        int key;
        int val;
        Node next;
        Node prev;

        Node(int key, int val){
            this.val=val;
            this.key=key;
        }
    }

    Map<Integer,Node> map=new HashMap<>();
    Node head;
    Node tail;
    int cap;
    public LRUCache(int capacity) {
        this.cap=capacity;
        head=new Node(-1,-1);
        tail=new Node(-1,-1);
        head.next=tail;
        tail.prev=head;
    }
    
    public int get(int key) {
        int ret=-1;
        if(map.containsKey(key)){
            Node torem=map.get(key);
            remove(torem);
            addtofront(torem);

            ret = torem.val;
        }
        return ret;
    }

    private void remove(Node n){
        n.prev.next=n.next;
        n.next.prev=n.prev;
    }

    private void addtofront(Node n){
        n.prev=head;
        n.next=head.next;
        head.next=n;
        n.next.prev=n;
    }
    
    public void put(int key, int value) {
        if(map.containsKey(key)){
            Node n=map.get(key);
            n.val=value;
            remove(n);
            addtofront(n);
        }else{
            if(map.size()==cap){
                Node torem=tail.prev;
                remove(torem);
                map.remove(torem.key);
            }
            Node toadd=new Node(key,value);
            addtofront(toadd);
            map.put(key,toadd);
        }
    }
}

/**
 * Your LRUCache object will be instantiated and called as such:
 * LRUCache obj = new LRUCache(capacity);
 * int param_1 = obj.get(key);
 * obj.put(key,value);
 */