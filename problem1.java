// Time Complexity :O(1)
// Space Complexity :O(n)
// Did this code successfully run on Leetcode :yes
// Any problem you faced while coding this : still i bit fuzzy on the logic

/*
 * Approach
 * 
 * We are using the stack over the hood to do a controlled recursion
 * 
 * initially we when the NesterIterator is loaded is will put
 * Java's own itterator on the nesterList and push it to the stack
 * 
 * when has next function is called we will check if the stack is empty or not
 * if empty pop 
 * if it is any int return true
 * or will it is a NESTEDINTEGER push it to the stack
 * 
 * since all the heavy lifting is done by hashnext to find the next int 
 * next just has to get the int value from the NEXTEL var
 * 
 * 
 */

// Your code here along with comments explaining your approach
public class NestedIterator implements Iterator<Integer> {
    private Stack<Iterator<NestedInteger>> st;
    private NestedInteger nextEl;

    public NestedIterator(List<NestedInteger> nestedList) {
        this.st = new Stack<>();
        st.push(nestedList.iterator());
    }

    @Override
    public Integer next() {
        return nextEl.getInteger();
    }

    @Override
    public boolean hasNext() {
        while (!st.isEmpty()) {
            if (!st.peek().hasNext()) {
                st.pop();
            } else if ((nextEl = st.peek().next()).isInteger()) {
                return true;
            } else {
                st.push(nextEl.getList().iterator());
            }
        }
        return false;
    }
}
