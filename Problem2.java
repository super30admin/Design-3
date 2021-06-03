//Time Complexity: O(1)
//Space Complexity:O(n) n is the capacity
class LRUCache {
    class Node{
        int key;
        int val;
        Node prev;
        Node next;
        public Node(int key, int value){
            this.key=key;
            this.val=value;
        } 
    }
    Node head, tail;
    public void remove(Node node){
        node.next.prev=node.prev;
        node.prev.next=node.next;
    }
    public void addHead(Node node){
        node.next=head.next;
        node.prev=head;
        head.next.prev=node;
        head.next=node;
    }
    HashMap<Integer, Node> map;
    int cap;
    public LRUCache(int capacity) {
        head=new Node(-1,-1);
        tail=new Node(-1,-1);
        head.next=tail;
        tail.prev=head;
        cap=capacity;
        map=new HashMap<>(cap);
    }

    public int get(int key) {
        if(map.containsKey(key)){
            Node node =map.get(key);
            remove(node);
            addHead(node);
            return node.val;
        }
        return -1;
    }

    public void put(int key, int value) {
        if(map.containsKey(key)){
            Node node= map.get(key);
            remove(node);
            addHead(node);
            node.val=value;
            map.put(key, node);
        }else{
            if(map.size()==cap){
                Node tailprev = tail.prev;
                remove(tailprev);
                map.remove(tailprev.key);
            }
            Node node = new Node(key, value);
            map.put(key, node);
            addHead(node);
        }
    }
}

/**
 * Your LRUCache object will be instantiated and called as such:
 * LRUCache obj = new LRUCache(capacity);
 * int param_1 = obj.get(key);
 * obj.put(key,value);
 */ 
