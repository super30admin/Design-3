/**
 * Time complexity: O(1)
 * Space complexity: O(N + L) where N is total number of integers and L is number of nested lists.
 */
public class NestedIterator implements Iterator<Integer> {

    Stack<Iterator<NestedInteger>> stack;
    NestedInteger element; // when we use next -> iterator automatically moves to next so store the current in global variable 

    public NestedIterator(List<NestedInteger> nestedList) {
        stack = new Stack<>();
        stack.push(nestedList.iterator());
    }

    @Override
    public Integer next() {
        return element.getInteger();
    }

    @Override
    public boolean hasNext() {
        while (!stack.isEmpty()) {
            if (!stack.peek().hasNext()) {
                stack.pop();
            } else if ((element = stack.peek().next()).isInteger()) {
                return true;
            } else {
                stack.push(element.getList().iterator());
            }
        }
        return false;
        
    }
}

/**
 * Your NestedIterator object will be instantiated and called as such:
 * NestedIterator i = new NestedIterator(nestedList);
 * while (i.hasNext()) v[f()] = i.next();
 */