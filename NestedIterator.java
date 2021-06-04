// Time Complexity:
//          - O(1) for fetching the next element.
//          - O(H) for hasNext where H is the max depth of nested list.
// Space Complexity : O(H) At max.
// Did this code successfully run on Leetcode : yes
// Any problem you faced while coding this : No


// Your code here along with comments explaining your approach

public class NestedIterator implements Iterator<Integer> {

    Stack<Iterator<NestedInteger>> st;
    
    NestedInteger nextEle;

    public NestedIterator(List<NestedInteger> nestedList) {
        st = new Stack<>();
        st.push(nestedList.iterator());
    }

    @Override
    public Integer next() {
        // Next element will always have the element that we want to return.
        // so we can blindly fetch the integer from the Iterator and return it. 
        return nextEle.getInteger();
    }

    @Override
    public boolean hasNext() {
        
        while(!st.isEmpty()){
            
            // If the top of the stack doesn't have a next element then we have traversed all the elements in the list in the top of the stack.
            // In that scenario remove the iterator that is on the top.
            if(!st.peek().hasNext()){
                st.pop();
            }else{
                // Here the iterator is pointing to an element on the top of the stack.
                // Fetching the top element from the stack.
                nextEle = st.peek().next();
                
                // If the top element is an Integer then the iterator is at the current position.
                if(nextEle.isInteger()){
                    return true;
                }else{
                    // If the top element is a list we push the list to top of the stack.
                    st.push(nextEle.getList().iterator());
                    // While loop takes care of moving the iterator to the correct position.
                }
            }
        }
        return false;
    }
}