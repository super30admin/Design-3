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

/**
LeetCode Submitted : YES
Space Complexity : O(Num of Elements in LinkedList)
Time Complexity : O(Num of Elements in LinkedList)

The main key is to use Queue as Data Structure and then use it to iterate with operations next() and hasNext(). 
**/
public class NestedIterator implements Iterator<Integer> {

    Queue<Integer> q = new LinkedList<>();
    public NestedIterator(List<NestedInteger> nestedList) {
        queueFill(nestedList);
    }

    @Override
    public Integer next() {
        return q.poll();
    }

    @Override
    public boolean hasNext() {
        return (!q.isEmpty());
    }
    
    private void queueFill(List<NestedInteger> nestedList){
        for(NestedInteger i : nestedList){
            if(i.isInteger()){
                q.add(i.getInteger());
            }else{
                queueFill(i.getList());
            }
        }
    }
}

/**
 * Your NestedIterator object will be instantiated and called as such:
 * NestedIterator i = new NestedIterator(nestedList);
 * while (i.hasNext()) v[f()] = i.next();
 */
