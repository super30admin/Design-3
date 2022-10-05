// Time Complexity: O(1)
// Space Complexity: O(Capacity)
// Did this code successfully run on Leetcode : Yes
// Any problem you faced while coding this : Yes, I haven't fully understood this problem.
class LRUCache {
    class Node
    {
        int key;
        int val;
        Node next;
        Node prev;
        
        public Node(int key, int val) {
            this.key = key;
            this.val = val;
        }
    
        
    }
    
    
    private void addToHead(Node node)
    {
        node.next = head.next;
        node.prev = head;
        head.next = node;
        node.next.prev = node;
    }
    
    private void removeNode(Node node)
    {
        node.next.prev = node.prev;
        node.prev.next = node.next;
    }
    
    Node head;
    Node tail;
    int capacity;
    HashMap<Integer, Node>map;

    public LRUCache(int capacity) {
        map = new HashMap<>();
        this.head = new Node(-1, -1);
        this.tail = new Node(-1, -1);
        this.head.next = tail;
        this.tail.prev = head;
        this.capacity = capacity;
    }
    
    public int get(int key) 
    {
        if(!map.containsKey(key))
        {
             return -1;       
        }
        Node node = map.get(key);
        removeNode(node);
        addToHead(node);
        return node.val;
        
    }
    
    public void put(int key, int value) 
    {
        
        //existing
        if(map.containsKey(key))
        {
            Node node = map.get(key);
            node.val = value;
            removeNode(node);
            addToHead(node);
        }
        
        //fresh
        else{
            if(capacity == map.size())
            {
                //remove LRU node
                Node tailPrev = tail.prev;
                removeNode(tailPrev);
                map.remove(tailPrev.key);
            }
            Node newNode = new Node(key, value);
            addToHead(newNode);
            map.put(key, newNode);
        }
    }
}


// Time Complexity: O(n)
// Space Complexity: O(n)
// Did this code successfully run on Leetcode : Yes
// Any problem you faced while coding this : no

public class NestedIterator implements Iterator<Integer> 
{
    Queue<Integer> q;
    
    public NestedIterator(List<NestedInteger> nestedList) 
    {
        q = new LinkedList<>(); // Intialize the queue
        dfs(nestedList); //Call the dfs function on the nested List
        
    }

    @Override
    public Integer next() 
    {
        //pop out the first element from the q
        return q.poll();
    }

    @Override
    public boolean hasNext() 
    {
        //returns the q if its not empty.
        return !q.isEmpty();
    }
    private void dfs(List<NestedInteger> nestedList)
    {
        for(NestedInteger element : nestedList)
        {
        
            //If its an integer, add it to the queue else call the dfs function on the list.
            if(element.isInteger())
            {
                q.add(element.getInteger());
            }
            else
            {
                dfs(element.getList());
            }
        }
    
    }
}
