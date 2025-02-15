//TC --> O(1)
//SC --> O(n)

public class LRUCache
{

    public class DoublyLinkedList
    {
        public int key;
        public int value;
        public DoublyLinkedList prev;
        public DoublyLinkedList next;

        public DoublyLinkedList(int key, int value)
        {
            this.key = key;
            this.value = value;
        }
    }
    Dictionary<int, DoublyLinkedList> cache;
    DoublyLinkedList head, tail;
    int capacity;
    public LRUCache(int capacity)
    {
        this.capacity = capacity;
        cache = new Dictionary<int, DoublyLinkedList>();
        head = new DoublyLinkedList(-1, -1);
        tail = new DoublyLinkedList(-1, -1);
        head.next = tail;
        tail.prev = head;
    }

    public void AddNearHead(DoublyLinkedList node)
    {
        head.next.prev = node;
        node.next = head.next;
        node.prev = head;
        head.next = node;
    }

    public void Remove(DoublyLinkedList node)
    {
        node.next.prev = node.prev;
        node.prev.next = node.next;

    }
    public int Get(int key)
    {
        if (cache.ContainsKey(key))
        {
            var node = cache[key];
            Remove(node);
            AddNearHead(node);

            return cache[key].value;
        }
        return -1;
    }

    public void Put(int key, int value)
    {
        if (cache.ContainsKey(key))
        {
            var node1 = cache[key];
            Remove(node1);
            node1.value = value;
            AddNearHead(node1);
            return;
        }
        if (cache.Count == capacity)
        {

            cache.Remove(tail.prev.key);
            Remove(tail.prev);
        }
        var node = new DoublyLinkedList(key, value);

        AddNearHead(node);
        cache.Add(key, node);
    }
}

/**
 * Your LRUCache object will be instantiated and called as such:
 * LRUCache obj = new LRUCache(capacity);
 * int param_1 = obj.Get(key);
 * obj.Put(key,value);
 */