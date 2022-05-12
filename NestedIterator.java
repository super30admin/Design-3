import java.util.Iterator;
import java.util.List;
import java.util.Stack;

public class NestedIterator implements Iterator<Integer> {

    Stack<Iterator<NestedInteger>> stack;
    NestedInteger nextEl;

    public NestedIterator(List<NestedInteger> nestedList) {
        stack = new Stack<>();
        stack.push(nestedList.iterator());
    }

    // TC: O(d) d - depth of the nested list. It depends on how deeply a list is nested
    // SC: O(d)
    // Worst case scenario -> [1,[2,[3,[4,[5,[6]]]]]]
        // We will need to put each nested list onto the stack which increases both the complexities
    @Override
    public boolean hasNext() {

        while(!stack.isEmpty()) {
            // hasNext & next on stack.top() / stack.peek() object are the native methods since we are storing the iterator in the stack
                // and not NestedInteger
            if(!stack.peek().hasNext()) {
                // The current iterator for NestedInteger object does not have anything to process
                // In short, we are done with processing a list (either a sublist of the input list OR the entire input list)
                stack.pop();
            }else if((nextEl = stack.peek().next()).isInteger()) {
                return true;
            }else {
                // If the current NestedInteger object is a list then put on top of the stack
                stack.push(nextEl.getList().iterator());
            }
        }
        return false;
    }

    // TC: O(1)
    // SC: O(1)
    @Override
    public Integer next() {
        return nextEl.getInteger();
    }
}
