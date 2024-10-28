# Design-3

## Problem 1: Flatten Nested List Iterator (https://leetcode.com/problems/flatten-nested-list-iterator/)


Approached Solution: In this implementation of NestedIterator we will use stack to flatten a nested list structure dynamically. Instead of flattening the entire list in advance, we will process elements on demand. The hasNext method will iterate through the structure checking if the current item is an integer or a nested list. If it encounters an integer it will make it available for retrieval by next. If it encounters a nested list then it will add an iterator for that list onto the stack enabling depth-first traversal. This menthod will only delve into nested lists when necessary preserving memory.

Time Complexity: O(N) for iterating over all integers in the nested list structure where N is the total number of integers as each integer is visited once.

Space Complexity: O(D) where D is the meximum depth of the nested lists. The stack will hold at most one iterator per depth level making it proportional to the depth of nesting.

Approached Code:

/**
 * // This is the interface that allows for creating nested lists.
 * // You should not implement it, or speculate about its implementation
 * public interface NestedInteger {
 *
 *     // @return true if this NestedInteger holds a single integer, rather than a nested list.
 *     public boolean isInteger();
 *
 *     // @return the single integer that this NestedInteger holds, if it holds a single integer
 *     // Return null if this NestedInteger holds a nested list
 *     public Integer getInteger();
 *
 *     // @return the nested list that this NestedInteger holds, if it holds a nested list
 *     // Return empty list if this NestedInteger holds a single integer
 *     public List<NestedInteger> getList();
 * }
 */
public class NestedIterator implements Iterator<Integer> {

    // private List<Integer> li;
    // private int i;

    private Stack<Iterator<NestedInteger>> st;
    private NestedInteger nextEle;

    public NestedIterator(List<NestedInteger> nestedList) {
        // this.li = new ArrayList<>();
        // dfs(nestedList);

        this.st = new Stack<>();
        this.st.push(nestedList.iterator());
    }

    private void dfs(List<NestedInteger> nestedList) {

        // for(NestedInteger nestedInteger: nestedList) {
        //     if(nestedInteger.isInteger()) {
        //         li.add(nestedInteger.getInteger());
        //     } else {
        //         dfs(nestedInteger.getList());
        //     }
        // }

    }

    @Override
    public Integer next() {
        // int result = li.get(i);
        // i++;
        // return result;

        return nextEle.getInteger();
    }

    @Override
    public boolean hasNext() {
        // if(i == li.size()) {
        //     return false;
        // }

        // return true;

        while(!st.isEmpty()) {
            if(!st.peek().hasNext()) {
                st.pop();
            } else if((nextEle = st.peek().next()).isInteger()) {
                return true;
            } else {
                st.push(nextEle.getList().iterator());
            }
        }

        return false;
    }
}

/**
 * Your NestedIterator object will be instantiated and called as such:
 * NestedIterator i = new NestedIterator(nestedList);
 * while (i.hasNext()) v[f()] = i.next();
 */



## Problem 2: LRU Cache(https://leetcode.com/problems/lru-cache/)



Approached Solution: In this implementation of LRU (Least Recently Used) cache we will use HashMap and a doubly linked list. The HashMap will store key-value pairs for quick access, while the doubly linked list will maintain the order of the access. The most recently accessed or added item will be kept at the head of the list and the least recently used item will be at the tail. When a cache miss will occur and the capacity is reached we will remove least recently used item. For each access (get or put), the accessed node will be moved to the head ensuring it is the most recently used.

Time Complexity: O(1) for both get and put operations, as the HashMap provided constant time access and the doubly linked list enables constant time removal and insertion.

Space Complexity: O(N) where N is the cache capacity. The HashMap and doubly linked list store up to capacity nodes requiring space proportional to N.

Approached Code:

class LRUCache {

    class Node {
        int key;
        int val;
        Node next;
        Node prev;

        public Node(int key, int value) {
            this.key = key;
            this.val = value;
        }
    }

    private void removeNode(Node node) {
        node.next.prev = node.prev;
        node.prev.next = node.next;
    }

    private void addToHead(Node node) {
        node.next = this.head.next;
        node.prev = this.head;
        this.head.next = node;
        node.next.prev = node;
    }

    private HashMap<Integer, Node> map;
    private Node head;
    private Node tail;
    int capacity;

    public LRUCache(int capacity) {
        this.capacity = capacity;
        this.map = new HashMap<>();
        this.head = new Node(-1, -1);
        this.tail = new Node(-1, -1);
        this.head.next = this.tail;
        this.tail.prev = this.head;
    }
    
    public int get(int key) {
        if(!map.containsKey(key)) {
            return -1;
        }

        Node node = map.get(key);

        removeNode(node);
        addToHead(node);

        return node.val;
    }
    
    public void put(int key, int value) {
        if(map.containsKey(key)) {
            Node node = map.get(key);
            node.val = value;
            removeNode(node);
            addToHead(node);
        } else {
            if(map.size() == capacity) {
                Node tailPrev = this.tail.prev;
                removeNode(tailPrev);
                map.remove(tailPrev.key);
            } 

            Node newNode = new Node(key, value);
            addToHead(newNode);
            map.put(key, newNode);
        }
    }
}

/**
 * Your LRUCache object will be instantiated and called as such:
 * LRUCache obj = new LRUCache(capacity);
 * int param_1 = obj.get(key);
 * obj.put(key,value);
 */


