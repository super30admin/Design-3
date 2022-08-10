
// 0(1) time
// 0(capacity) space
class LRUCache {
    class Node{
        int key,value;
        Node prev, next;
        public Node(int key,int value)
        {
            this.key=key;
            this.value=value;
        }
    }

    Node head,tail;
    HashMap<Integer,Node> mp;
    int capacity;

    private void removeNode(Node node)
    {
        node.next.prev=node.prev;
        node.prev.next=node.next;
    }
    private void insertNodeToHead(Node node)
    {
        node.next=head.next;
        node.prev=head;
        head.next=node;
        node.next.prev=node;
    }

    public LRUCache(int capacity) {
        this.capacity=capacity;
        mp=new HashMap<>();
        head=new Node(-1,-1);
        tail=new Node(-1,-1);
        head.next=tail;
        tail.prev=head;
    }

    public int get(int key) {
        if(!mp.containsKey(key))
        {
            return -1;
        }
        Node node=mp.get(key);
        removeNode(node);
        insertNodeToHead(node);
        return node.value;
    }

    public void put(int key, int value) {
        if(mp.containsKey(key))
        {
            Node node=mp.get(key);
            node.value=value;
            removeNode(node);
            insertNodeToHead(node);
            return;
        }
        if(mp.size()== capacity)
        {
            Node tailPrev =tail.prev;
            removeNode(tailPrev);
            mp.remove(tailPrev.key);
        }
        Node node = new Node(key, value);
        insertNodeToHead(node);
        mp.put(key,node);

    }

}


/**
 * Your LRUCache object will be instantiated and called as such:
 * LRUCache obj = new LRUCache(capacity);
 * int param_1 = obj.get(key);
 * obj.put(key,value);
 */