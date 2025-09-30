//TC: Amortized O(1)
//SC: O(n)
import java.util.Iterator;
import java.util.List;
import java.util.Stack;

public class NestedIterator implements Iterator<Integer> {

    private final Stack<Iterator<NestedInteger>> stack;
    private NestedInteger next;

    public NestedIterator(List<NestedInteger> nestedList) {
        this.stack = new Stack<>();
        this.stack.push(nestedList.iterator());
    }

    @Override
    public boolean hasNext() {
        while (!stack.isEmpty()) {
            if (!stack.peek().hasNext()) {
                stack.pop();
            } else if ((next = stack.peek().next()).isInteger()) {
                return true;
            } else {
                stack.push(next.getList().iterator());
            }
        }

        return false;
    }

    @Override
    public Integer next() {
        return this.next.getInteger();
    }

    private interface NestedInteger {
        boolean isInteger();

        Integer getInteger();

        List<NestedInteger> getList();
    }
}
