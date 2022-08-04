//TC=O(n)
//SC=O(h)
//Intuit
//Controlled Recursive stack
//Designing the control of the recursive stack based on the nested lists and integer values to get the element by using the flatten iterator.

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
    Queue<Integer> q;


    public NestedIterator(List<NestedInteger> nestedList) {
        q = new LinkedList<>();
        flattenList(nestedList);
    } //Bulk of time is taken here becuase using here constructor I don't consider.

    @Override
    public Integer next() {
        return q.poll();
        
    }

    @Override
    public boolean hasNext() {
        return !q.isEmpty();
    }
    private void flattenList(List<NestedInteger> nestedList){
        for(NestedInteger el: nestedList){
            if(el.isInteger()){
                q.add(el.getInteger());
            }
            else{
                flattenList(el.getList());
            }
        }
    }
}

/**
 * Your NestedIterator object will be instantiated and called as such:
 * NestedIterator i = new NestedIterator(nestedList);
 * while (i.hasNext()) v[f()] = i.next();
 */