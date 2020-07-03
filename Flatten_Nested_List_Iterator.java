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

/**
 * Time: O(D) D-maximum depth of the stack(maximum number of lists inside each other)
 * Space: O(D)
 */
public class NestedIterator implements Iterator<Integer> {
    Stack<Iterator<NestedInteger>> stk;
    NestedInteger nextInt;
    public NestedIterator(List<NestedInteger> nestedList) {
        stk = new Stack<>();
        stk.push(nestedList.iterator());
    }

    @Override
    public Integer next() {
        return nextInt.getInteger();
    }

    @Override
    public boolean hasNext() {
        while(!stk.isEmpty()){
            if(!stk.peek().hasNext()) stk.pop();
            else if((nextInt=stk.peek().next()).isInteger())
            {
                return true;
            }
            else
                stk.push(nextInt.getList().iterator());
        }
        return false;
    }
}

/**
 * Your NestedIterator object will be instantiated and called as such:
 * NestedIterator i = new NestedIterator(nestedList);
 * while (i.hasNext()) v[f()] = i.next();
 */

