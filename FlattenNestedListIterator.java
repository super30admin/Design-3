// Time Complexity : O(n)
// Space Complexity : O(1)
// Did this code successfully run on Leetcode : Yes
// Any problem you faced while coding this : No

// Notes : Create a stack and add the NestedInteger as an iterator. While stack is not empty check the top of the stack, if no next element it found, pop the element. If the top of the stack is an integer, hasNext() will be true, else if a list is found, add the List as in iterator to the stack.
 
public class FlattenNestedListIterator {
    Stack<Iterator<NestedInteger>> st;
    NestedInteger nextElement;
    public NestedIterator(List<NestedInteger> nestedList) {
        st = new Stack<>();
        st.add(nestedList.iterator());
    }

    @Override
    public Integer next() {
        return nextElement.getInteger();
    }

    @Override
    public boolean hasNext() {
        while(!st.isEmpty()){
            // If top of stack doesnt have next element, pop it
            if(!st.peek().hasNext()){
                st.pop();
            } 
            // If the next() of iterator at the top of stack has an Integer
            else if((nextElement = st.peek().next()).isInteger()){ 
                return true;
            } 
            // If the next() of iterator at the top of stack has an List, make it as iterator and ad to stack
            else{
                st.push(nextElement.getList().iterator());
            }
        }
        return false;
    }
}
