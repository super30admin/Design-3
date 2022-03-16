class LRUCache {

    int capacity;
    Map<Integer, ListNode> map;
    ListNode head;
    ListNode tail;

    public LRUCache(int capacity) {
        this.capacity = capacity;
        map = new HashMap<>();
        head = new ListNode(-1,-1);
        tail = new ListNode(-1,-1);
        head.next = tail;
        tail.prev = head;
    }

    public int get(int key) {

        ListNode curr = map.get(key);

        if(curr == null) return -1;

        deleteNode(key);
        addNode(key, curr.val);

        return curr.val;

    }

    public void put(int key, int value) {

        ListNode curr = map.get(key);

        if(curr == null) {

            if(map.size() == capacity){
                deleteNode(tail.prev.key);
            }
            addNode(key, value);
        }
        else{
            deleteNode(key);
            addNode(key, value);
        }

    }

    public void deleteNode(int key){

        ListNode curr = map.get(key);

        if(curr == null) return;

        ListNode currPrev = curr.prev;
        ListNode currNext = curr.next;

        currPrev.next = currNext;
        currNext.prev = currPrev;

        map.remove(key);
    }

    public void addNode(int key, int val){

        ListNode curr = new ListNode(key, val);

        ListNode first = head.next;

        head.next = curr;
        curr.prev = head;

        curr.next = first;
        first.prev = curr;

        map.put(key, curr);
    }

}

class ListNode{

    public int key;
    public int val;
    public ListNode prev;
    public ListNode next;

    public ListNode(int key, int val){
        this.key = key;
        this.val = val;
        prev = null;
        next = null;
    }
}
