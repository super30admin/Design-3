// # Design-3

// ## Problem 1: Flatten Nested List Iterator (https://leetcode.com/problems/flatten-nested-list-iterator/)
// Time Complexity : O(n)
// Space Complexity : O(n)
// Did this code successfully run on Leetcode : yes
// Any problem you faced while coding this : I didnt face any problem while coding this.

public class NestedIterator implements Iterator<Integer> {
    List<Integer> list; int i; int index;
    public NestedIterator(List<NestedInteger> nestedList) {
        this.list = new ArrayList<>();
        dfs(nestedList);
    }

    private void dfs(List<NestedInteger> nestedList){
        for(NestedInteger el: nestedList){
            if(el.isInteger()){
                list.add(el.getInteger());
            }else{
                dfs(el.getList());
            }
        }
    }
    @Override
    public Integer next() {
        int toReturn = list.get(index);
        index++;
        return toReturn;
    }

    @Override
    public boolean hasNext() {
        return index!=list.size();
    }
}

// # Design-3

// ## Problem 1: Flatten Nested List Iterator (https://leetcode.com/problems/flatten-nested-list-iterator/)
// Time Complexity : O(1)
// Space Complexity : O(d)
// Did this code successfully run on Leetcode : yes
// Any problem you faced while coding this : I didnt face any problem while coding this.

public class NestedIterator implements Iterator<Integer> {
    Stack<Iterator<NestedInteger>> st;
    NestedInteger nextEl;
    public NestedIterator(List<NestedInteger> nestedList) {
        this.st = new Stack<>();
        this.st.push(nestedList.iterator());
    }

    @Override
    public Integer next() { //O(1)
        return nextEl.getInteger();
    }

    @Override
    public boolean hasNext() { //O(1)
        while(!st.isEmpty()){
            if(!st.peek().hasNext()){
                st.pop();
            }else if((nextEl = st.peek().next()).isInteger()){
                return true;
            }else{
                st.push(nextEl.getList().iterator());
            }
        }
        return false;
    }
}

// ## Problem 2: LRU Cache(https://leetcode.com/problems/lru-cache/)
// Time Complexity : O(1)
// Space Complexity : O(capacity)
// Did this code successfully run on Leetcode : yes
// Any problem you faced while coding this : I didnt face any problem while coding this.

class LRUCache {

    Node head = new Node();
    Node tail = new Node();
    int capacity;
    Map<Integer, Node> map;

    public LRUCache(int capacity) {
        head.next = tail;
        tail.prev = head;
        map = new HashMap(capacity);
        this.capacity = capacity;
    }
    
    public int get(int key) {
        int result = -1;
        Node node = map.get(key);
        if(node != null) {
            result = node.val;
            //add it to the front of cache as its recently used
            remove(node);
            add(node);
        }
        return result;
    }
    
    public void put(int key, int value) {
        Node node = map.get(key);
        if(node != null) {
            //to add it at the front of the cache as its most recently used
            remove(node);
            node.val = value;
            add(node);
        } else {
            if(map.size() == capacity) {
                map.remove(tail.prev.key);
                remove(tail.prev);
            }
            Node new_node = new Node();
            new_node.key = key;
            new_node.val = value;
            map.put(key, new_node);
            add(new_node);
        }
    }

    public void add(Node node) {
        Node nextNode = head.next;
        node.next = nextNode;
        nextNode.prev = node;
        head.next = node;
        node.prev = head;
    }

    public void remove(Node node) {
        Node nextNode = node.next;
        Node prevNode = node.prev; 
        nextNode.prev = prevNode;
        prevNode.next = nextNode;
    }

    class Node {
        Node prev;
        Node next;
        int key, val;
    } 
}