// Time Complexity:O(1)
// Space Complexity: O(depth of the nested list)
// Did this code successfully run on Leetcode: Yes
// Any problem you faced while coding this:No


// Your code here along with comments explaining your approach
/*
 * Remember, the property of an iterator is the dynamic ability. If any element changes in the main DS,
 * that element should be available on next(). We are using a stack of native iterators of type nestedIntegers.
 * Also, next() will give the element at which the pointer is at currently but the pointer also goes to the
 * next element. So, we first push the native iterator form of the nestedlist into the stack. On next(), we get the 
 * element at which the pointer is at, we first check if that is an Integer - return true if yes. If the next() has gone out
 * of bounds, return do a st.pop(). If there is a list, put a list version of that element inside the stack
 * and convert that into an iterator.
 */

public class NestedInteger {
       
    Stack<Iterator<NestedInteger>> st;
    NestedInteger nextEl;
    public NestedIterator(List<NestedInteger> nestedList) {
        st = new Stack<>();
        st.push(nestedList.iterator());
    }

    @Override
    public Integer next() {
        return nextEl.getInteger();
    }

    @Override
    public boolean hasNext() {
        while(!st.isEmpty())
        {
            if(!st.peek().hasNext()){
                st.pop();
            }
            else if((nextEl = st.peek().next()).isInteger())
            {
                return true;
            }
            else
            {
                st.push(nextEl.getList().iterator());
            }
        }
        return false;
    }
}
