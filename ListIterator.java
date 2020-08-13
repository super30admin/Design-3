// Time Complexity : O(n) for craeting a queue, O(1) for both operations
// Space Complexity : O(n) for queue
// Did this code successfully run on Leetcode : Yes
// Any problem you faced while coding this : No


import java.util.*;

public class ListIterator {
    /**
     * // This is the interface that allows for creating nested lists.
     * // You should not implement it, or speculate about its implementation
     * public interface NestedInteger {
     *
     *     // @return true if this NestedInteger holds a single integer, rather than a nested list.
     *     public boolean isInteger();
     *
     *     // @return the single integer that this NestedInteger holds, if it holds a single integer
     *     // Return null if this NestedInteger holds a nested list
     *     public Integer getInteger();
     *
     *     // @return the nested list that this NestedInteger holds, if it holds a nested list
     *     // Return null if this NestedInteger holds a single integer
     *     public List<NestedInteger> getList();
     * }
     */
    class NestedInteger1{
        public boolean isInteger(){
            return true;
        }

        public int getInteger(){
            return 0;
        }

        public List<NestedInteger1> getList(){
            return new ArrayList<NestedInteger1>();
        }
    }

     class NestedIterator implements Iterator<Integer> {
        Queue<Integer> q;
        public NestedIterator(List<NestedInteger1> nestedList) {
            q = new LinkedList<>();
            addAll(nestedList);
        }

        private void addAll(List<NestedInteger1> nestedList){
            for(NestedInteger1 n : nestedList){
                if(n.isInteger()){
                    q.add(n.getInteger());
                } else {
                    addAll(n.getList());
                }
            }
        }
        @Override
        public Integer next() {
            return !q.isEmpty() ? q.poll() : -1;
        }

        @Override
        public boolean hasNext() {
            return !q.isEmpty();
        }
    }

    /**
     * Your NestedIterator object will be instantiated and called as such:
     * NestedIterator i = new NestedIterator(nestedList);
     * while (i.hasNext()) v[f()] = i.next();
     */

    // Google Type Code
    Stack<ListIterator<NestedInteger>> st;
    public NestedIterator(List<NestedInteger> nestedList) {
        st = new Stack<>();
        st.push(nestedList.listIterator());
    }

    @Override
    public Integer next() {
        hasNext();
        return st.peek().next().getInteger();
    }

    @Override
    public boolean hasNext() {
        while(!st.isEmpty()){

            if(!st.peek().hasNext()){
                st.pop();
            } else {
                NestedInteger x = st.peek().next();
                if(x.isInteger()){
                    return st.peek().previous() == x;
                } else {
                    st.push(x.getList().listIterator());
                }
            }
        }

        return false;
    }


}
