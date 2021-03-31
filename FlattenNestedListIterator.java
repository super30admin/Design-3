// Time Complexity : O(n)
// Space Complexity : O(n)
// Did this code successfully run on Leetcode :Yes
// Any problem you faced while coding this :No


// Your code here along with comments explaining your approach



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
    //have a queue implementation
    private Queue<Integer> queue;

    public NestedIterator(List<NestedInteger> nestedList) {
      
      queue = new LinkedList<>();
      dfs(nestedList);
        
    }

    private void dfs(List<NestedInteger> nestedList)
    {
      if(nestedList == null || nestedList.size()==0)
      {
        return;
      }
      //iterate over all the elements in the input list
      for(NestedInteger e : nestedList)
      {
        //i the current element is an integer add it to the queue
        if(e.isInteger())
        {
          queue.add(e.getInteger());
        }
        else
        {
          //if not an integer then pass the list to the dfs
          dfs(e.getList());
        }
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
 */