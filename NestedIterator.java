
// Time Complexity : O(n)
// Space Complexity : O(n)
// Did this code successfully run on Leetcode : Yes
// Any problem you faced while coding this : No


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
 *     // Return null if this NestedInteger holds a single integer
 *     public List<NestedInteger> getList();
 * }
 */
public class NestedIterator implements Iterator<Integer> {

    Stack<ListIterator<NestedInteger>> st = new Stack<>();
    public NestedIterator(List<NestedInteger> nestedList) {
        st.push(nestedList.listIterator());
    }

    @Override
    public Integer next() {
        
        hasNext();
        return st.peek().next().getInteger();
        
    }

    @Override
    public boolean hasNext() {
       while(!st.isEmpty())
       {
            
        if(!st.peek().hasNext())
        {
            st.pop();
        }
        else{
            NestedInteger x = st.peek().next();
            if(x.isInteger())
                return st.peek().previous() == x;
            else
            {
                st.push(x.getList().listIterator());
            }
            
        }
       }
        return false;
    }
}

/**
 * Your NestedIterator object will be instantiated and called as such:
 * NestedIterator i = new NestedIterator(nestedList);
 * while (i.hasNext()) v[f()] = i.next();
 */