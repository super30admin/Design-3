// TC: O(1) for both operations
// SC: O(D) where D is the depth of the nested list

// 1: We use iterators to lazy load the data, and implement it using a stack
// 2: We push the entire nestedList into the stack and then iterate through each element. If the element is a List, we add that list to the top of the stack
// 3: Once we reach out of bounds from the next element, we pop and continue iteration with the previous element. In the case of an integer, we store the integer value as is.
public class NestedIterator implements Iterator<Integer> {
    private Stack<Iterator<NestedInteger>> st;
    private NestedInteger nextEle;

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
            if(!st.peek().hasNext()){
                // pop from the stack
                st.pop();
            }
            // checking if the next element in the stack is an Integer
            else if((nextEle = st.peek().next()).isInteger()){
                return true;
            }
            // if it is a list, push into stack and add an iterator on it
            else{
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