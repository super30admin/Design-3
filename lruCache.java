
class ddList {

    int key;
    int val;
    ddList next;
    ddList prev;

    public ddList(int key, int val) {
        this.key = key;
        this.val = val;
        this.next = null;
        this.prev = null;
    }

    public void setVal(int val) {
        this.val = val;
    }

    public int getKey() {
        return this.key;
    }

    public int getVal() {
        return this.val;
    }
}

class LRUCache {

    int idx;
    HashMap<Integer, ddList> mp;
    ddList head;
    ddList tail;
    int cap;

    public LRUCache(int capacity) {
        mp = new HashMap<>();
        idx = 0;
        head = new ddList(-1, -1);
        tail = new ddList(-1, -1);
        head.next = tail;
        tail.prev = head;
        this.cap = capacity;

    }

    public int get(int key) {
        if (!mp.containsKey(key)) {
            return -1;
        }
        ddList node = mp.get(key);
        node.prev.next = node.next;
        node.next.prev = node.prev;
        pushFront(mp.get(key));
        return mp.get(key).getVal();

    }

    public void put(int key, int value) {
        //System.out.println("FIRST "+head.next.key);
        if (!mp.containsKey(key)) {
            ddList node = new ddList(key, value);
            mp.put(key, node);
            if (idx < cap) {
                idx++;

            } else {
                //System.out.println("REMOVE FROM CACHE "+tail.prev.key);
                mp.remove(tail.prev.getKey());
                delLast();
            }

        } else {
            ddList node = mp.get(key);
            node.prev.next = node.next;
            node.next.prev = node.prev;
            node.setVal(value);
        }
        // ddList node = mp.get(key);
        // node.prev.next = node.next;
        // node.next.prev = node.prev;
        pushFront(mp.get(key));

    }

    public void pushFront(ddList node) {
        //System.out.println("PUSH FRONT "+node.key);
        node.prev = head;
        node.next = head.next;
        head.next.prev = node;
        head.next = node;

    }

    public void delLast() {
        //System.out.println("LRU :"+tail.prev.key);
        tail.prev.prev.next = tail;
        tail.prev = tail.prev.prev;

    }
}

/**
 * Your LRUCache object will be instantiated and called as such:
 * LRUCache obj = new LRUCache(capacity);
 * int param_1 = obj.get(key);
 * obj.put(key,value);
 */
