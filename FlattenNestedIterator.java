//Time Complexity = O(1)
//Space Complexity = O(D) maximum depth of the stack
//Did it run on leetcode : yes
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
        
        while(!st.isEmpty()){
            
            if(!st.peek().hasNext()){
                st.pop();
            }
            else if((nextInt  = st.peek().next()).isInteger())
                return true;
            else
                st.push(nextInt.getList().iterator());
      
        }
        return false;
    }
}

/**
 * Your NestedIterator object will be instantiated and called as such:
 * NestedIterator i = new NestedIterator(nestedList);
 * while (i.hasNext()) v[f()] = i.next();
 */
