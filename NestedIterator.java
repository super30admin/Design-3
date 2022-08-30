// Time Complexity : O(d) // d is the depth of the nested list, avg is O(1)
// Space Complexity : O(d)
// Did this code successfully run on Leetcode : yes
// Any problem you faced while coding this : no

// Your code here along with comments explaining your approach

import java.util.*;

public class NestedIterator implements Iterator<Integer> { // handle concurrent changes
    // (iterator) - used to handle dynamic nature.only concerned about the very next
    Stack<Iterator<NestedInteger>> st;
    NestedInteger nextEl;

    public NestedIterator(List<NestedInteger> nestedList) {
        st = new Stack();
        st.push(nestedList.iterator());
    }

    @Override
    public Integer next() { // O(1)
        return nextEl.getInteger();
    }

    @Override
    public boolean hasNext() { // O()
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

/**
 * Your NestedIterator object will be instantiated and called as such:
 * NestedIterator i = new NestedIterator(nestedList);
 * while (i.hasNext()) v[f()] = i.next();
 */