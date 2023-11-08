// Time Complexity : O(1)
// Space Complexity : O(1)
// Did this code successfully run on Leetcode : Yes
// Any problem you faced while coding this : No


class LRUCache {

    HashMap<Integer, DoublyLinkedList> map;
    DoublyLinkedList head = new DoublyLinkedList(-1, -1);
    DoublyLinkedList tail = new DoublyLinkedList(-1, -1);
    int capacity;

    class DoublyLinkedList
    {
        int key;
        int value;
        DoublyLinkedList next;
        DoublyLinkedList prev;

        DoublyLinkedList(int key, int value)
        {
            this.key = key;
            this.value = value;
        }
    }

    public LRUCache(int capacity) {
        map = new HashMap();
        head.next = tail;
        tail.prev = head;
        this.capacity = capacity;
    }

    public int get(int key) {
        if(map.containsKey(key))
        {
            DoublyLinkedList temp = map.get(key);
            remove(temp);
            insert(temp);

            return temp.value;
        }
        return -1;
    }

    public void put(int key, int value) {

        if(map.containsKey(key))
        {
            DoublyLinkedList temp = map.get(key);
            remove(temp);
            temp.value = value;
            insert(temp);
        }
        else
        {
            if(map.size() == capacity)
            {
                DoublyLinkedList temp = tail.prev;
                remove(temp);
                map.remove(temp.key);
            }

            DoublyLinkedList temp = new DoublyLinkedList(key, value);
            insert(temp);
            map.put(key, temp);
        }
    }

    public void insert(DoublyLinkedList node)
    {
        node.next = head.next;
        node.prev = head;
        head.next = node;
        node.next.prev = node;
    }

    public void remove(DoublyLinkedList node)
    {
        node.next.prev = node.prev;
        node.prev.next = node.next;
    }
}
