import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Stack;

public class FlattenNestedIterator implements Iterator<Integer> {
    private NestedInteger nextEl;
    private Stack<Iterator<NestedInteger>> st;

    public NestedIterator(List<NestedInteger> nestedList) {
        this.st = new Stack<>();
        st.push(nestedList.iterator());
    }

    @Override
    public Integer next() { // O(1)
        return nextEl.getInteger();
    }

    @Override
    public boolean hasNext() {
        while(!st.isEmpty()) { //O(D)
            if(!st.peek().hasNext()) {
                st.pop();
            } else if((nextEl = st.peek().next()).isInteger()) return true;
            else {
                st.push(nextEl.getList().iterator());
            }
        }
        return false;
    }