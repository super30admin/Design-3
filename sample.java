// Time Complexity : O(1) 
// Space Complexity : O(1) as capacity is fixed
// Did this code successfully run on Leetcode : Yes
// Any problem you faced while coding this : None


class LRUCache {
    class Node // Doubly Linkedlist node for maintaining order
    {
        int key; //key value pairs
        int val;
        Node prev;
        Node next;
        
        public Node(int key, int val) //constructor
        {
            this.key = key;
            this.val = val;
        }
    }
    
    private void removeNode(Node node) // to remove any node
    {
        node.next.prev = node.prev;
        node.prev.next = node.next;
    }
    
    private void addtoHead(Node node) // to add most recently used node to head
    {
        node.next = head.next;
        node.prev = head;
        head.next = node;
        node.next.prev = node;
    }
    
    HashMap<Integer, Node> map; // map to store the (key,node) pairs
    Node head; // to add most recently element to head
    Node tail; // to remove least recently used element from tail
    int capacity; // global capacity
    
    public LRUCache(int capacity) {
        map = new HashMap<>();
        this.capacity = capacity;
        head = new Node(-1,-1);
        tail = new Node(-1,-1);
        head.next = tail; // connecting head and tail initially
        tail.prev = head;
    }
    
    public int get(int key) {
        if(!map.containsKey(key)) return -1; // if key not present
        
        Node node = map.get(key); // getting node reference corresponding to the key value
        removeNode(node); //remove that node and move to head as it is most recently used now
        addtoHead(node);
        return node.val; //return its value
    }
    
    public void put(int key, int value) {
        if(map.containsKey(key)) // if map already contains the key
        {
            Node node = map.get(key); //get then node reference from the map
            node.val = value; // update its value
            removeNode(node); // remove and add it to head as most recently used
            addtoHead(node);
        }
        else
        { //new node to be added
            if(capacity == map.size()) //check if reached the capacity of the cache
            {
                Node tailnode = map.get(tail.prev.key); //get the last node as least recently used
                removeNode(tailnode); //remove it from list
                map.remove(tailnode.key); //remove it from hashmap
            }
            
            Node node = new Node(key, value); //create the new node 
            map.put(key, node); //add it to map
            addtoHead(node); // add it to head as most recent used
        }
    }
}

// Time Complexity : O(n) iterating through the list + recursion stack space
// Space Complexity : O(n) queue
// Did this code successfully run on Leetcode : Yes
// Any problem you faced while coding this : None


/**
 * Your LRUCache object will be instantiated and called as such:
 * LRUCache obj = new LRUCache(capacity);
 * int param_1 = obj.get(key);
 * obj.put(key,value);
 */


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
 *     // Return null if this NestedInteger holds a single integer
 *     public List<NestedInteger> getList();
 * }
 */
public class NestedIterator implements Iterator<Integer> {
    Queue<Integer> q; //global queue
    public NestedIterator(List<NestedInteger> nestedList) {
        q = new LinkedList<>();
        flatten(nestedList); // falttening the list
    }

    public void flatten(List<NestedInteger> nestedList)
    {
        for(NestedInteger l: nestedList) // iterating through each element in list
        {
            if(l.isInteger()) //checking if it is integer
            {
                q.add(l.getInteger()); // get the integer value and add to queue
            }
            else
                flatten(l.getList()); // else get the list and call flatten on it
        }
    }
    
    @Override
    public Integer next() {
        return q.poll(); // get the front value in queue
    }

    @Override
    public boolean hasNext() {
        return !q.isEmpty();  // if queue is not empty, it has a next value
    }

}

/**
 * Your NestedIterator object will be instantiated and called as such:
 * NestedIterator i = new NestedIterator(nestedList);
 * while (i.hasNext()) v[f()] = i.next();
 */






// Time Complexity : O(1) average ....hasnexxt gives O(n) but average O(1)
// Space Complexity : O(n) stack
// Did this code successfully run on Leetcode : Yes
// Any problem you faced while coding this : None


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
 *     // Return null if this NestedInteger holds a single integer
 *     public List<NestedInteger> getList();
 * }
 */
public class NestedIterator implements Iterator<Integer> {
    Stack<Iterator<NestedInteger>> st; //gloabl stack
    NestedInteger nextEl; // to store the next element globally
    public NestedIterator(List<NestedInteger> nestedList) {
       st = new Stack<>(); 
       st.push(nestedList.iterator()); // pushing the list to stack initially
    }

    @Override
    public Integer next() {
        return nextEl.getInteger();  //get the integer from the nextEl nestedinteger element
    }

    @Override
    public boolean hasNext() {
        while(!st.isEmpty()) // till stack is not empty
        {
           if(!st.peek().hasNext()) //check if the element at peek has a next element, if not it has been traversed so pop
           {
              st.pop(); 
           }
            else if((nextEl = st.peek().next()).isInteger()) // check if the peek next element is integer, next moves the iterator so storing the value in nextEl
            {
                return true; // value present is integer
            }
            else
            {
                st.push(nextEl.getList().iterator()); // get the list and put an iterator and push to stack
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