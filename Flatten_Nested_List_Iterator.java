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
//Time Complexity-O(n)
//Space Complexity-O(n)
public class NestedIterator implements Iterator<Integer> {
  private Queue<Integer>queue=new LinkedList();
    public NestedIterator(List<NestedInteger> nestedList) {
        recur(nestedList);
    }

    @Override
    public Integer next() {
        return queue.poll();
    }

    @Override
    public boolean hasNext() {
        return !queue.isEmpty();
    }
    private void recur(List<NestedInteger>nestedList)
    {
        for(NestedInteger nestedInteger:nestedList)
        {
            if(nestedInteger.isInteger())
            {
                queue.add(nestedInteger.getInteger());
            }
            else
            {
                recur(nestedInteger.getList());                
            }
        }
    }

}

/**
 * Your NestedIterator object will be instantiated and called as such:
 * NestedIterator i = new NestedIterator(nestedList);
 * while (i.hasNext()) v[f()] = i.next();
 */