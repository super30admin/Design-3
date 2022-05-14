// Time Complexity : O(1)
// Space Complexity : O(1)
// Did this code successfully run on Leetcode : Yes
// Any problem you faced while coding this : No


// Your code here along with comments explaining your approach

public class Node
{
    public int key, val;
    public Node next, prev;
    public Node(int key, int val)
    {
        this.key = key;
        this.val = val;
    }
}

Node head, tail;
int capacity;
Dictionary<int, Node> map;

private void removeNode(Node node)
{
    node.prev.next = node.next;
    node.next.prev = node.prev;
}

private void addToHead(Node node)
{
    //move access node to head or start of list
    //so that at tail is least recently used node to delete later
    node.next = head.next;
    node.prev = head;
    node.next.prev = node;
    head.next = node;
}

public LRUCache(int capacity) {
    map = new Dictionary<int, Node>();
    head = new Node(-1, -1);
    tail = new Node(-1, -1);
    head.next = tail;
    tail.prev = head;
    this.capacity = capacity;
}

public int Get(int key) {
    if(!map.ContainsKey(key))
        return -1;
    
    var node = map[key];
    removeNode(node);
    addToHead(node);
    
    return node.val;
}

public void Put(int key, int value) {
    if(map.ContainsKey(key))
    {
        var node = map[key];
        node.val = value;
        removeNode(node);
        addToHead(node);
        return;
    }
    
    if(capacity == map.Count)
    {
        Node prevTail = tail.prev;
        removeNode(prevTail);
        map.Remove(prevTail.key);
    }
    
    Node newNode = new Node(key, value);
    map.Add(key, newNode);
    addToHead(newNode);
}