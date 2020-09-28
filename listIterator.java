    /*  Explanation
    # Leetcode problem link : https://leetcode.com/problems/flatten-nested-list-iterator/
    Time Complexity for operators : o(n) .. 
    Extra Space Complexity for operators : o(n) ... used queues
    Did this code successfully run on Leetcode : Yes
    Any problem you faced while coding this : No
# Your code here along with comments explaining your approach
        # Basic approach : 

        # Optimized approach:
                              

             approach   
                    A. We will use queue to store the data.
                    B. Do the recusion on the nestedList. if it is a integer then add to the queue
                    C. If not the do the recusion call with that list.
                    D. For next(), just poll the queue.
                    E. for hasNext, return is queue is not empty.

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

    Queue<Integer> queue;
    public NestedIterator(List<NestedInteger> nestedList) {
        queue = new LinkedList<>();
        recursion(nestedList);
    }

    @Override
    public Integer next() {
        return queue.poll();
    }

    @Override
    public boolean hasNext() {
        return !queue.isEmpty();
    }
    
    public void recursion(List<NestedInteger> nestedList){
        if(nestedList == null)
            return;
        
        for(NestedInteger value : nestedList){
            if(value.isInteger()){
                queue.add(value.getInteger());
            }else{
                recursion(value.getList());
            }
        }
    }
}

/**
 * Your NestedIterator object will be instantiated and called as such:
 * NestedIterator i = new NestedIterator(nestedList);
 * while (i.hasNext()) v[f()] = i.next();
 */