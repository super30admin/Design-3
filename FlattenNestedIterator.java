// Time Complexity : next(): O(1)     
// Space Complexity : next- O(H) where H is max depth for recursion tree 
// Did this code successfully run on Leetcode : Yes
// Any problem you faced while coding this : No

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Stack;

interface NestedInteger {

    // @return true if this NestedInteger holds a single integer, rather than a
    // nested list.
    public boolean isInteger();

    // @return the single integer that this NestedInteger holds, if it holds a
    // single integer
    // Return null if this NestedInteger holds a nested list
    public Integer getInteger();

    // @return the nested list that this NestedInteger holds, if it holds a nested
    // list
    // Return empty list if this NestedInteger holds a single integer
    public List<NestedInteger> getList();
}

public class FlattenNestedIterator {
    public class NestedIterator implements Iterator<Integer> {
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
                if (!st.peek().hasNext())
                    st.pop();
                else if ((nextEl = st.peek().next()).isInteger())
                    return true;
                else
                    st.push(nextEl.getList().iterator());
            }
            return false;
        }
    }

    public static void main(String[] args) {

        List<NestedInteger> nestedList = new ArrayList<>();

        // Create your nested list here
        // Example: [1, [2, 3], 4, [5, [6, 7]]]

        NestedIterator nestedIterator = new FlattenNestedIterator().new NestedIterator(nestedList);

        while (nestedIterator.hasNext()) {
            System.out.print(nestedIterator.next() + " ");
        }
    }
}
