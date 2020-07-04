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
//space: O(n)
// time: O(n)  for storing elements in queue   
public class NestedIterator implements Iterator<Integer> {
    Queue<Integer> queue;
    public NestedIterator(List<NestedInteger> nestedList) {
       queue=new LinkedList<>();
       dfs(nestedList); 
    }
    private void dfs(List<NestedInteger> nestedList)
    {
        for(NestedInteger e: nestedList)
        {
            if(e.isInteger()) queue.add(e.getInteger());
            else{
            dfs(e.getList()); }
        }
    }

    @Override
    public Integer next() {
        return queue.poll();
    }

    @Override
    public boolean hasNext() {
        return !queue.isEmpty();
    }
}

/**
 * Your NestedIterator object will be instantiated and called as such:
 * NestedIterator i = new NestedIterator(nestedList);
 * while (i.hasNext()) v[f()] = i.next();
 */{\rtf1}