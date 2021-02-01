// Time Complexity : The time complexity is O(n) where n is the number of nested lists
// Space Complexity : The space complexity is O(n) where n is the number of nested lists
// Did this code successfully run on Leetcode : Yes
// Any problem you faced while coding this : No


// Your code here along with comments explaining your approach

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

    // Stack to store the nested lists till depth
    Stack<Iterator<NestedInteger>> stack;
    NestedInteger cur;

    public NestedIterator(List<NestedInteger> nestedList) {

        stack = new Stack<>();
        stack.push(nestedList.iterator());

    }

    @Override
    public Integer next() {

        return cur.getInteger();

    }

    @Override
    public boolean hasNext() {

        while(!stack.isEmpty()){

            // If iteration on the current list is done, remove it
            if(!stack.peek().hasNext()){
                stack.pop();
            }
            // If the current element is integer, return it
            else if((cur = stack.peek().next()).isInteger()){
                return true;
            }
            // If the current element is a list, add it to the stack
            else{
                stack.push(cur.getList().iterator());
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