import java.util.Iterator;
import java.util.Queue;

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

/**
 * @author Vishal Puri
 * // Time Complexity : O(1)
 * // Space Complexity : O(h) h is max size of the queue
 * // Did this code successfully run on Leetcode : Yes
 * // Any problem you faced while coding this :
 */


public class NestedIterator implements Iterator<Integer> {
    private Queue<Integer> q = new LinkedList<>();

    public NestedIterator (List<NestedInteger> nestedList) {
        flattenList(nestedList);
    }

    public void flattenList(List<NestedInteger> nestedList){
        for (NestedInteger nestedInt: nestedList){
            if (nestedInt.isInteger()){
                q.offer(nestedInt.getInteger());
            } else {
                flattenList(nestedInt.getList());
            }
        }
    }

    @Override
    public Integer next() {
        return q.peek() != null ? q.poll() : null;
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