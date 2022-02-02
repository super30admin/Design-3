import java.util.Iterator;
import java.util.List;
import java.util.Stack;
//TC O(h) where h is the length of the inner NesterInteger
//SC O(h)
public class NestedIterator implements Iterator<Integer> {
    Stack<Iterator<NestedInteger>> stack;
    NestedInteger nextEl;

    public NestedIterator(List<NestedInteger> nestedList) {
        stack = new Stack<>();
        stack.push(nestedList.iterator());
    }

    @Override
    public Integer next() {
        return nextEl.getInteger();
    }

    @Override
    public boolean hasNext() {
        while (!stack.isEmpty()) {
            if (!stack.peek().hasNext()) {
                stack.pop();
            } else if ((nextEl = stack.peek().next()).isInteger()) {
                return true;
            } else {
                stack.push(nextEl.getList().iterator());
            }
        }
        return false;
    }
}