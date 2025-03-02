// Time Complexity : O(n) - where n is total number of nested integers
// Space Complexity : O(depth) - depth of nested list
// Did this code successfully run on Leetcode : Yes
// Any problem you faced while coding this : No

// Your code here along with comments explaining your approach

public class FlattenedNestedListIterator {
    Stack<Iterator<NestedInteger>> st;
    NestedInteger nextEl;

    public NestedIterator(List<NestedInteger> nestedList) {
        this.st = new Stack<>();
        st.push(nestedList.iterator());
    }

    @Override
    public Integer next() {
        return nextEl.getInteger();
    }

    @Override
    public boolean hasNext() {
        while (!st.isEmpty()) {
            if (!st.peek().hasNext()) {
                st.pop();
            } else if ((nextEl = st.peek().next()).isInteger()) {
                return true;
            } else {
                st.push(nextEl.getList().iterator());
            }
        }
        return false;
    }
}