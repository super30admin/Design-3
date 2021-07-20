//Time complexity-put-O(1) and get-O(1)
//Space complexity-O(capacity)
//Ran on leetcode-yes
//Solution with code- 
class LRUCache {
    class Node{//douubly linked list
        Node prev;
        Node next;
        int key;
        int val;
        public Node(int key, int val){
            this.key=key;
            this.val=val;
            prev=null;
            next=null;
        }
    }

HashMap<Integer,Node> map;//map will point to the node
Node head;
Node tail;
int capacity;

public LRUCache(int capacity) {
    head = new Node(0,0);
    tail= new Node(0,0);
    head.next=tail;
    tail.prev=head;
    this.capacity=capacity;
    map= new HashMap<>();
}

public void add(Node curr){    //add to doubly linked list
    Node temp=tail.prev;
    temp.next=curr;
    curr.next=tail;
    tail.prev=curr;
    curr.prev=temp;
   
}

public void remove(Node curr){//remove from doubly linked list
    Node before=curr.prev;
    Node after=curr.next;
    before.next=after;
    after.prev=before;
}

public void update(Node curr){
    remove(curr);
    add(curr);
}

public int get(int key) {
    Node curr=map.get(key);
    if(curr==null)
        return -1;
    
    update(curr);
    return curr.val;
}

public void put(int key, int value) {
    Node curr= map.get(key);
    
    if(curr==null){
        curr=new Node(key,value);

        map.put(key,curr);
        add(curr);
    }
    
    else{
        update(curr);
        curr.val=value;
    }
   
    
    if(map.size()>capacity){
        map.remove(head.next.key);
        remove(head.next);
     }

    
}
}

/**
* Your LRUCache object will be instantiated and called as such:
* LRUCache obj = new LRUCache(capacity);
* int param_1 = obj.get(key);
* obj.put(key,value);
*/