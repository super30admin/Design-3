//Time Complexity : O(1)
//Space Complexity :O(1) 
//Did this code successfully run on Leet code :Yes

public class LRUCache {
    class Node{
        public int key; public int val;
        public Node prev; public Node next;
        public Node(int key, int val)
        {
            this.key = key;
            this.val = val;            
        }
    }
    Node head;
    Node tail;
    int cap;
    Dictionary<int, Node> map;
    private void addToHead(Node node)
    {
        node.next = head.next;
        head.next = node;
        node.prev = head;
        node.next.prev = node;
    }
    private void remove(Node node){
        node.next.prev = node.prev;
        node.prev.next = node.next;
    }

    public LRUCache(int capacity) {
        this.map = new Dictionary<int, Node>();
        this.cap = capacity;
        this.head = new Node(-1,-1);
        this.tail = new Node(-1,-1);
        this.head.next = tail;
        this.tail.prev = head;

    }
    
    public int Get(int key) {
        if(!map.ContainsKey(key))
            return -1;
        
        Node n = map[key];
        remove(n);
        addToHead(n);
        return n.val;
    }
    
    public void Put(int key, int value) {
        if(map.ContainsKey(key)){
            Node n = map[key];
            n.val = value;
            remove(n);
            addToHead(n);
        }else
        {
            if(map.Count == cap){
                Node tailPrev = tail.prev;
                remove(tailPrev);
                map.Remove(tailPrev.key);
            }            
            Node n = new Node(key, value);
            addToHead(n);
            map.Add(key, n);            
        }        
    }
}

/**
 * Your LRUCache object will be instantiated and called as such:
 * LRUCache obj = new LRUCache(capacity);
 * int param_1 = obj.Get(key);
 * obj.Put(key,value);
 */
