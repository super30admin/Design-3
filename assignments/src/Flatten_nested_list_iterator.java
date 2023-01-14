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
// Approach: We create a stack of <Iterator<NestedInteger>> and push the nestedList's iterator first
// For hasNext(): Check inside that whether we are getting an integer or another list
// For next(): always return integer only
// Time: next() is O(1), hasNext is O(D) D is the depth of integer (nested depth)
// Space: O(D) nexted depth as we are using stack to store nestedLists
public class NestedIterator implements Iterator<Integer> {
    private NestedInteger nextEl;
    private Stack<Iterator<NestedInteger>> stack;           // IMP: Stack of <Iterator<NestedInteger>>

    public NestedIterator(List<NestedInteger> nestedList) {
        this.stack = new Stack();
        this.stack.push(nestedList.iterator());
    }

    @Override
    public Integer next() {
        return nextEl.getInteger();     // we make sure integer is returned here (hasNext will handle if not an Integer)
    }

    @Override
    public boolean hasNext() {
        while (!stack.isEmpty()) {
            if (!stack.peek().hasNext()) {                          // if list ends, pop()
                stack.pop();
            }
            else if ((nextEl = stack.peek().next()).isInteger()) {  // if integer, return true
                return true;
            }
            else {
                stack.push(nextEl.getList().iterator());            // else, get the list's iterator and push it onto the stack
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
