import java.util.List;
import java.util.Iterator;
import java.util.Stack;

interface NestedInteger {
    public boolean isInteger();

    public Integer getInteger();

    public List<NestedInteger> getList();
}

public class Problem2 implements Iterator<Integer> {
    Problem2 ni;
    NestedInteger ne;
    Stack<Iterator> st;

    public Problem2(List<NestedInteger> nestedList) {
        st = new Stack<>();
        st.push(nestedList.iterator());
    }

    //O(1)
    @Override
    public Integer next() {
        return ne.getInteger();
    }

    //O(depth)
    //space: O(depth)
    @Override
    public boolean hasNext() {
        while (!st.isEmpty()) {
            if (!st.peek().hasNext()) {
                st.pop();
            } else if ((ne = (NestedInteger) st.peek().next()).isInteger()) {
                return true;
            } else {
                st.push(ne.getList().iterator());
            }
        }
        return false;
    }
}
