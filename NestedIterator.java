import java.util.Iterator;
import java.util.List;
import java.util.Stack;


/**
 * Time Complexity: next() - O(1), hashNext() - O(k), k is number of depths/list
 * Space Complexity: O(k)
 * Did this code successfully run on Leetcode: Yes
 * Any problem you faced while coding this: None

 * Approach:
 * Hint: Here the class implements a iterator, so in a situation like this, we can use an iterator.
 * A stack (s) is used to store iterators of NestedInteger lists.
 * hasNext ensures that nextElement holds the next integer to be returned. 
 * Instead of fully flattening the list beforehand, the approach dynamically flattens
 * it on-demand, maintaining efficient traversal using a stack-based DFS strategy.
 */
public class NestedIterator implements Iterator<Integer> {
    public interface NestedInteger {
        public boolean isInteger();
        public Integer getInteger();
        public List<NestedInteger> getList();
    }

    Stack<Iterator<NestedInteger>> s;

    NestedInteger nextElement;
    public NestedIterator(List<NestedInteger> nestedList) {
        s = new Stack<>();
        // put the given list iterator into stack
        s.push(nestedList.iterator());
    }

    @Override
    public Integer next() {
        // 
        return nextElement.getInteger();
    }

    @Override
    public boolean hasNext() {
        while (!s.isEmpty()) {
            if (!s.peek().hasNext()) {
                s.pop();
            } else if ((nextElement = s.peek().next()).isInteger()) {
                return true;
            } else {
                s.push(nextElement.getList().iterator());
            }
        }

        return false;
    }
}