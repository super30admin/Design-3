/*
Time: Get and Put are O(1), Add and remove are also O(1)
Space: O(capacity) of the cache
*/
class LRUCache {

    class Node { // subclass
        int key;
        int value;
        Node prev;
        Node next;

    }

    final Node head = new Node();
    final Node tail = new Node();

    Map<Integer, Node> nodeMap;
    int capacity;

    public LRUCache(int capacity) {

        // initiliaze the LL
        this.capacity = capacity;
        head.next = tail;
        tail.prev = head;
        nodeMap = new HashMap(capacity);

    }

    public int get(int key) {

        Node node = nodeMap.get(key);

        int result = -1;

        if (node != null) {
            result = node.value;
            remove(node);
            addToHead(node);
        }

        return result;
    }

    public void put(int key, int value) {

        Node node = nodeMap.get(key);

        if (node != null) {
            remove(node);
            node.value = value;
            addToHead(node);
        }

        else {

            if (nodeMap.size() >= capacity) {
                nodeMap.remove(tail.prev.key);
                remove(tail.prev);

            }

            Node newNode = new Node();
            newNode.key = key;
            newNode.value = value;

            nodeMap.put(key, newNode);
            addToHead(newNode);
        }

    }

    public void addToHead(Node nodeToAdd) {
        Node startNode = head.next;

        // head connections
        head.next = nodeToAdd;
        nodeToAdd.prev = head;

        // startNode connections
        nodeToAdd.next = startNode;
        startNode.prev = nodeToAdd;
    }

    public void remove(Node nodeToDel) {
        Node next = nodeToDel.next;
        Node prev = nodeToDel.prev;

        prev.next = next;
        next.prev = prev;
    }

}