// https://leetcode.com/submissions/detail/634530606/

public class LRUCache {
    
    public class Node {
        public int key;
        public int val;
        Node _prev;
        Node _next;
        public Node(int key, int val) {
            this.key = key;
            this.val = val;
        }
        public Node prev
        {
            get { return _prev; } 
            set { _prev = value; } 
        }
        
        public Node next
        {
            get { return _next; } 
            set { _next = value; } 
        }
    }
    
    private void AddToHead(Node node) {
        node.prev = head;
        node.next = head.next;
        head.next = node;
        node.next.prev = node;
    }
    
    public void RemoveNode(Node node) {
        node.prev.next = node.next;
        node.next.prev = node.prev;
    }
    
    Dictionary<int, Node> dict;
    int capacity;
    Node head;
    Node tail;
    
    public LRUCache(int capacity) {
        this.capacity = capacity;
        head = new Node(-1, -1);
        tail = new Node(-1, -1);
        head.prev = null;
        head.next = tail;
        tail.next = null;
        tail.prev = head;
        dict = new Dictionary<int, Node>();
    }
    
    public int Get(int key) {
        
        if(!dict.ContainsKey(key)) {
            return -1;
        }
        Node node = dict[key];
        RemoveNode(node);
        AddToHead(node);
        return node.val;
    }
    
    public void Put(int key, int val) {
        if(dict.ContainsKey(key)) {
            Node node = dict[key];
            node.val = val;
            RemoveNode(node);
            AddToHead(node);
        } else {
            if(capacity == dict.Count) {
                Node toRemove = tail.prev;
                RemoveNode(toRemove);
                dict.Remove(toRemove.key);
            }
            Node newNode = new Node(key, val);
            AddToHead(newNode);
            dict.Add(key, newNode);
        }
    }
}

/**
 * Your LRUCache object will be instantiated and called as such:
 * LRUCache obj = new LRUCache(capacity);
 * int param_1 = obj.Get(key);
 * obj.Put(key,value);
 */