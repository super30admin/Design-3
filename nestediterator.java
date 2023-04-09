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
 *     // Return empty list if this NestedInteger holds a single integer
 *     public List<NestedInteger> getList();
 * }
 */

public class NestedIterator implements Iterator<Integer> {
    // stack to maintain current native iterators
    private Stack<Iterator<NestedInteger>> st;
    private NestedInteger nextel;

    public NestedIterator(List<NestedInteger> nestedList) {
        // push iterator object of given list into stack
        this.st = new Stack<>();
        this.st.push(nestedList.iterator());
    }

    @Override
    public Integer next() { //O(1)
        // return integer of current element of type nestedlist
        return nextel.getInteger();
    }

    @Override
    public boolean hasNext() { //O(d)-depth of stack both TC and SC
        // if st is empty do nothing
        while(!st.isEmpty()){
            // if the top element of stack has no next, pop it
            if (!st.peek().hasNext()){
                st.pop();
            }
            // if its integer store in nextel and return true
            else if ((nextel = st.peek().next()).isInteger()){
                return true;
            }
            // if notr integer, push its iterator object to st
            else{
                st.push(nextel.getList().iterator());   
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