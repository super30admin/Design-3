//Time Complexity; O(1)
//Space Complexity; O(capacity or n)
class LRUCache {
    class Node{
        int key;
        int value;
        Node prev;
        Node next;
        public Node(int key, int value){
            this.key=key;
            this.value=value;
        }
    }
    private void addToHead(Node node){
        node.next=head.next;
        node.prev=head;
        head.next=node;
        node.next.prev=node;
    }
    private void remove(Node node){
        node.prev.next=node.next;
        node.next.prev=node.prev;
    }
    Node head;
    Node tail;
    int capacity;
    HashMap<Integer,Node> map;
    public LRUCache(int capacity) {
        this.capacity=capacity;
        head=new Node(-1,-1);
        tail=new Node(-1,-1);
        head.next=tail;
        tail.prev=head;
        map=new HashMap<>();
    }
    
    public int get(int key) {
        if(!map.containsKey(key))
            return -1;
        Node curr=map.get(key);
        remove(curr);
        addToHead(curr);
        return curr.value;
    }
    
    public void put(int key, int value) {
        if(map.containsKey(key)){
            Node curr=map.get(key);
            remove(curr);
            curr.value=value;
            addToHead(curr);
        }else{
            if(map.size()==capacity){
                Node tailPrev=tail.prev;
                remove(tail.prev);
                map.remove(tailPrev.key);
            }
            Node node=new Node(key,value);
            addToHead(node);
            map.put(key,node);
        }
    }
}

/**
 * Your LRUCache object will be instantiated and called as such:
 * LRUCache obj = new LRUCache(capacity);
 * int param_1 = obj.get(key);
 * obj.put(key,value);
 */
