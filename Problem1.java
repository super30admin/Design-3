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
 */
 // Space Complexity - O(N+L) N = number of integers and L number of lists
 // Time Complexity - O(N+L)
public class NestedIterator implements Iterator<Integer> {

    private Stack<NestedInteger> stack;

    public NestedIterator(List<NestedInteger> nestedList) {
        stack = new Stack();

        for(int i = nestedList.size() -1; i >= 0; i--){
            stack.push(nestedList.get(i));
        }
    }

    @Override
    public Integer next() {

        if(!hasNext())
            return null;

        return stack.pop().getInteger();
    }

    @Override
    public boolean hasNext() {

        setTopAsInteger();

        return !stack.isEmpty();
    }

    private void setTopAsInteger(){

        while(!stack.isEmpty() && !stack.peek().isInteger()){

            List<NestedInteger> nestedList = stack.pop().getList();
            for(int i= nestedList.size()-1; i>=0; i--)
                stack.push(nestedList.get(i));
        }
    }
}

/**
 * Your NestedIterator object will be instantiated and called as such:
 * NestedIterator i = new NestedIterator(nestedList);
 * while (i.hasNext()) v[f()] = i.next();
 */