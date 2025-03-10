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
    Stack<Iterator<NestedInteger>> s = new Stack<>();
    NestedInteger nextel = null;
    public NestedIterator(List<NestedInteger> nestedList) {
        if (nestedList != null) {
            s.push(nestedList.iterator());
        }
    }

    @Override
    public Integer next() {
        return nextel.getInteger();
    }

    @Override
    public boolean hasNext() {
        while (!s.isEmpty()) {
            if (!s.peek().hasNext()) {
                s.pop();
            } else if ((nextel = s.peek().next()).isInteger()) {
                return true;
            } else{
                s.push(nextel.getList().iterator());
            }
        }
        return false;
    }
}


class LRUCache {
    Map<Integer, ListNode> m = null;
    ListNode head=null;
    ListNode tail=null;
    int capacity=0;
    int nodes=0;
    class ListNode {
        int key, val;
        ListNode left;
        ListNode right;
        public ListNode(int key, int val) {
            this.key =key;
            this.val=val;
            left=null;
            right=null;
        }
    }
    public LRUCache(int capacity) {
       m = new HashMap<Integer, ListNode>();
       this.capacity = capacity;
       head = new ListNode(-1, -1);
       tail = new ListNode(-1, -1);
       head.right = tail;
       tail.left=head;
    }
    public int get(int key) {
        if (m.containsKey(key)) {
            ListNode l = m.get(key);
            removenode(l);
            addtohead(l);
            return l.val;
        } 
        return -1;
    }
    public void put(int key, int value) {
        if (m.containsKey(key)) {
            ListNode l = m.get(key);
            removenode(l);
            l = new ListNode(key, value);
            m.put(key, l);
            addtohead(l);
            return;
        }
        nodes++;
        if (nodes > capacity) {
            int tailkey= tail.left.key;
            ListNode l = m.get(tail.left.key);
            System.out.println(l.val);
            removenode(l);
            m.remove(tailkey);
        }
        
        ListNode curr = new ListNode(key, value);
        addtohead(curr);
        m.put(key, curr);
    }
    private void addtohead(ListNode curr) {
        curr.right = head.right;
        curr.right.left = curr;
        curr.left=head;
        head.right=curr;
    }
    private void removenode(ListNode curr) {
        curr.left.right = curr.right;
        curr.right.left = curr.left;
    }
}

/**
 * Your LRUCache object will be instantiated and called as such:
 * LRUCache obj = new LRUCache(capacity);
 * int param_1 = obj.get(key);
 * obj.put(key,value);
 */

/**
 * Your NestedIterator object will be instantiated and called as such:
 * NestedIterator i = new NestedIterator(nestedList);
 * while (i.hasNext()) v[f()] = i.next();
 */
