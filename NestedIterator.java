//Runtime complexity - NestedIterator constructor takes O(N+D) for the loop, since the flattenlist is the recrusion - recursion stack is involved. next() and hasnext() is O(1)
//space complexity - O(N+D) for the queue, since the flattenlist is the recrusion - recursion stack is involved.

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

    public Queue<Integer> q1;
    
    public NestedIterator(List<NestedInteger> nestedList) {
        q1=new LinkedList<Integer>();
        flatten(nestedList);
    }

    public void flatten(List<NestedInteger> nestedList)
    {
        for(NestedInteger nested:nestedList)
        {
            if(nested.isInteger())
                q1.add(nested.getInteger());
            else
                flatten(nested.getList());
        }
    }
    
    @Override
    public Integer next() {
        return q1.poll();
    }

    @Override
    public boolean hasNext() {
        return !q1.isEmpty();
    }
}

/**
 * Your NestedIterator object will be instantiated and called as such:
 * NestedIterator i = new NestedIterator(nestedList);
 * while (i.hasNext()) v[f()] = i.next();
 */
