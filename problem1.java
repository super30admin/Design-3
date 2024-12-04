import java.util.Iterator;
import java.util.List;
import java.util.Stack;
//Overall iteration: O(n).
//Space Complexity: O(d), where d is the maximum depth of the nested list.
public class problem1 {
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

        private Stack<NestedInteger> stack;;

        public NestedIterator(List<NestedInteger> nestedList) {
            stack= new Stack<>();
            for(int i=nestedList.size()-1;i>=0;i--)
            {
                stack.push(nestedList.get(i));
            }
        }

        @Override
        public Integer next() {
            if (hasNext()) {
                return stack.pop().getInteger();
            }
            return null;
        }

        @Override
        public boolean hasNext() {
            while (!stack.isEmpty()) {
                NestedInteger current = stack.peek();
                if (current.isInteger()) {
                    return true;
                }
                stack.pop();
                List<NestedInteger> nestedList = current.getList();
                // Push nested list elements in reverse order
                for (int i = nestedList.size() - 1; i >= 0; i--) {
                    stack.push(nestedList.get(i));
                }
            }
            return false;
        }
    }
}
