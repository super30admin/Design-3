//TC : O(1) for both put and get operation
//SC : O(capacity)
class LRUCache {

    class Node
    {
        int val;
        Node next;
        Node prev;
        int key;
        private Node(int val,int key)
        {
            this.val = val;
            this.key = key;
        }
    }
    Node start;
    Node end;
    HashMap<Integer,Node> hm;
    int cap;
    public LRUCache(int capacity) {
        hm = new HashMap<>();
        start = new Node(-1,-1);
        end = new Node(-1,-1);
        start.next = end;
        end.prev = start;
        cap = capacity;

    }
    private void displaceNode(Node n)
    {
        n.prev.next = n.next;
        n.next.prev = n.prev;
    }
    private void moveNodeToFront(Node n)
    {
        n.next = start.next;
        start.next.prev = n;
        n.prev = start;
        start.next = n;
    }
    public int get(int key) {
        //System.out.println("HM : "+hm.toString());
        if(hm.containsKey(key))
        {
            Node n = hm.get(key);
            displaceNode(n);
            moveNodeToFront(n);
            return n.val;
        }

        return -1;
    }

    public void put(int key, int value) {
        if(hm.containsKey(key))
        {
            Node n = hm.get(key);
            displaceNode(n);
            moveNodeToFront(n);
            n.val = value;
        }
        else
        {
            if(cap == hm.size())
            {
                Node n = end.prev;
                displaceNode(n);
                hm.remove(n.key);
            }
            Node n1 = new Node(value,key);
            moveNodeToFront(n1);
            hm.put(key,n1);
        }
    }
}

/**
 * Your LRUCache object will be instantiated and called as such:
 * LRUCache obj = new LRUCache(capacity);
 * int param_1 = obj.get(key);
 * obj.put(key,value);
 */