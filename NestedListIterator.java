// Time Complexity : O(1)
// Space Complexity : O(K) - where you have a maximum nesting depth of k.
// Did this code successfully run on Leetcode : Yes
// Any problem you faced while coding this : No
// Your code here along with comments explaining your approach: Iterator approach. at every nesting point a new cop of the array is pushed on top of the exsisting copy. Method uses a stack.


public class NestedIterator implements Iterator<Integer> {
      Stack<Iterator<NestedInteger>> st;
      NestedInteger nextInt;
    public NestedIterator(List<NestedInteger> nestedList) {
        st=new Stack<>();
        st.push(nestedList.iterator());
    }

    @Override
    public Integer next() {
        return nextInt.getInteger();
    }

    @Override
    public boolean hasNext() {
        while(!st.isEmpty()){
             if(!st.peek().hasNext()) st.pop();
       else if((nextInt=st.peek().next()).isInteger()) 
           return true;
        else st.push(nextInt.getList().iterator());
        }
        return false;
    }
    
    
}