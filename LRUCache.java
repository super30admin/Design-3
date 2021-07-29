class LRUCache {

    //In your overall LRU cache class you need a map of integers to keep track of items that are currently stored
    //for fast lookup

    Map<Integer, ListNode> map = new HashMap<Integer, ListNode>();

    //The cache also has a max capacity and a current variable to check if we are over the limit
    int maxCapacity;
    int currentSize;

    //The cache is also a DLL so it needs a head and tail that is not currently initalized
    ListNode head;
    ListNode tail;

    //This is the contstructor for the LRU cache
    public LRUCache(int capacity) {

        this.maxCapacity = capacity;
        currentSize = 0;

        //Since there is no constructor for ListNode it is all set to default objects
        head = new ListNode();
        tail = new ListNode();

        //The default DLL has the two pointers pointing to eachother
        head.next = tail;
        tail.prev = head;

    }

    public int get(int key) {

        ListNode node = map.get(key);

        if (node == null) {
            return -1;
        }

        moveToHead(node);

        return node.value;

    }

    public void put(int key, int value) {

        ListNode node = map.get(key);

        if (node == null) {

            ListNode newNode = new ListNode();
            newNode.key = key;
            newNode.value = value;

            map.put(key, newNode);
            addToFront(newNode);

            currentSize++;

            if (currentSize > maxCapacity) {
                removeLRUNode();
            }

        } else {
            node.value = value;
            moveToHead(node);
        }

    }

    public void addToFront(ListNode node) {

        node.prev = head;
        node.next = head.next;

        head.next.prev = node;
        head.next = node;

    }

    public void removeLRUNode() {

        ListNode tailItem = tail.prev;
        removeFromList(tailItem);

        map.remove(tailItem.key);
        currentSize--;
    }

    private void removeFromList(ListNode node) {

        ListNode savedPrev = node.prev;
        ListNode savedNext = node.next;

        savedPrev.next = savedNext;
        savedNext.prev = savedPrev;

    }

    public void moveToHead(ListNode node) {

        removeFromList(node);
        addToFront(node);

    }

    //This is a private. class of LRU Cache to make the nodes
    //We could add a construtor here but it is not neeeded, if we did we would need to add values in the
    //initaliziation of the head and tail in the LRU cache method
    private class ListNode {

        int key;
        int value;
        ListNode next;
        ListNode prev;

    }
}