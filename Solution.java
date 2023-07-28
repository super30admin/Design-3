class ListNode {
    int key;
    int value;
    ListNode next;
    ListNode prev;

    public ListNode(int key, int value) {
        this.key = key;
        this.value = value;
    }
}


class LRUCache {
    int index;
    int capacity;
    private ListNode head;
    private ListNode tail;
    private HashMap<Integer, ListNode> map;

    private void addToHead(ListNode node) {
        ListNode temp = head.next;
        if( temp == null ) {
            head.next = node;
            node.prev = head;
            return;
        }
        head.next = node;
        node.next = temp;
        temp.prev = node;
        node.prev = head;
    }

    private ListNode deleteFromTail() {
        ListNode temp = tail.prev;
        ListNode newTail = tail.prev.prev;
        newTail.next = tail;
        tail.prev = newTail;
        temp.next = null;
        temp.prev = null;
        return temp;
    }

    private void update(ListNode node) {
        ListNode pr = node.prev;
        ListNode nxt = node.next;
        pr.next = nxt;
        nxt.prev = pr;
        node.next = null;
        node.prev = null;
        addToHead(node);
    }

    public LRUCache(int capacity) {
        this.map = new HashMap<>();
        this.index = 0;
        this.capacity = capacity;
        this.head = new ListNode(-1,-1);
        this.tail = new ListNode(-1,-1);
        head.next = tail;
        tail.prev = head;
    }

    public int get(int key) {
        if(map.containsKey(key)) {
            ListNode node = map.get(key);
            update(node);
            return node.value;
        } else {
            return -1;
        }
    }

    public void put(int key, int value) {
        if( map.containsKey(key) ) {
            ListNode node = map.get(key);
            node.value = value;
            update(node);
            // addToHead(node);
            return;
        } else {
            ListNode newNode = new ListNode(key, value);
            map.put(key, newNode);
            addToHead(newNode);
            if( index < capacity ) {
                index++;
            } else {
                ListNode del = deleteFromTail();
                map.remove(del.key);
            }
        }
    }
}

/**
 * Your LRUCache object will be instantiated and called as such:
 * LRUCache obj = new LRUCache(capacity);
 * int param_1 = obj.get(key);
 * obj.put(key,value);
 */