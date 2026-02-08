// This is the interface that allows for creating nested lists.
// hasNext() walks through nested lists until it finds the next actual integer, while next() simply returns the integer previously discovered by hasNext().
// Time Complexity: O(1) amortized. 
// Space Complexity: O(d) for the stack used to keep track of iterators at each level of nesting.


class NestedIterator(nestedList: List<NestedInteger>) {
    Stack<Iterator<NestedInteger>> st;
    NestedInteger nextEl;

    public NestedIterator(List<NestedInteger> nestedList) {
        this.st = new Stack<>();
        st.push(nestedList.iterator());
    }
    fun next(): Int {
        return nextEl.getInteger();

    }
    
    fun hasNext(): Boolean {
        while(!st.isEmpty()){
            if(!st.peek().hasNext()){
                st.pop();
                nextEl = null;
            }else{
                nextEl = st.peek().next();
                if(nextEl.isInteger()){
                    return true;
                }else{
                    st.push(nextEl.getList().iterator());
                }
            }
        }
        return false;
    }
}