// Time Complexity : O(N)
// Space Complexity :O(D)  D is the maximum depth of nesting in the input list
// Did this code successfully run on Leetcode : Yes
// Any problem you faced while coding this : No


/* implements an iterator for a nested list, enabling iteration over integers in a flattened structure.
 *
 */
public class problem2 implements Iterator<Integer> {
    private Stack<Iterator<NestedInteger>> st;
    private NestedInteger nextEl;
    public NestedIterator(List<NestedInteger> nestedList) {
        st = new Stack<>();
        st.push(nestedList.iterator());
    }

    @Override
    public Integer next() {
        return nextEl.getInteger();
    }

    @Override
    public boolean hasNext() {
        while(!st.isEmpty()){
            if(!st.peek().hasNext())
                st.pop();
            else if((nextEl = st.peek().next()).isInteger())
                return true;
            else
                st.push(nextEl.getList().iterator());

        }
        return false;
    }
}

/**
 * Your NestedIterator object will be instantiated and called as such:
 * NestedIterator i = new NestedIterator(nestedList);
 * while (i.hasNext()) v[f()] = i.next();
 */