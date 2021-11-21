//Time Complexity: O(1)
//Space Complexity: O(depth)

/**
 * // This is the interface that allows for creating nested lists.
 * // You should not implement it, or speculate about its implementation
 * public interface NestedInteger {
 *
 *     // @return true if this NestedInteger holds a single integer, rather than a nested list.
 *     public boolean isInteger();
 *
 *     // @return the single integer that this NestedInteger holds, if it holds a single integer
 *     // Return null if this NestedInteger holds a nested list
 *     public Integer getInteger();
 *
 *     // @return the nested list that this NestedInteger holds, if it holds a nested list
 *     // Return empty list if this NestedInteger holds a single integer
 *     public List<NestedInteger> getList();
 * }
 */
public class NestedIterator implements Iterator<Integer> {
    Stack<Iterator<NestedInteger>> stack;
    NestedInteger current;
    public NestedIterator(List<NestedInteger> nestedList) {
        stack = new Stack<>();
        current = null;
        stack.push(nestedList.iterator());
    }

    @Override
    public Integer next() {
        return current.getInteger();
    }

    @Override
    public boolean hasNext() {
        while(!stack.isEmpty()){
            if(!stack.peek().hasNext()){
                stack.pop();
                continue;
            }
            current = stack.peek().next();
            if(current.isInteger())
                return true;
            else
                stack.push(current.getList().iterator());

        }
        return false;
    }
}

/**
 * Your NestedIterator object will be instantiated and called as such:
 * NestedIterator i = new NestedIterator(nestedList);
 * while (i.hasNext()) v[f()] = i.next();
 */