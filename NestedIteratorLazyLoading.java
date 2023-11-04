// Time Complexity : O(1)
// Space Complexity : O(N) where N is size of List
// Did this code successfully run on Leetcode : Yes


public class NestedIteratorLazyLoading implements Iterator<Integer> {
    Stack<Iterator<NestedInteger>> st;
    NestedInteger nextElem;
    public NestedIterator(List<NestedInteger> nestedList) {
        this.st = new Stack();
        this.st.push(nestedList.iterator());
    }

    @Override
    public Integer next() {
        return this.nextElem.getInteger();
    }

    @Override
    public boolean hasNext() {
        while(!st.isEmpty()){
            if(!st.peek().hasNext()){
                st.pop();
            }else if((this.nextElem = st.peek().next()).isInteger()){
                return true;
            }
            else{
                st.push(this.nextElem.getList().iterator());
            }
        }
        return false;
    }
}