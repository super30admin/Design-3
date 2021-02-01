//Time Complexity for operators : o(1)Extra Space Complexity for operators : o(1)
class LRUCache {

    class DoublyNode{
        int key;
        int val;
        DoublyNode next, prev;

        public DoublyNode(int key, int val){
            this.key = key;
            this.val = val;

            prev = null;
            next = null;
        }

    }

    private DoublyNode head, tail;
    private HashMap<Integer,DoublyNode> hm;
    private int capacity;
    private int size;

    private void add(DoublyNode node){
        DoublyNode temp = tail.prev;
        tail.prev = node;
        node.prev = temp;
        temp.next = node;
        node.next = tail;
    }

    private void remove(DoublyNode node){
        DoublyNode before = node.prev;
        DoublyNode after = node.next;

        before.next = after;
        after.prev = before;
    }

    public LRUCache(int capacity) {
        head = new DoublyNode(0,0);
        tail = new DoublyNode(0,0);
        head.next = tail;
        tail.prev = head;
        this.capacity = capacity;

        hm = new HashMap<>();
        size = 0;

    }

    private void update(DoublyNode node){
        remove(node);
        add(node);
    }

    public int get(int key) {
        DoublyNode node = hm.get(key);

        if(node == null)
            return -1;
        else{
            update(node);
        }

        return node.val;
    }

    public void put(int key, int value) {
        DoublyNode node = hm.get(key);

        if(node == null){

            node = new DoublyNode(key,value);

            hm.put(key,node);
            size +=1;

            add(node);

        }else{
            node.val = value;
            update(node);
        }

        if(size>capacity){
            DoublyNode temp = head.next;
            remove(temp);
            size -= 1;
            hm.remove(temp.key);
        }
    }
}
