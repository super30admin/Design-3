import java.util.Iterator;
import java.util.List;
import java.util.Stack;


  // This is the interface that allows for creating nested lists.
  // You should not implement it, or speculate about its implementation
  public interface Problem1 {
 
      // @return true if this NestedInteger holds a single integer, rather than a nested list.
      public boolean isInteger();
 
      // @return the single integer that this NestedInteger holds, if it holds a single integer
      // Return null if this NestedInteger holds a nested list
      public Integer getInteger();
 
      // @return the nested list that this NestedInteger holds, if it holds a nested list
      // Return empty list if this NestedInteger holds a single integer
      public List<Problem1> getList();
  }


public class NestedIterator implements Iterator<Integer> {
    Stack<Iterator<Problem1>> st;
    Problem1 nextEle;
    public NestedIterator(List<Problem1> nestedList) {
        st = new Stack<>();
        st.push(nestedList.iterator());
    }

    @Override
    public Integer next() {   // O(1)
        return nextEle.getInteger();
    }

    @Override
    public boolean hasNext() {  // O(1)
        while (!st.isEmpty()) {
            if (!st.peek().hasNext()) {
                st.pop();
            } else if ((nextEle = st.peek().next()).isInteger()) {
                return true;
            } else {
                st.push(nextEle.getList().iterator());
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
