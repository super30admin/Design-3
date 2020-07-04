// Time Complexity : O(n) --> where n is the total number of intergers in the input list
// Space Complexity : O(m) --> where m is the number of nested list inside the input list
// Did this code successfully run on Leetcode (341): Yes 
// Any problem you faced while coding this : No


// Your code here along with comments explaining your approach

public class NestedIterator implements Iterator<Integer> {
    Stack<Iterator<NestedInteger>> st;
    NestedInteger nextInt;
    public NestedIterator(List<NestedInteger> nestedList) {
        st = new Stack<>();
        st.push(nestedList.iterator());
    }

    @Override
    public Integer next() {
        return nextInt.getInteger();
    }

    @Override
    public boolean hasNext() {
        while(!st.isEmpty()) {
            if (!st.peek().hasNext()) st.pop();
            else if ((nextInt = st.peek().next()).isInteger()) return true; 
            else st.push(nextInt.getList().iterator());
        }
        return false;
    }    
}

