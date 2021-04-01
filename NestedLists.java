//TC: O(number of integers)
//SC: O(nestedlists)
//Executed on leetcode.
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
    Queue<Integer> queue = new LinkedList<>();
    public NestedIterator(List<NestedInteger> nestedList) {
        
        dfs(nestedList);
        //return queue;        
    }
    
    void dfs(List<NestedInteger> nestedList)
    {
        for(NestedInteger list: nestedList) //Adding each value to queue using recursion.
        {                               //the NestedInteger class has isInteger method which says if the index value is integer or not.
            if(list.isInteger())    //If the value is integer adding to queue, else using recursion calling the dfs value.
            {
                queue.add(list.getInteger());//If queue has the value return the value on next().
            }                               //If queue is not empty return on hasNext using is Empty().
            else
            {
                List<NestedInteger> l = list.getList();
                if(l.size()!=0)
                    dfs(l);                
            }
        }
    }

    @Override
    public Integer next() {
        if(hasNext())
            return queue.poll();
        return -1;
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