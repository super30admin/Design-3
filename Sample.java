## Problem 1: Flatten Nested List Iterator (https://leetcode.com/problems/flatten-nested-list-iterator/)

// Time Complexity - 0(n) where next() has time complexity of 0(1) and hasNext() with also 0(1)
// Space Complexity - 0(n)

public class NestedIterator implements Iterator<Integer> {
    private Deque<NestedInteger> stack;
    private Integer nextElement;

    public NestedIterator(List<NestedInteger> nestedList) {
        stack = new ArrayDeque<>();
        flattenList(nestedList);   
        findNextElement();  
    }

    @Override
    public Integer next() {
        Integer currentElement = nextElement;
        findNextElement();
        return currentElement;   
    }

    @Override
    public boolean hasNext() {
        return nextElement != null;
    }

    private void findNextElement() {
        nextElement = null;
        while (!stack.isEmpty()) {
            NestedInteger current = stack.pop();
            if (current.isInteger()) {
                nextElement = current.getInteger();
                break;
            }
            flattenList(current.getList());
        }
    }

    private void flattenList(List<NestedInteger> nestedList) {
        for (int i = nestedList.size() - 1; i >= 0; i--) {
            stack.push(nestedList.get(i));
        }
    }
}

/**
 * Your NestedIterator object will be instantiated and called as such:
 * NestedIterator i = new NestedIterator(nestedList);
 * while (i.hasNext()) v[f()] = i.next();
 */


## Problem 2: LRU Cache(https://leetcode.com/problems/lru-cache/)

// Time Complexity - 0(1) because all the operation are in constant Time
// Space Complexity - 0(c) where c is capacity

class LRUCache {
    class Node {
        int key, value;
        Node next, prev;
        public Node(int key, int value) {
            this.key = key;
            this.value = value;
        }
    }

    private void addToHead (Node node) {
        node.next = head.next;
        node.next.prev = node;
        node.prev = head;
        head.next = node;
    }

    private void removeNode (Node node) {
        node.next.prev = node.prev;
        node.prev.next = node.next;
    }
    HashMap<Integer, Node> map;
    int capacity;
    Node head, tail;

    public LRUCache(int capacity) {
        this.capacity = capacity;
        head = new Node(-1, -1);
        tail = new Node(-1, -1);
        head.next = tail;
        tail.prev = head;
        map = new HashMap<>();
    }
    
    public int get(int key) {
        if (!map.containsKey(key)) {
            return -1;
        }
        Node node = map.get(key);
        removeNode(node);
        addToHead(node);
        return node.value;     
    }
    
    public void put(int key, int value) {
        if (map.containsKey(key)) {
            Node node = map.get(key);
            removeNode(node);
            addToHead(node);
            node.value = value;
            return;
        }
        if (map.size() == capacity) {
            Node nodeprev = tail.prev;
            removeNode(nodeprev);
            map.remove(nodeprev.key);
        }
        Node node = new Node(key, value);
        addToHead(node);
        map.put(key, node);
    }
}

/**
 * Your LRUCache object will be instantiated and called as such:
 * LRUCache obj = new LRUCache(capacity);
 * int param_1 = obj.get(key);
 * obj.put(key,value);
 */