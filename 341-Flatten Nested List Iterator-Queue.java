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
 
// Time Complexity=O(N) For storing element in queue
// Space Complexity=O(N)
// Did this code successfully run on Leetcode : Yes
// Any problem you faced while coding this : No

public class NestedIterator implements Iterator<Integer> {
    Queue<Integer> q; //Here we are creating a queue to store all the integers
    public NestedIterator(List<NestedInteger> nestedList) {
        q=new LinkedList<>();
        flatten(nestedList);
    }
private void flatten(List<NestedInteger> nestedList)
{
    if(nestedList==null) return; //We are iterating the entire list and checking whether the nexte 
    for(NestedInteger  e: nestedList){//element is list or integer and as per that we are doing the 
    if(e.isInteger())                 //operations
        q.add(e.getInteger());
    else
        flatten(e.getList());
    }
    
}
    @Override
    public Integer next() {
    return q.poll();
    
    }

    @Override//Checks whether there is next element or not
    public boolean hasNext() {
        if(q.peek()==null)
            return false;
        else return true;
    }
}

/**
 * Your NestedIterator object will be instantiated and called as such:
 * NestedIterator i = new NestedIterator(nestedList);
 * while (i.hasNext()) v[f()] = i.next();
 */