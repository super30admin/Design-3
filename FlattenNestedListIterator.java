// Time Complexity : O(m+n) 
// Space Complexity : O(m) 
// Did this code successfully run on Leetcode : Yes
// Any problem you faced while coding this : No

public class NestedIterator implements Iterator<Integer> {
  Stack<Iterator<NestedInteger>> s;
  NestedInteger element;

  public NestedIterator(List<NestedInteger> nestedList) {
    s = new Stack<>();
    s.push(nestedList.iterator());
  }

  @Override
  public Integer next() {
    return element.getInteger();
  }

  @Override
  public boolean hasNext() {
    while (!s.isEmpty()) {
      if (!s.peek().hasNext()) {
        s.pop();
      } else if ((element = s.peek().next()).isInteger()) {
        return true;
      } else {
        s.push(element.getList().iterator());
      }
    }
    return false;
  }
}