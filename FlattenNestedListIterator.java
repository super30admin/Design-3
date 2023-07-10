import java.util.List;
import java.util.Iterator;
import java.util.Stack;

public class FlattenNestedListIterator {

    // This is the interface that allows for creating nested lists.
    // You should not implement it, or speculate about its implementation
    public interface NestedInteger {


        // @return true if this NestedInteger holds a single integer, rather than a nested list.
        boolean isInteger();

        // @return the single integer that this NestedInteger holds, if it holds a single integer
        // Return null if this NestedInteger holds a nested list
        Integer getInteger();

        // @return the nested list that this NestedInteger holds, if it holds a nested list
        // Return empty list if this NestedInteger holds a single integer
        List<NestedInteger> getList();
      }


    public static class NestedIterator implements Iterator<Integer> {

        // a NestedInteger element
        private NestedInteger nextEl;

        // stack of iterator on NestedIntegers of given nested list
        public Stack<Iterator<NestedInteger>> st;

        public NestedIterator(List<NestedInteger> nestedList) {

            this.st = new Stack<>();

            //put iterator on given nested list and push it into stack
            st.push(nestedList.iterator());

        }

        @Override
        public Integer next() { //O(1)

            // give next integer of NestedInteger element
            return nextEl.getInteger();
        }

        @Override
        public boolean hasNext() { //HasNext

            // run until stack is not empty
            while(!st.isEmpty()) { // O(d)

                // if top iterator in stack has nothing in next (hasNext == false) i.e., next pointer is on null
                if(!st.peek().hasNext()) {

                    // pop out top iterator from stack as its processing is done
                    st.pop();
                }

                // get top iterator-in-stack's next element, store before next moves and check if it is an integer
                else if((nextEl = st.peek().next()).isInteger()) {

                    // output true if next element (NestedInteger) is an integer
                    return true;
                }

                else {

                    // if next element (NestedInteger) is another list of nested list, put an iterator and push into stack
                    st.push(nextEl.getList().iterator());
                }
            }
            //once stack is empty and integer is not found, return false i.e., nestedList is processed
            return false;
        }
    }


}

/*
TIME COMPLEXITY = O(1) - average, O(D) - worst case

D = maximum depth of stack = maximum number of open brackets without a following closing bracket in nested list

*/

/*
 * Your NestedIterator object will be instantiated and called as such:
 * NestedIterator i = new NestedIterator(nestedList);
 * while (i.hasNext()) v[f()] = i.next();
 */

