// Time Complexity : O(n), integers in the list
// Space Complexity : O(d), depth of list
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

  Stack<Iterator<NestedInteger>> stack;
  NestedInteger newEl;
  public NestedIterator(List<NestedInteger> nestedList) {
    stack = new Stack<>();
    // Iterator that points to the first element
    stack.push(nestedList.iterator());
    newEl = null;
  }

  @Override
  public Integer next() {
    return newEl.getInteger();
  }

  @Override
  public boolean hasNext() {
    while(!stack.isEmpty()) {
      //Iterator interface has hasNext() and next() methods
      if(!stack.peek().hasNext()) {
        stack.pop();
        // If the next element is an integer, return true
      }else if((newEl = stack.peek().next()).isInteger()) {
        return true;
        // If the next element is a list, add it to the stack with an iterator.
      }else {
        stack.push(newEl.getList().iterator());
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