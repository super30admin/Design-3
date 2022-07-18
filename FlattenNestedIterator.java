import java.util.Iterator;
import java.util.List;
import java.util.Stack;

// Time Complexity : O(d); d - depth of nested list
// Space Complexity : O(d)
// Did this code successfully run on Leetcode : Yes


public class FlattenNestedIterator {

      public interface NestedInteger {

              // @return true if this NestedInteger holds a single integer, rather than a nested list.
              public boolean isInteger();

              // @return the single integer that this NestedInteger holds, if it holds a single integer
              // Return null if this NestedInteger holds a nested list
              public Integer getInteger();

              // @return the nested list that this NestedInteger holds, if it holds a nested list
              // Return empty list if this NestedInteger holds a single integer
              public List<NestedInteger> getList();
  }

    public class NestedIterator implements Iterator<Integer> {
        Stack<Iterator<NestedInteger>> st;
        NestedInteger nextEle;

        public NestedIterator(List<NestedInteger> nestedList) {
            st = new Stack<>();
            st.push(nestedList.iterator());
        }

        @Override
        public Integer next() {
            return nextEle.getInteger();
        }

        @Override
        public boolean hasNext() {
            while(!st.isEmpty()){
                //iterator on top of st has run out of elements in list
                if(!st.peek().hasNext()){
                    st.pop();
                }else if((nextEle = st.peek().next()).isInteger()){
                    return true;
                }else {
                    st.push(nextEle.getList().iterator());
                }
            }
            return false;
        }
    }

}
