//Space complexity is O(N+L) N is length of total integers, L is length of nested list
import java.util.NoSuchElementException;

public class NestedIterator implements Iterator<Integer> {

    private Deque<NestedInteger> stack;
    //Time complexity is O(N+L) N is total integers, L is nestedlist length
    public NestedIterator(List<NestedInteger> nestedList) {
        stack = new ArrayDeque(nestedList);
    }
        
    //Time complexity is O(N/L) N is total integers, L is nestedlist length
    @Override
    public Integer next() {
        if (!hasNext()) throw new NoSuchElementException();
        return stack.removeFirst().getInteger();
    }

    //Time complexity is O(N/L) N is total integers, L is nestedlist length
    @Override
    public boolean hasNext() {
        helper();
        return !stack.isEmpty();
    }

//Time complexity is O(N/L) N is total integers, L is nestedlist length
    private void helper() {
        while (!stack.isEmpty() && !stack.peekFirst().isInteger()) {
            List<NestedInteger> nestedList = stack.removeFirst().getList();
            for (int i = nestedList.size() - 1; i >= 0; i--) {
                stack.addFirst(nestedList.get(i));
            }
        }
    }
}