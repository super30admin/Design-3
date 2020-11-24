// Time Complexity :  O(1)
// Space Complexity : O(1)
// Did this code successfully run on Leetcode :Yes
// Any problem you faced while coding this : nopes
class LRUCache {

    class Node
    {
        int key;
        int value;
        Node prev;
        Node next;

        public Node(int key,int value)
        {
            this.key=key;
            this.value=value;
        }
    }

    private void addToHead(Node node)
    {
        node.next = head.next;
        node.prev = head;
        head.next = node;
        node.next.prev = node;
    }

    private void removeNode(Node node)
    {
        node.prev.next = node.next;
        node.next.prev = node.prev;
    }

    HashMap<Integer,Node> map;
    int capacity;
    Node head;
    Node tail;

    public LRUCache(int capacity) {

        this.capacity = capacity;

        map = new HashMap<>();

        head = new Node(-1,-1);

        tail = new Node(-1,-1);

        head.next = tail;

        tail.prev = head;

    }

    public int get(int key) {

        if(!map.containsKey(key))
            return -1;

        Node node=map.get(key);
        removeNode(node);
        addToHead(node);

        return node.value;
    }

    public void put(int key, int value) {
        //element already exists
        if(map.containsKey(key))
        {
            Node node = map.get(key);
            node.value=value;
            removeNode(node);
            addToHead(node);
        }
        else
        {//doesnt exist,create new element

            Node newNode = new Node(key,value);
            if(capacity==map.size())
            {
                //capacity or size of map is full so remove the tail element
                Node tailPrev = tail.prev;
                //remove from list
                removeNode(tailPrev);

                //remove from map
                map.remove(tailPrev.key);
            }

            //add new node to the list
            addToHead(newNode);

            //add to map
            map.put(key,newNode);
        }
    }
}

/**
 * Your LRUCache object will be instantiated and called as such:
 * LRUCache obj = new LRUCache(capacity);
 * int param_1 = obj.get(key);
 * obj.put(key,value);
 */