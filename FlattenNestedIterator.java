// Time Complexity :O(n) 
// Space Complexity :O(l) 
// Did this code successfully run on Leetcode : Yes
// Any problem you faced while coding this : No


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
    Stack<Iterator<NestedInteger>> st;
    public NestedIterator(List<NestedInteger> nestedList) {
        st = new Stack<>();
        st.push(nestedList.iterator());
    }
    
    NestedInteger nextInt;

    @Override
    public Integer next() {
        return nextInt.getInteger();
    }

    @Override
    public boolean hasNext() {
        while(!st.isEmpty()){
            if(!st.peek().hasNext()){
                st.pop();
            }
            else if((nextInt = st.peek().next()).isInteger()){
                return true;
            }
            else{
                st.push(nextInt.getList().iterator());
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