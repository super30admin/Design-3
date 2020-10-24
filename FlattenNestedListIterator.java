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
 *     // Return null if this NestedInteger holds a single integer
 *     public List<NestedInteger> getList();
 * }
 Time Complexity: O(M*N)
 */
public class NestedIterator implements Iterator<Integer> {

    Stack<NestedInteger> stack;

    public NestedIterator(List<NestedInteger> nestedList) {

        stack = new Stack<>();
        prepareStack(nestedList);

    }

    @Override
    public Integer next() {
        Integer val = null;
        if(hasNext())
        {
            val = stack.pop().getInteger();
        }
        return val;
    }

    @Override
    public boolean hasNext() {

        while(!stack.isEmpty() && !stack.peek().isInteger())
        {
            List<NestedInteger> list = stack.pop().getList();
            prepareStack(list);
        }
        return !stack.isEmpty();
    }

    private void prepareStack(List<NestedInteger> nestedList)
    {
        for(int i = nestedList.size()-1 ; i>=0 ; i--)
        {
            stack.add(nestedList.get(i));
        }
    }
}

/**
 * Your NestedIterator object will be instantiated and called as such:
 * NestedIterator i = new NestedIterator(nestedList);
 * while (i.hasNext()) v[f()] = i.next();
 */
