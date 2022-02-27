import java.util.Iterator;
import java.util.List;
import java.util.Stack;

// TC O(1)
// SC O(D) D be the maximum nesting depth (maximum number of lists inside each other)
interface NestedInteger {

    // @return true if this NestedInteger holds a single integer, rather than a
    // nested list.
    public boolean isInteger();

    // @return the single integer that this NestedInteger holds, if it holds a
    // single integer
    // Return null if this NestedInteger holds a nested list
    public Integer getInteger();

    // @return the nested list that this NestedInteger holds, if it holds a
    // nested list
    // Return empty list if this NestedInteger holds a single integer
    public List<NestedInteger> getList();
}

public class NestedIterator implements Iterator<Integer> {
    Stack<Iterator<NestedInteger>> st;
    NestedInteger nextEle;

    public NestedIterator(List<NestedInteger> nestedList) {
        st = new Stack<>();
        st.push(nestedList.iterator());
    }

    @Override
    public Integer next() {
        return nextEle.getInteger();
    }

    @Override
    public boolean hasNext() {
        while (!st.isEmpty()) {
            if (!st.peek().hasNext()) {
                st.pop();
            } else if ((nextEle = st.peek().next()).isInteger()) {
                return true;
            } else {
                st.push(nextEle.getList().iterator());
            }
        }
        return false;
    }
}
