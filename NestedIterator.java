// Time Complexity : O(N)
// Space Complexity : O(N)
// Did this code successfully run on Leetcode : yes
// Any problem you faced while coding this : Nope


// Your code here along with comments explaining your approach
/*
Approach:
1) we have to flatten the list and return a single list.
2) We use a DFS approach to flatten the list and we use a queue to store the integers and return the output.
3) In the dfs function, we iterate over lists and only put integers in queue and not nested elements.

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
 *     // Return empty list if this NestedInteger holds a single integer
 *     public List<NestedInteger> getList();
 * }
 */

 import java.util.*;

public class NestedIterator implements Iterator<Integer> {

    Queue<Integer> queue ;
    
    List<Integer> output;
    
    public NestedIterator(List<NestedInteger> nestedList) {
        
        queue = new LinkedList();
        output = new ArrayList();
        
        dfs(nestedList);
        
        
    }

    @Override
    public Integer next() {
        return queue.poll();
    }

    @Override
    public boolean hasNext() {
        
        return !queue.isEmpty();
    }
    
    
    public void dfs(List<NestedInteger> nestedList)
    {
        if(nestedList==null || nestedList.size()==0)
        {
            return;
        }
        
        for(NestedInteger ni : nestedList)
        {
            if(ni.isInteger())
            {
                queue.add(ni.getInteger());
            }
            else
            {
                dfs(ni.getList());
            }
            
        }
        
        
        
    }
}

/**
 * Your NestedIterator object will be instantiated and called as such:
 * NestedIterator i = new NestedIterator(nestedList);
 * while (i.hasNext()) v[f()] = i.next();
 */